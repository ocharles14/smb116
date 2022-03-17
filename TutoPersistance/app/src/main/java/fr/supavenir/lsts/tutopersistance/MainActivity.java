package fr.supavenir.lsts.tutopersistance;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends Activity {
    final static String TAG="MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // La base de donnée a-t'elle été remplie ?
        // Si c'est le cas, cela a été sauvegardé dans les préférences

        boolean dbUpToDate = checkDbState();
        if(!dbUpToDate) {
            createAndPopulateDb();
            writeDbState();
        }
        // la base de données est remplie,
        // il est possible d'afficher la liste des éléments
        showDbItems();
    }

    private boolean checkDbState() {
        Log.d(TAG,"Lecture des préférences utilisateur");
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        return sharedPreferences.getBoolean("dbUpToDate", false);
    }


    // A noter, cette méthode n'est pas optimisée : il ne faut pas
    // effectuer de tache aussi longue sur le thread principal.
    // Une asynctask n'est-elle pas adaptée ?

    private void createAndPopulateDb() {
        Log.d(TAG,"Remplissage de la base de données");

        DbHelper dbHelper = new DbHelper(MainActivity.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        db.beginTransaction();
        for(int i =0;i<25;i++) {
            String title = String.format("Element %d", i+1);
            String description = String.format("Ceci est la description de l'élément %d" , i+1);
            ContentValues contentValues = new ContentValues();
            contentValues.put("title",title);
            contentValues.put("description",description);
            db.insert("DummyItems", null, contentValues);
        }
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
    }

    private void writeDbState() {
        Log.d(TAG,"Ecriture des préférences utilisateur");
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("dbUpToDate", true);
        editor.commit();
    }


    private void showDbItems() {
        Log.d(TAG,"Affichage de la liste");
        ListView listView =(ListView)findViewById(R.id.mainList);
        ArrayList<DummyItem> dummyItems = getDummyItems();
        DummyItemAdapter dummyItemAdapter = new DummyItemAdapter(MainActivity.this, -1, dummyItems);
        listView.setAdapter(dummyItemAdapter);
    }

    @SuppressLint("Range")
    private ArrayList<DummyItem> getDummyItems() {
        String sqlQuery = "Select * from DummyItems order by title";
        DbHelper dbHelper = new DbHelper(MainActivity.this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);
        ArrayList<DummyItem> arrayList = new ArrayList<>(cursor.getCount());
        while (cursor.moveToNext()) {
            DummyItem dummyItem = new DummyItem();
            dummyItem.id = cursor.getInt(cursor.getColumnIndex("id"));
            dummyItem.title = cursor.getString(cursor.getColumnIndex("title"));
            dummyItem.description = cursor.getString(cursor.getColumnIndex("description"));
            arrayList.add(dummyItem);
        }
        return arrayList;
    }

}
