package Models;

import java.util.List;

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
    private String color = "blanc";
    private int degre;
    private int niveau = 0;
    //Ordonnancement
    private int duree;
    private int dureePlusTot;
    private int dureePlusTard;
    //Position
    private int posX;
    private int posY;

    public Sommet() {}

    public Sommet(int id, T info) {
        this.id = id;
        this.info = info;
    }

    public Sommet(T x, int poids, int duree) {
        this.info = x;
        this.poids = poids;
        this.duree = duree;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getDegre() {
        return degre;
    }

    public void setDegre(int degre) {
        this.degre = degre;
    }

    public void initialize(List<Sommet<T>> noeuds) {
        for(Sommet n : noeuds){
            n.setCouleur("BLANC");
        }
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public int getDureePlusTot() {
        return dureePlusTot;
    }

    public void setDureePlusTot(int dureePlusTot) {
        this.dureePlusTot = dureePlusTot;
    }

    public int getDureePlusTard() {
        return dureePlusTard;
    }

    public void setDureePlusTard(int dureePlusTard) {
        this.dureePlusTard = dureePlusTard;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
}
