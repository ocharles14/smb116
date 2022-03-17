package fr.supavenir.lsts.tutopersistance;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DbHelper extends SQLiteOpenHelper {
    final static String TAG="DbHelper";
    // La version de la base de données est ici définie à 2,
    // pour pouvoir illustrer le processus d'upgrade
    private final static int dbVersion = 2;
    private final static String dbName="mainDB";

    public DbHelper(Context context) {
        super(context, dbName, null, dbVersion);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        /* Ci dessous, le code qui a été écrit pour la création de la base en version 1.
        db.execSQL("CREATE TABLE DummyItems (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT);");
        */

        // Le code ci-dessous correspond à la version 2 : la colonne description est ajoutée à la table
        db.execSQL("CREATE TABLE DummyItems (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, description TEXT);");

        Log.d(TAG,"la méthode onCreate de DbHelper a été exécutée");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        for (int indexVersion = oldVersion; indexVersion < newVersion;
             indexVersion++) {
            int nextVersion = indexVersion + 1;
            switch (nextVersion) {
                case 2:
                    upgrapdeToVersion2(db);
                    break;
                case 3:
                    // mise à jour future pour la version 3
                    break;
            }

        }
        Log.d(TAG,"la méthode onUpgrade de DbHelper a été exécutée");
    }


    private void upgrapdeToVersion2(SQLiteDatabase db) {
        db.execSQL("ALTER TABLE DummyItems  ADD COLUMN Description TEXT");
    }


}

