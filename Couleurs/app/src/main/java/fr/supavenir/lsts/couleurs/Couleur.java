package fr.supavenir.lsts.couleurs;

public class Couleur {

    int a;
    int r;
    int v;
    int b;
    String nom;

    public Couleur(int a, int r, int v, int b, String nom) {
        this.a = a;
        this.r = r;
        this.v = v;
        this.b = b;
        this.nom = nom;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getV() {
        return v;
    }

    public void setV(int v) {
        this.v = v;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
