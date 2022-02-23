package fr.supavenir.lsts.couleurs;

import java.util.ArrayList;

public class ModeleListeCouleurs {

    private ArrayList<Couleur> lesCouleurs;

    public  ModeleListeCouleurs() {
        lesCouleurs = new ArrayList<Couleur>();
    }

    public ArrayList<Couleur> getLesCouleurs() {
        return lesCouleurs;
    }

    public void setLesCouleurs( ArrayList<Couleur> lesCouleurs ) {
        this.lesCouleurs = lesCouleurs;
    }

    public void ajouterCouleur( Couleur couleur ) {
        lesCouleurs.add( couleur );
    }

    public void retirerCouleurEnPosition( int position )
    { lesCouleurs.remove( position ); }

    public void modifierCouleur( int position , Couleur couleur )
    { lesCouleurs.set( position , couleur ); }
}
