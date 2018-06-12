package Models;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Andrianina_pc on 05/02/2018.
 */
public class Graph<T> {
    private List<Sommet<T>> sommets;
    private List<Arc<T>> arcs;
    File<Sommet<T>> file = new File<>(50);

    public Graph() {
    }

    public Graph(List<Sommet<T>> sommets, List<Arc<T>> arcs) {
        this.sommets = sommets;
        this.arcs = arcs;
    }

    public List<Sommet<T>> getSommets() {
        return sommets;
    }

    public void setSommets(List<Sommet<T>> sommets) {
        this.sommets = sommets;
    }

    public List<Arc<T>> getArcs() {
        return arcs;
    }

    public void setArcs(List<Arc<T>> arcs) {
        this.arcs = arcs;
    }

    //--------------- traitement sommet ----------------//
    //---------- ajout de sommet
    public void addSommet(Sommet<T> newSommet){
        for(Sommet<T> noeud : sommets){
            if(noeud.getId() == newSommet.getId()){
              return;
            }
        }
        sommets.add(newSommet);
    }

    //--------- supprimer sommet
    public void deleteSommets(Sommet<T> newSommet){
        for(int i=0; i< sommets.size(); i++){
            if(sommets.get(i).getId() == newSommet.getId()){
                sommets.remove(i);
                break;
            }
        }
        //------ delete arc associe
        for(int i=0; i< arcs.size(); i++){
            if(arcs.get(i).getDepart().getId() == arcs.get(i).getDepart().getId() || arcs.get(i).getArrivee().getId() == arcs.get(i).getArrivee().getId()){
                arcs.remove(i);
            }
        }
    }

    //--------------- traitement arc -----------------//
    //---------- ajout de arc
    public void addArc(Arc<T> newArc){
        for(Arc<T> arc : arcs){
            if(arc.getDepart().getId() == newArc.getDepart().getId() && arc.getArrivee().getId() == newArc.getArrivee().getId()){
                return;
            }
        }
        arcs.add(newArc);
    }

    //--------- supprimer arc
    public void deleteArc(){}
    public void deleteArc(Arc<T> newArc){
        for(int i=0; i< arcs.size(); i++){
            if(arcs.get(i).getDepart().getId() == arcs.get(i).getDepart().getId() && arcs.get(i).getArrivee().getId() == arcs.get(i).getArrivee().getId()){
                arcs.remove(i);
                return;
            }
        }
    }


    //------------ Successeur et predecesseur
    public List<Sommet> getSuccesseurs(Sommet successeur){
        List<Sommet> allSuccesseur = new ArrayList<Sommet>();
        for(Arc arc : getArcs()){
            if(arc.getDepart().getId() == successeur.getId()){
                allSuccesseur.add(arc.getArrivee());
            }
        }
        return allSuccesseur;
    }
    public List<Sommet> getPredecesseurs(Sommet predecesseur){
        List<Sommet> allPredecesseur = new ArrayList<Sommet>();
        for(Arc arc : getArcs()){
            if(arc.getArrivee().getId() == predecesseur.getId()){
                allPredecesseur.add(arc.getDepart());
            }
        }
        return allPredecesseur;
    }

    //----------- matrice
    //transformation en matrice adjacent
    public Matrice getMatrice(){
        int t = this.sommets.size();
        Matrice ret = new Matrice( (t), (t));

        List<Arc<T>> arcsTemps ;

        for(int i=0 ; i < t ;i++){
            arcsTemps = getArcsDepuisSource(this.sommets.get(i));

            for (Arc<T> arcsTemp : arcsTemps) {
                System.out.println(i + " == " + arcsTemp.getDepart().getInfo()+ "    ,       " +arcsTemp.getArrivee().getId());
                ret.setValue(i, arcsTemp.getArrivee().getId(), 1);
            }
            System.out.println("***");
        }

        return ret;
    }

    public List<Arc<T>> getArcsDepuisSource(Sommet<T> noeudSource) {
        Iterator iterator = this.arcs.iterator();
        List<Arc<T>> arcsret = new ArrayList<>();

        Arc<T> arcTemp;
        while (iterator.hasNext()) {
            arcTemp = ((Arc<T>) iterator.next());

            if (arcTemp.getDepart().equals(noeudSource)) {
                arcsret.add(arcTemp);
            }
        }

        return arcsret;
    }

    void visiterPP(Arbre<T> arbre, Sommet<T> noeud, int temp) throws Exception{
        int dv = temp++;
        noeud.setCouleur("GRIS");

        for (Sommet<T> ajdacent : ajdacent(noeud)) {
            if("BLANC".equals(ajdacent.getCouleur())){
                arbre.ajouter(ajdacent.getInfo(), noeud.getInfo());
            }
        }

        noeud.setCouleur("NOIR");

        int fv = temp++;
    }

