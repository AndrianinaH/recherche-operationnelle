package Models;

/**
 * Created by Andrianina_pc on 05/02/2018.
 */
public class Sommet<T> {
    private int id;
    private T info;
    private String couleur="BLANC";
    private double entree;
    private double sortie;
    private double derive;
    private float poids = Float.POSITIVE_INFINITY;

    public Sommet(int id, T info) {
        this.id = id;
        this.info = info;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public double getEntree() {
        return entree;
    }

    public void setEntree(double entree) {
        this.entree = entree;
    }

    public double getSortie() {
        return sortie;
    }

    public void setSortie(double sortie) {
        this.sortie = sortie;
    }

    public double getDerive() {
        return derive;
    }

    public void setDerive(double derive) {
        this.derive = derive;
    }

    public float getPoids() {
        return poids;
    }

    public void setPoids(float poids) {
        this.poids = poids;
    }
}
