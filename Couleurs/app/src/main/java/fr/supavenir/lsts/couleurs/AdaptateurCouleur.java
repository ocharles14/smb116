package fr.supavenir.lsts.couleurs;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

/**
 *  L'adaptateur pour visualiser le modèle : une liste d'objets Couleur
 */

// TODO : Mettre en place un cache dans la methode getView()

public class AdaptateurCouleur extends BaseAdapter {

    private Context context;
    private ModeleListeCouleurs modele = new ModeleListeCouleurs();

    public AdaptateurCouleur( Context context , ArrayList<Couleur> couleurs ) {
        this.context = context;
        modele.setLesCouleurs( couleurs );
    }

    public void ajouterCouleur( Couleur couleur ) {
        modele.ajouterCouleur( couleur );
    }

    public void supprimerCouleur( int position ) {
        modele.retirerCouleurEnPosition( position );
    }

    public void changerCouleur( int position , Couleur  couleur ) {
        modele.modifierCouleur( position , couleur );
    }

    /** On adapte les methodes pour visualiser le modèle en mémoire. */

    @Override
    public int getCount() {
        return modele.getLesCouleurs().size();
    }

    @Override
    public Object getItem(int position) {
        return modele.getLesCouleurs().get( position );
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Important : le code pour recuperer la vue de l'item par son layout
        View itemView = LayoutInflater.from( context ).inflate(R.layout.liste_couleur_item,
                parent , false );

        TextView tvCouleur = itemView.findViewById( R.id.tvCouleur);
        TextView tvNomCouleur = itemView.findViewById( R.id.tvNomCouleur );

        int a = modele.getLesCouleurs().get( position ).getA();
        int r = modele.getLesCouleurs().get( position ).getR();
        int v = modele.getLesCouleurs().get( position ).getV();
        int b = modele.getLesCouleurs().get( position ).getB();

        tvCouleur.setBackgroundColor(Color.argb( a , r, v ,b ));
        tvNomCouleur.setText( ((Couleur)getItem( position )).getNom() );
        //itemView.setBackgroundColor(  Color.argb(255, 200,200,200));
        return itemView;
    }
}
