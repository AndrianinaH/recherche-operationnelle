package Models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrianina_pc on 02/07/2018.
 */
public class OrdonnancementService<T> {

    public void ordonnancementTache(Graph g) throws Exception {
        List<Arc<T>> arcs = g.getArcs();
        this.addNoeudFin(g);
        List<Sommet<T>> niveau = g.decompEnNiveau();
        int niveauMax = niveau.get(niveau.size() - 1).getNiveau() + 1;
        this.initializePosition(niveauMax, g);
        for (int i = 1; i < niveauMax; i++) {
            List<Sommet<T>> noeudNiveau = g.getNiveau(i, niveau);
            for (Sommet<T> noeud : noeudNiveau) {
                Arc<T> arcMaximum = g.arcExtremum(g.getPredecesseurs(arcs, noeud), "maximum");
                arcMaximum.setColor("214;15;15");
                Sommet<T> n = arcMaximum.getArrivee();
                n.setPoids((int) (arcMaximum.getDepart().getPoids() + arcMaximum.getPoids()));
                n.setDureePlusTot((int) n.getPoids());
                n.setDureePlusTard((int) n.getPoids());
            }
        }
        for (int i = 0; i < niveauMax - 1; i++) {
            List<Sommet<T>> noeudNiveau = g.getNiveau(i, niveau);
            for (Sommet<T> noeud : noeudNiveau) {
                Arc<T> arcMinimum = g.arcExtremum(g.getSuccesseurs(arcs, noeud), "minimum");
                Sommet<T> n = arcMinimum.getDepart();
                n.setDureePlusTard((int) (arcMinimum.getArrivee().getPoids() - arcMinimum.getPoids()));
            }
        }
    }

    public Graph<T> makeGraphe(List<Ordonnancement> ordonnancements) throws Exception {
        List<Arc<T>> arcs = new ArrayList();
        List<Sommet<T>> noeuds = new ArrayList();
        for (Ordonnancement ord : ordonnancements) {
            noeuds.add(new Sommet(ord.getTache(), 0, ord.getDuree()));
        }
        this.verifyTask(ordonnancements, noeuds);
        for (Sommet noeud : noeuds) {
            List<Arc<T>> listeArc = this.makeArc(noeud, noeuds, ordonnancements);
            if (!listeArc.isEmpty()) {
                arcs.addAll(listeArc);
            }
        }
        return new Graph(noeuds, arcs);
    }

    private List<Arc<T>> makeArc(Sommet<T> noeud, List<Sommet<T>> noeuds, List<Ordonnancement> ord) {
        List<Arc<T>> arcs = new ArrayList();
        for (Ordonnancement o : ord) {
            if (noeud.getInfo().equals(o.getTache()) && !o.getTacheAnterieur().isEmpty()) {
                String[] tacheAnterieurs = o.getTacheAnterieurs();
                for (String t : tacheAnterieurs) {
                    for (Sommet n : noeuds) {
                        if (n.getInfo().equals(t)) {
                            arcs.add(new Arc(n, noeud, n.getDuree()));
                        }
                    }
                }
                break;
            }
        }
        return arcs;
    }

    private boolean verifyTask(List<Ordonnancement> ordonnancements, List<Sommet<T>> noeuds) throws Exception {
        boolean result = true;
        for (Ordonnancement ord : ordonnancements) {
            if (!ord.getTacheAnterieur().isEmpty()) {
                String[] task = ord.getTacheAnterieurs();
                for (String t : task) {
                    if (!ifTaskExist(t, noeuds)) {
                        throw new Exception("Inexistance d'une tâche antérieure");
                    }

                }
            }
        }
        return result;
    }

    private boolean ifTaskExist(String task, List<Sommet<T>> noeuds) {
        boolean result = false;
        for (Sommet n : noeuds) {
            if (n.getInfo().equals(task)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public void addNoeudFin(Graph g) {
        List<Sommet<T>> noeuds = g.getSommets();
        List<Arc<T>> arcs = g.getArcs();
        List<Sommet<T>> noeudDecompose = g.decompEnNiveau();
        int niveauMax = noeudDecompose.get(noeudDecompose.size() - 1).getNiveau();
        Sommet<T> noeudFin = new Sommet("FIN", 0, 0);
        noeudFin.setNiveau(niveauMax + 1);
        noeudFin.setPosX(50 + (noeudFin.getNiveau() * 200));
        noeudFin.setPosY(150);
        noeuds.add(noeudFin);
        for (Sommet noeud : noeuds) {
            if (noeud.getNiveau() == niveauMax) {
                arcs.add(new Arc(noeud, noeudFin, noeud.getDuree()));
            }
        }
    }

    public void initializePosition(int niveauMax, Graph g) {
        List<Sommet<T>> noeuds = g.getSommets();
        for (int i = 0; i < niveauMax + 1; i++) {
            int k = 50;
            for (Sommet n : noeuds) {
                if (n.getNiveau() == i) {
                    n.setPosX(50 + (i * 200));
                    n.setPosY(k);
                    k += 100;
                }
            }
        }
    }
}
