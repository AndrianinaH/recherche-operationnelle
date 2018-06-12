package Models;

/**
 * Created by Andrianina_pc on 05/02/2018.
 */
public class Arc<T> {
    private Sommet<T> depart;
    private Sommet<T> arrivee;
    private double poids;
    private int distance;

    public Arc(Sommet<T> depart, Sommet<T> arrivee) {
        this.depart = depart;
        this.arrivee = arrivee;
    }
    public Arc(Sommet<T> depart, Sommet<T> arrivee, int distance) {
        this.depart = depart;
        this.arrivee = arrivee;
        this.distance = distance;
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
}
