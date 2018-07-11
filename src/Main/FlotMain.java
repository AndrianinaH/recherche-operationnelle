package Main;

import Models.Arc;
import Models.FlotMaxService;
import Models.Graph;
import Models.Sommet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrianina_pc on 03/07/2018.
 */
public class FlotMain {
    public static void main(String[] args) throws Exception {
        //FLOT MAX
        List<Sommet<String>> noeuds = new ArrayList();
        noeuds.add(new Sommet(0,"A"));//0
        noeuds.add(new Sommet(1,"B"));//1
        noeuds.add(new Sommet(2,"C"));//2
        noeuds.add(new Sommet(3,"D"));//3
        List<Arc<String>> arcs = new ArrayList();
        arcs.add(new Arc(noeuds.get(0), noeuds.get(1), 3));
        arcs.add(new Arc(noeuds.get(0), noeuds.get(2), 3));
        arcs.add(new Arc(noeuds.get(1), noeuds.get(2), 2));
        arcs.add(new Arc(noeuds.get(1), noeuds.get(3), 2));
        arcs.add(new Arc(noeuds.get(2), noeuds.get(3), 3));

        Graph g = new Graph(noeuds, arcs);
        FlotMaxService flotService = new FlotMaxService();
        flotService.searchFlotMax(g, noeuds.get(0), noeuds.get(3));

        //LES FLOTS SORTANT DU SOURCE DOIVENT ETRE EGALE AU FLOT ENTRANT DU PUIT
        //LES FLOTS SORTANT DU SOURCE, ICI LA SOURCE EST A
        System.out.println("---SOURCE---");
        for (int i = 0; i<g.getArcs().size(); i++) {
            Arc a = (Arc) g.getArcs().get(i);
            if (a.getDepart().getInfo().equals("A")) {
                System.out.println(a.getFlot() + "/" + a.getPoids());
            }
        }

        //LES FLOTS ENTRANT DU PUIT, ICI LA PUIT EST D
        System.out.println("---PUIT---");
        for (int i = 0; i<g.getArcs().size(); i++) {
            Arc a = (Arc) g.getArcs().get(i);
            if (a.getArrivee().getInfo().equals("D")) {
                System.out.println(a.getFlot() + "/" + a.getPoid());
            }
        }
    }
}