    //--------------- afficher Arc
    public void affichageArc() {
        System.out.println("Arc **************************");
        Iterator iterator = this.arcs.iterator();
        Arc<T> arcTemp;

        while (iterator.hasNext()) {
            arcTemp = (Arc<T>) iterator.next();
            arcTemp.afficher();
        }
    }

    //--------------- adjacent d'un sommet testé
    Sommet<T>[] ajdacent(Sommet<T> noeud){
        List<Arc<T>> arcs = this.getArcsDepuisSource(noeud);
        int arcsize = arcs.size();
        Sommet<T>[] noeuds = new Sommet[arcsize];

        for(int i=0; i < arcsize;i++){
            noeuds[i] = arcs.get(i).getDepart();
        }
        return noeuds;
    }

    //------------ parcours DFS(deep)profondeur
    public void parcoursProfondeur(Sommet depart,Arbre arbre){
        if(arbre.isEmpty()){
            arbre.racine = depart;
            arbre.ajouter(depart,null);
        }
        List<Sommet> allSuccesseur = getSuccesseurs(depart);
        for(Sommet sommet : allSuccesseur){
            if(!arbre.findKey(sommet)){
                arbre.ajouter(sommet,depart);
                parcoursProfondeur(sommet,arbre);
            }
        }
    }

    //------------- Parcours en largeur BFS(bround)largeur
    public Arbre<Sommet> parcoursEnLargeur(Sommet s) {
        List<Sommet> liste = new ArrayList<Sommet>();
        List<Sommet> pere = new ArrayList<Sommet>();
        Arbre<Sommet> arbre = new Arbre<Sommet>();
        liste.add(s);
        pere.add(null);
        for(int i=0; i<liste.size(); i++) {
            if (!arbre.findKey(liste.get(i))) {
                arbre.ajouter(liste.get(i), pere.get(i));
                List<Sommet> successeur = this.getSuccesseurs(liste.get(i));
                liste.addAll(successeur);
                for (Sommet ret : successeur) {
                    pere.add(liste.get(i));
                }
            }
        }
        return arbre;
    }

    public boolean verifListe(List<Sommet> liste, Sommet s) {
        boolean ret = false;
        for (Sommet sommet : liste) {
            if (sommet.getId() == s.getId()) {
                ret = true;
                break;
            }
        }
        return ret;
    }

    // ------- chemin le plus court Dijkrista ----------------
    public Arbre<T> plusCourtChemin(Sommet<T> sommet){
        Arbre<T> arbre = new Arbre(sommet.getInfo());
        List<Sommet<T>> file_priorite = new ArrayList<Sommet<T>>();
        sommet.setPoids(0);
        file_priorite.add(sommet);
        while(file_priorite.size() > 0) {
            Sommet<T> pivot = this.getNoeudMinimum(file_priorite);
            List<Sommet> successeurs = this.getSuccesseurs(pivot);
            for(Sommet<T> successeur : successeurs) {
                if(successeur.getCouleur().equals("BLANC")) {
                    if(successeur.getPoids() == Float.POSITIVE_INFINITY) {
                        file_priorite.add(successeur);
                    }

                    int distanceArc = this.getDistanceArc(pivot, successeur);
                    if(successeur.getPoids() > pivot.getPoids() + distanceArc) {
                        successeur.setPoids((int) (pivot.getPoids() + distanceArc));
                    }
                    arbre.ajouter(successeur.getInfo(), pivot.getInfo());
                }
            }
            pivot.setCouleur("NOIR");
            file_priorite.remove(pivot);
        }
        return arbre;
    }

    public int getDistanceArc(Sommet<T> debut, Sommet<T> fin) {
        List<Arc<T>> arcs = this.getArcs();
        int res = 0;
        for(Arc<T> arc : arcs) {
            if (arc.getDepart().getInfo() == debut.getInfo() && arc.getArrivee().getInfo() == fin.getInfo()) {
                res = arc.getDistance();
                break;
            }
        }
        return res;
    }

    public Sommet<T> getNoeudMinimum(List<Sommet<T>> listeNoeud) {
        Sommet<T> res = listeNoeud.get(0);
        for(Sommet<T> noeud : listeNoeud) {
            if(noeud.getPoids() <= res.getPoids()) {
                res = noeud;
            }
        }
        return res;
    }

    // ------- chemin le plus long ----------------
    public void plusLongChemin(Arbre<Sommet> arbre) {

    }

    //-------- coloration -------------------------
    public void coloration(){

    }

    //-------- flot max ---------------------------
    public void flotMax(){

    }

    //-------- ordononcement MPM -----------------
    public void ordononcementMPM(){

    }



}
