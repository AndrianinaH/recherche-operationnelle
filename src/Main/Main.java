package Main;

import Models.Arbre;
import Models.Arc;
import Models.Graph;
import Models.Sommet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrianina_pc on 05/02/2018.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        List<Sommet<String>> sommets = new ArrayList<Sommet<String>>();
        List<Arc<String>> arcs = new ArrayList<Arc<String>>();

        Graph<String> graph = new Graph<>(sommets,arcs);

        Sommet<String> sommetA = new Sommet<String>(0,"a");
        Sommet<String> sommetB = new Sommet<String>(1,"b");
        Sommet<String> sommetC = new Sommet<String>(2,"c");
        Sommet<String> sommetD = new Sommet<String>(3,"d");
        Sommet<String> sommetE = new Sommet<String>(4,"e");
        Sommet<String> sommetF = new Sommet<String>(5,"f");
        Sommet<String> sommetG = new Sommet<String>(6,"g");

        graph.addSommet(sommetA);
        graph.addSommet(sommetB);
        graph.addSommet(sommetC);
        graph.addSommet(sommetD);
        graph.addSommet(sommetE);
        graph.addSommet(sommetF);
        graph.addSommet(sommetG);

        graph.addArc(new Arc<String>(sommetA,sommetB,2));
        graph.addArc(new Arc<String>(sommetB,sommetD,4));
        graph.addArc(new Arc<String>(sommetD,sommetC,1));
        graph.addArc(new Arc<String>(sommetC,sommetE,5));
        graph.addArc(new Arc<String>(sommetE,sommetF,6));
        graph.addArc(new Arc<String>(sommetF,sommetG,6));
        graph.addArc(new Arc<String>(sommetA,sommetG,4));


        Arbre<Sommet> arbre = new Arbre<Sommet>();

        graph.parcoursProfondeur(sommetA,arbre);
        System.out.println("fin Parcours en profondeur");

       Arbre<String> plusCourtChemin =  graph.plusCourtChemin(sommetA);
        System.out.println("fin get Arc depuis source");



    }
}
