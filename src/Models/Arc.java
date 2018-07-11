package Models;

/**
 * Created by Andrianina_pc on 05/02/2018.
 */
public class Arc<T> {
    private Sommet<T> depart;
    private Sommet<T> arrivee;
    private double poids;
    private int poid;
    private int distance;
    private int flot;
    private String color = "0;0;0";
    private String couleur = "BLANC";
    private int delta;

    public Arc(Sommet<T> depart, Sommet<T> arrivee) {
        this.depart = depart;
        this.arrivee = arrivee;
    }
    public Arc(Sommet<T> depart, Sommet<T> arrivee, int distance) {
        this.depart = depart;
        this.arrivee = arrivee;
        this.distance = distance;
    }
    public Arc(Sommet<T> depart, Sommet<T> arrivee, int distance, int flot) {
        this.depart = depart;
        this.arrivee = arrivee;
        this.distance = distance;
        this.flot = flot;
    }

    public Sommet<T> getDepart() {
        return depart;
    }

    public void setDepart(Sommet<T> depart) {
        this.depart = depart;
    }

    public Sommet<T> getArrivee() {
        return arrivee;
    }

    public void setArrivee(Sommet<T> arrivee) {
        this.arrivee = arrivee;
    }

    public double getPoids() {
        return poids;
    }

    public void setPoids(double poids) {
        this.poids = poids;
    }

    public int getDistance() {
    return distance;
}

    public void setDistance(int distance) {
        this.distance = distance;
    }


    public void afficher(){
        System.out.println("        Origine :" + this.depart.getInfo() +" Destination = " +this.arrivee.getInfo() );
    }

    public int getFlot() {
        return flot;
    }

    public void setFlot(int flot) {
        this.flot = flot;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public int getDelta() {
        return delta;
    }

    public void setDelta(int delta) {
        this.delta = delta;
    }

    public int getPoid() {
        return poid;
    }

    public void setPoid(int poid) {
        this.poid = poid;
    }
}
