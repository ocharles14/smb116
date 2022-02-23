package fr.supavenir.lsts.couleurs;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;


// TODO pouvoir modifier et supprimer la couleur sélectionnée dans la liste
// TODO en ajoutant deux éléments visibles comportant des îcones de type delete et edit


public class ListeCouleurs extends AppCompatActivity {

    private ListView lvListeCouleurs;
    private Button btnAjouterCouleur;
    private AdaptateurCouleur adaptateur;

    ActivityResultLauncher<Intent> lanceurActiviteChoixCouleur = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        int a = result.getData().getIntExtra("a", 255);
                        int r = result.getData().getIntExtra("r", 255);
                        int v = result.getData().getIntExtra("v", 255);
                        int b = result.getData().getIntExtra("b", 255);
                        String nomCouleur = result.getData().getStringExtra("nom");
                        Log.i("COULEUR","couleur "+a+" , " + r + " , " + v +" , "+ b +" " + nomCouleur);
                        adaptateur.ajouterCouleur( new Couleur( a, r, v, b , nomCouleur ));
                        adaptateur.notifyDataSetChanged();
                    }
                }
            } );


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView( R.layout.activite_liste_couleurs );

        adaptateur = new AdaptateurCouleur( this , generationListeCouleurs() );
        lvListeCouleurs = findViewById( R.id.lvCouleurs );
        lvListeCouleurs.setAdapter( adaptateur );

        btnAjouterCouleur = findViewById( R.id.btnAjouterCouleur );
        btnAjouterCouleur.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentionChoixCouleur = new Intent( ListeCouleurs.this , ActiviteChoixCouleur.class );
                lanceurActiviteChoixCouleur.launch( intentionChoixCouleur );
            }
        });
    }

    public ArrayList<Couleur> generationListeCouleurs() {
        ArrayList<Couleur> listeCouleursInitiale = new ArrayList<>();
        listeCouleursInitiale.add( new Couleur( 255, 0 , 0, 0 , "Noir absolu"));
        listeCouleursInitiale.add( new Couleur( 255, 255 , 255, 255 , "Blanc absolu"));
        return listeCouleursInitiale;
    }

}