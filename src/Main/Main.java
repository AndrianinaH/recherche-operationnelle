package Main;

import Models.*;

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

        graph.addArc(new Arc<String>(sommetA,sommetC,2));
        graph.addArc(new Arc<String>(sommetC,sommetB,4));
        graph.addArc(new Arc<String>(sommetB,sommetG,1));
        graph.addArc(new Arc<String>(sommetB,sommetD,5));
        graph.addArc(new Arc<String>(sommetD,sommetE,6));
        graph.addArc(new Arc<String>(sommetD,sommetG,6));
        graph.addArc(new Arc<String>(sommetD,sommetF,4));
        graph.addArc(new Arc<String>(sommetE,sommetF,3));


        Arbre<Sommet> profondeur = new Arbre<Sommet>();

//        graph.parcoursProfondeur(sommetA,profondeur);
//        profondeur.afficher();
//        System.out.println("fin Parcours en profondeur");
//        System.out.println("--------x---------");

        graph.affichageArc();
        //------------- plus court chemin
        graph.plusCourtChemin(sommetA, sommetE);
        System.out.println("fin get Arc depuis source");
        System.out.println("--------x---------");

        //------------- flot max
//        FlotMaxService<String> service = new FlotMaxService();
//        service.searchFlotMax(new Graph(sommets, arcs), sommets.get(0), sommets.get(6));
//        System.out.println("fin flot max");
//        System.out.println("--------x---------");




    }
}
