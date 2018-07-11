package Models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrianina_pc on 02/07/2018.
 */
public class FlotMaxService<T> {

    public void searchFlotMax(Graph g, Sommet<T> source, Sommet<T> puit) throws Exception {
        Arbre<T> arbre;
        Sommet<T> n = new Sommet();
        List<Sommet<T>> file = new ArrayList();
        file.add(source);
        while (true) {
            arbre = new Arbre(source.getInfo());
            file.clear();
            file.add(source);
            n.initialize(g.getSommets());

            while (!file.isEmpty()) {
                List<Arc<T>> successeurs = g.getSuccesseurs(g.getArcs(), file.get(0));
                if (isSuccesseurValid(successeurs)) {
                    for (Arc successeur : successeurs) {
                        if (successeur.getArrivee().getCouleur().equals("BLANC") && !successeur.getCouleur().equals("ROUGE")) {
                            successeur.getArrivee().setCouleur("NOIR");
                            successeur.setDelta(successeur.getPoid() - successeur.getFlot());
                            file.add(successeur.getArrivee());
                            arbre.ajouter((T) successeur.getArrivee().getInfo(), file.get(0).getInfo());

                            if (successeur.getArrivee().getInfo().equals(puit.getInfo())) {
                                break;
                            }
                        }
                    }
                } else {
                    List<Arc<T>> predecesseurs = g.getPredecesseurs(g.getArcs(), file.get(0));
                    for (Arc predecesseur : predecesseurs) {
                        Sommet<T> debutArc = predecesseur.getDepart();
                        Sommet<T> finArc = predecesseur.getArrivee();
                        if (debutArc.getCouleur().equals("BLANC") && 0 < predecesseur.getFlot() && !debutArc.getInfo().equals(source.getInfo()) && !finArc.getInfo().equals(puit.getInfo())) {
                            predecesseur.getArrivee().setCouleur("NOIR");
                            predecesseur.setDelta(-((predecesseur.getPoid() - predecesseur.getFlot()) + predecesseur.getPoid()));

                            Arc<T> arc = new Arc(predecesseur.getArrivee(), predecesseur.getDepart());
                            arc.setPoids(predecesseur.getPoids());
                            arc.setDelta(predecesseur.getDelta());

                            g.getArcs().add(arc);

                            file.add(arc.getArrivee());
                            arbre.ajouter((T) arc.getArrivee().getInfo(), file.get(0).getInfo());
                        }
                    }
                }
                file.remove(file.get(0));
            }

            if (!arbre.containsKey(puit.getInfo())) {
                break;
            }

            arbre.afficher();

            int flotMinimal = this.flotMinimum(arbre, g, puit.getInfo());
            this.updateFlot(arbre, g, puit.getInfo(), flotMinimal);

        }
    }

    public int flotMinimum(Arbre<T> arbre, Graph<T> g, T puit) {
        T visite = puit;
        List<Integer> poids = new ArrayList();
        while (true) {
            if (arbre.get(visite) == null) {
                break;
            }
            Arc<T> arc = this.getArc(g.getArcs(), arbre.get(visite), visite);
            poids.add(Math.abs(arc.getDelta()));
            visite = arbre.get(visite);
        }
        return this.extremumNumber(poids, "minimum");
    }

    public void updateFlot(Arbre<T> arbre, Graph<T> g, T puit, int flotMinimal) {
        T visite = puit;
        while (true) {
            if (arbre.get(visite) == null) {
                break;
            }
            Arc<T> arc = this.getArc(g.getArcs(), arbre.get(visite), visite);
            if (arc.getDelta() < 0) {
                int flot = arc.getFlot() - flotMinimal;
                arc = this.getArc(g.getArcs(), arbre.get(visite), visite);
                arc.setFlot(flot);
            } else {
                arc.setFlot(flotMinimal + arc.getFlot());
            }

            if (arc.getFlot() == arc.getPoids()) {
                arc.setCouleur("ROUGE");
            }
            visite = arbre.get(visite);
        }

    }

    public boolean isSuccesseurValid(List<Arc<T>> arcs) {
        boolean result = false;
        for (Arc arc : arcs) {
            if (arc.getArrivee().getCouleur().equals("BLANC") && !arc.getCouleur().equals("ROUGE")) {
                result = true;
                break;
            }

        }
        return result;
    }

    private Arc<T> getArc(List<Arc<T>> arcs, T debut, T fin) {
        Arc<T> result = null;
        for (Arc arc : arcs) {
            if (debut.equals(arc.getDepart().getInfo()) && fin.equals(arc.getArrivee().getInfo())) {
                result = arc;
                break;
            }
        }
        return result;
    }

    public static int extremumNumber(List<Integer> numbers, String typeExtremum) {
        int extremum = numbers.get(0);
        if (typeExtremum.equals("maximum")) {
            for (int number : numbers) {
                if (number > extremum) {
                    extremum = number;
                }
            }
        } else {
            for (int number : numbers) {
                if (number < extremum) {
                    extremum = number;
                }
            }
        }
        return extremum;
    }
}
