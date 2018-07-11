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

    public Graph() {}

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
    //------------ Successeur et predecesseur
    public List<Arc<T>> getSuccesseurs(List<Arc<T>> arcs, Sommet<T> sommet) {
        List<Arc<T>> res = new ArrayList();
        for (Arc arc : arcs) {
            if (arc.getDepart().getInfo().equals(sommet.getInfo())) {
                res.add(arc);
            }
        }
        return res;
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

    public List<Arc<T>> getPredecesseurs(List<Arc<T>> arcs, Sommet<T> sommet) {
        List<Arc<T>> res = new ArrayList();
        for (Arc arc : arcs) {
            if (arc.getArrivee().getInfo().equals(sommet.getInfo())) {
                res.add(arc);
            }
        }
        return res;
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
    public void plusCourtChemin(Sommet<T> depart, Sommet<T> arrive) throws Exception {
        Arbre<T> arbres = this.dijkstra(depart);
        if (arbres.ifExist(arrive.getInfo())) {
            T noeudVisite = arrive.getInfo();
            for (int j = 0; j < arbres.size(); j++) {
                List<Sommet<T>> noeuds = this.getSommets();
                noeuds.get(this.indiceNoeud(noeuds, noeudVisite)).setColor("214;15;15");
                System.out.println(noeuds.get(this.indiceNoeud(noeuds, noeudVisite)).getInfo());
                if (noeudVisite.equals(depart.getInfo())) {
                    break;
                }

                noeudVisite = arbres.get(noeudVisite);
            }
        } else {
            throw new Exception("Chemin Impossible");
        }
    }

    public int indiceNoeud(List<Sommet<T>> noeuds, T noeud) {
        int indice = 0;
        for (int i = 0; i < noeuds.size(); i++) {
            if (noeuds.get(i).getInfo().equals(noeud)) {
                indice = i;
                break;
            }
        }
        return indice;
    }

    public Arbre<T> dijkstra(Sommet<T> sommet){
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

    public List<Sommet<T>> decompEnNiveau() {
        List<Sommet<T>> noeudNiveau = new ArrayList();
        List<Sommet<T>> sommets = new ArrayList(this.getSommets());
        List<Arc<T>> arcs = new ArrayList(this.getArcs());
        while (!sommets.isEmpty()) {
            List<Sommet<T>> degreEntrantNonNull = this.degreEntrantNonNull(sommets, arcs);
            for (Sommet<T> noeud : degreEntrantNonNull) {
                List<Sommet> successeurs = this.getSuccesseurs(noeud);
                for (Sommet<T> successeur : successeurs) {
                    if (successeur.getNiveau() < noeud.getNiveau() + 1) {
                        successeur.setNiveau(noeud.getNiveau() + 1);
                    }
                    this.removeArc(noeud, successeur, arcs);
                }
                sommets.remove(noeud);
                noeudNiveau.add(noeud);
            }
        }
        return noeudNiveau;
    }

    //-------- coloration -------------------------
    public void coloriage() throws Exception {
        List<Sommet<T>> noeuds = new ArrayList(this.classementDegre(this).getSommets());
//        String[] couleur = new String[]{"214;15;15", "29;158;27", "27;116;158"};
        String[] couleur = new String[]{"rouge", "jaune", "vert"};
        int indiceColor = 0;
        while (!noeuds.isEmpty()) {
            noeuds.get(0).setColor(couleur[indiceColor]);
            List<Sommet<T>> voisins = this.voisinNoeud(noeuds.get(0), this);
            noeuds.remove(noeuds.get(0));
            for (Sommet noeud : noeuds) {
                if (!voisins.contains(noeud)) {
                    noeud.setColor(couleur[indiceColor]);
                    voisins.addAll(this.voisinNoeud(noeud, this));
                }
            }
            this.removeNoeudColore(noeuds);
            indiceColor++;
        }
    }

    //CLASSEMENT SOMMET PAR ORDRE DECROISSANT DE DEGRE
    public Graph classementDegre(Graph g) {
        List<Sommet<T>> noeuds = this.getSommets();
        Sommet<T> temp;
        for (int i = 0; i < noeuds.size(); i++) {
            for (int j = i; j > 0; j--) {
                if (noeuds.get(j).getDegre() > noeuds.get(j - 1).getDegre()) {
                    temp = noeuds.get(j);
                    noeuds.set(j, noeuds.get(j - 1));
                    noeuds.set(j - 1, temp);
                }
            }
        }
        return g;
    }

    //VOISIN D'UN NOEUD
    public List<Sommet<T>> voisinNoeud(Sommet noeud,Graph g) {
        List<Sommet<T>> res = new ArrayList();
        List<Arc<T>> arcs = g.getArcs();
        for (Arc<T> arc : arcs) {
            if (!(noeud.getInfo().equals(arc.getDepart().getInfo()) && noeud.getInfo().equals(arc.getArrivee().getInfo()))) {
                if (noeud.getInfo().equals(arc.getDepart().getInfo())) {
                    res.add(arc.getArrivee());
                }
                if (noeud.getInfo().equals(arc.getArrivee().getInfo())) {
                    res.add(arc.getDepart());
                }
            }
        }
        return res;
    }

    //REMOVE NOEUD COLOR
    public void removeNoeudColore(List<Sommet<T>> noeuds) {
        List<Sommet<T>> noeudDelete = new ArrayList();
        for (Sommet noeud : noeuds) {
            if (!noeud.getColor().equals("blanc")) {
                noeudDelete.add(noeud);
            }
        }
        noeuds.removeAll(noeudDelete);
    }

    public void removeArc(Sommet<T> debut, Sommet<T> fin, List<Arc<T>> arcs) {
        for (int i = 0; i < arcs.size(); i++) {
            if (arcs.get(i).getDepart().getInfo() == debut.getInfo() && arcs.get(i).getArrivee().getInfo() == fin.getInfo()) {
                arcs.remove(i);
                break;
            }
        }
    }

    public List<Sommet<T>> degreEntrantNonNull(List<Sommet<T>> noeuds, List<Arc<T>> arcs) {
        List<Sommet<T>> res = new ArrayList<>();
        List<T> listeNoeudFin = this.noeudFin(arcs);
        for (Sommet<T> noeud : noeuds) {
            if (!listeNoeudFin.contains(noeud.getInfo())) {
                res.add(noeud);
            }
        }
        return res;
    }

    public List<T> noeudFin(List<Arc<T>> arcs) {
        List<T> res = new ArrayList();
        for (Arc<T> arc : arcs) {
            res.add(arc.getArrivee().getInfo());
        }
        return res;
    }

    public Arc<T> arcExtremum(List<Arc<T>> arcs, String typeExtremum) {
        Arc<T> res = null;
        if (!arcs.isEmpty()) {
            res = arcs.get(0);
            if (typeExtremum.equals("maximum")) {
                for (Arc arc : arcs) {
                    int distanceRes = (int) (res.getDepart().getPoids() + res.getPoids());
                    int distanceArc = (int) (arc.getDepart().getPoids() + arc.getPoids());
                    if (distanceArc > distanceRes) {
                        res = arc;
                    }
                }
            } else {
                for (Arc arc : arcs) {
                    int distanceRes = (int) (res.getDepart().getPoids() - res.getPoids());
                    int distanceArc = (int) (arc.getDepart().getPoids() - arc.getPoids());
                    if (distanceArc < distanceRes) {
                        res = arc;
                    }
                }
            }
        }
        return res;
    }

    public List<Sommet<T>> getNiveau(int niveau, List<Sommet<T>> listeNoeud) {
        List<Sommet<T>> res = new ArrayList();
        for (Sommet noeud : listeNoeud) {
            if (noeud.getNiveau() == niveau) {
                res.add(noeud);
            }
        }
        return res;
    }

}
