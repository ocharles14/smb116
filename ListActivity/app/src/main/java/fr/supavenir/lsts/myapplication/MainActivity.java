package fr.supavenir.lsts.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ArrayAdapter<String> lvAdaptateur;
    ListView lvTaches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setTitle("Mes tâches");
        lvTaches = (ListView) findViewById( R.id.lvTaches );

        // L'adaptateur adapte la vue (layout) et le modèle des éléments
        // Le layout pour visualiser une chaîne existe nativement dans Android
        // simple_list_item_1
        lvAdaptateur =  new ArrayAdapter<String>( this ,
                android.R.layout.simple_list_item_1 );
        lvTaches.setAdapter( lvAdaptateur );

        lvAdaptateur.add("Comprendre les Intents -> 01/03/2022");
        lvAdaptateur.add("Comprendre les ListViews -> 01/03/2022");
        lvAdaptateur.add("Faire le projet android -> 30/06/2022");

    }
}