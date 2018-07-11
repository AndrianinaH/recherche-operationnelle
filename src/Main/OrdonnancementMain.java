package Main;

import Models.Arc;
import Models.Graph;
import Models.OrdonnancementService;
import Models.Sommet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrianina_pc on 03/07/2018.
 */
public class OrdonnancementMain {
    public static void main(String[] args) throws Exception {
        //ORDONNANCEMENT
        List<Sommet<String>> noeuds = new ArrayList();
        noeuds.add(new Sommet("A", 0, 2));//0
        noeuds.add(new Sommet("B", 0, 4));//1
        noeuds.add(new Sommet("C", 0, 4));//2
        noeuds.add(new Sommet("D", 0, 5));//3
        noeuds.add(new Sommet("E", 0, 6));//4

        List<Arc<String>> arcs = new ArrayList();
        arcs.add(new Arc(noeuds.get(0), noeuds.get(2), 2));
        arcs.add(new Arc(noeuds.get(0), noeuds.get(3), 2));
        arcs.add(new Arc(noeuds.get(1), noeuds.get(3), 4));
        arcs.add(new Arc(noeuds.get(3), noeuds.get(4), 5));
        arcs.add(new Arc(noeuds.get(2), noeuds.get(4), 4));

        Graph<String> g = new Graph(noeuds, arcs);

        OrdonnancementService service = new OrdonnancementService();
        service.ordonnancementTache(g);

        for (Sommet n : g.getSommets()) {
            System.out.println("Noeud " + n.getInfo() + " - Niveau " + n.getNiveau() + " - Durée Plus Tôt " + n.getDureePlusTot() + " - Durée Plus Tard " + n.getDureePlusTard());
        }

    }
}
