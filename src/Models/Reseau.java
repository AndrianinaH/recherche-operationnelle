package Models;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tahinaR on 26/02/2018.
 */
public class Reseau<T> {

    private int[] layers;
    private List<List<Sommet<T>>> sommets;
    private List<List<Arc<T>>> arcs;
    private ArrayList<double[]> weights;
    private int nbSuccessIteration = 0;

    public Reseau(){}

    public Reseau(int[] element) {
        buildReseau(element);
    }

    public void buildReseau(int[] element){
        setLayers(element);
        setSommets(buildSommets(element));
        setArcs(buildArcs(sommets));
    }
    // ----------------- getters && setters --------------------------------

    public int[] getLayers() {
        return layers;
    }

    public void setLayers(int[] layers) {
        this.layers = layers;
    }


    public List<List<Sommet<T>>> getSommets() {
        return sommets;
    }

    public void setSommets(List<List<Sommet<T>>> sommets) {
        this.sommets = sommets;
    }

    public List<List<Arc<T>>> getArcs() {
        return arcs;
    }

    public void setArcs(List<List<Arc<T>>> arcs) {
        this.arcs = arcs;
    }

    public ArrayList<double[]> getWeights() {
        return weights;
    }

    public void setWeights(ArrayList<double[]> weights) throws Exception {
        boolean b = true;
        if (weights.size() == layers.length - 1) {
            for (int i=0; i<weights.size(); i++) {
                if (weights.get(i).length != (layers[i] * layers[i+1])) {
                    b = false;
                    break;
                }
            }
        }
        if (b) {
            this.weights = weights;
            setWeights();
        } else {
            throw new Exception("Unexpected array of weights");
        }
    }

    // ----------------- Construction du reseau -------------------------------//

    public void setWeights() {
        for(int i=0; i<arcs.size() ; i++) {
            for(int j=0; j<arcs.get(i).size() ; j++) {
                arcs.get(i).get(j).setPoids(weights.get(i)[j]);
            }
        }
    }

    //------------- recupere la liste de tout les sommets
    public List reverseBuildListSommets(List<List<Sommet<T>>> liste) {
        List ret = new ArrayList();
        for(List<Sommet<T>> elemList : liste) {
            for (Sommet<T> s : elemList) {
                ret.add(s);
            }
        }
        return ret;
    }

    //------------- recupere la liste de tout les arcs
    public List reverseBuildListArcs(List<List<Arc<T>>> liste) {
        List ret = new ArrayList();
        for(List<Arc<T>> elemList : liste) {
            for (Arc<T> a : elemList) {
                ret.add(a);
            }
        }
        return ret;
    }

    //------------- construit les couches reseaux sommets
    public List<List<Sommet<T>>> buildSommets(int[] element) {
        List<List<Sommet<T>>> ret = new ArrayList<List<Sommet<T>>>();
        int ind = 0;
        for(int el : element) {
            List<Sommet<T>> liste = new ArrayList<Sommet<T>>();
            for(int j=0; j<el ; j++) {
                Sommet<T> sommet = new Sommet<T>(ind, (T) new Object());
                ind++;
                liste.add(sommet);
            }
            ret.add(liste);
        }
        return ret;
    }

    //------------- construit les couches reseaux arcs
    public List<List<Arc<T>>> buildArcs(List<List<Sommet<T>>> sommets) {
        List<List<Arc<T>>> ret = new ArrayList<List<Arc<T>>>();
        for (int i=1; i<sommets.size(); i++ ) {
            List<Arc<T>> listeArcs = buildArcPerCouche(sommets.get(i-1), sommets.get(i));
            ret.add(listeArcs);
        }
        return ret;
    }

    //------------- construit le couple depart/arrivee pour un arc
    public List<Arc<T>> buildArcPerCouche(List<Sommet<T>> listeSommets1, List<Sommet<T>> listeSommets2) {
        List<Arc<T>> ret = new ArrayList<Arc<T>>();
        for (Sommet sommet1 : listeSommets1) {
            for (Sommet sommet2 : listeSommets2) {
                Arc<T> arc = new Arc<T>(sommet1, sommet2);
                ret.add(arc);
            }
        }
        return ret;
    }

    // ----------------- Traitements -------------------------------//

    //------------ traitement propagation en avant

    public double getSortie(double entree) {
        double res = 1 / (1 + Math.exp(-entree));
        return res;
    }

    public static double [][] produitMatriciel(double [][] m1, double [][] m2) throws Exception {
        int l1 = m1.length;
        int c1 = m1[0].length;
        int l2 = m2.length;
        int c2 = m2[0].length;
        double[][] res = new double[l1][c2];
        if (c1 != l2) throw new Exception("Illegal matrix dimensions.");
        for (int i = 0; i < l1; i++) {
            for (int j = 0; j < c2; j++) {
                for (int k = 0; k < l2; k++) {
                    res[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return res;
    }

    public double[][] getWeightsMatrix(int layer) throws Exception {
        if (layer < 1 || layer >= layers.length) throw new Exception("Illegal layer number.");
        double[][] res = new double[layers[layer]][layers[layer-1]];
        for(int i=0; i<res.length ; i++) {
            double[] value = getWeightPerEntry(sommets.get(layer).get(i), layer);
            res[i] = value;
        }
        return res;
    }

    public double[] getWeightPerEntry(Sommet<T> sommet, int layer) {
        double[] ret = new double[layers[layer-1]];
        int ind = 0;
        for (Arc arc : arcs.get(layer-1)) {
            if (arc.getArrivee().getId() == sommet.getId()) {
                ret[ind] = arc.getPoids();
                ind++;
            }
        }
        return ret;
    }

    public double[][] propagationEnAvant(double[] inputGiven) throws Exception {
        if (inputGiven.length != layers[0]) throw new Exception("Illegal array of inputs");
        double[][] input = getEntree(inputGiven);
        setEntree(input);
        for(int i=1; i<layers.length ; i++) {
            double[][] weights = getWeightsMatrix(i);
            double[][] expectedInputs = produitMatriciel(weights, input);
            input = getSortie(expectedInputs);
            setSortieSommets(i, input);
        }
        return input;
    }

    public double[][] getSortie(double[][] input) {
        double[][] res = new double[input.length][1];
        for(int i=0; i<input.length; i++) {
            res[i][0] = getSortie(input[i][0]);
        }
        return res;
    }

    public double[][] getEntree(double[] input) {
        double[][] ret = new double[input.length][1];
        for (int i=0; i<input.length; i++) {
            ret[i][0] = input[i];
        }
        return ret;
    }

    public void setSortieSommets(int layer, double[][] output) {
        for(int i=0; i<getSommets().get(layer).size(); i++) {
            getSommets().get(layer).get(i).setSortie(output[i][0]);
        }
    }

    public void setEntree(double[][] input) {
        for(int i=0; i<getSommets().get(0).size(); i++) {
            getSommets().get(0).get(i).setSortie(input[i][0]);
        }
    }

    //------------ traitement retropropagation

    public double calculErreur(double [][] sortie, double [][] sortieAttendu){
        double res = 0;
        for(int i = 0; i< sortie.length; i++){
            res = Math.pow((sortie[i][0]-sortieAttendu[i][0]),2);
        }
        return res * 1/2;
    }

    public double deriveCoucheFin(Sommet<T> sommet, double sortieAttendu) {
        double res = sommet.getSortie() * (1 - sommet.getSortie()) * (sortieAttendu - sommet.getSortie()) ;
        return res;
    }

    public double deriveCoucheCache(Sommet<T> sommet, int layer) {
        double res = sommet.getSortie() * (1 - sommet.getSortie());
        double temp = 0;
        for (Arc arc : getArcs().get(layer)) {
            if (arc.getDepart().getId() == sommet.getId()) {
                temp += (arc.getPoids() * arc.getArrivee().getSortie());
            }
        }
        return res * temp;
    }

    public double correctWeight(double weight, double alpha, Arc<T> arc) {
        double res = weight + (alpha * arc.getDepart().getSortie() * arc.getArrivee().getDerive());
        return res;
    }

    public void retropropagation(double[][] sortieAttendu, double alpha) {
        for(int i = layers.length -1; i>= 0 ; i--) {
            List<Sommet<T>> sommets = getSommets().get(i);
            for (int j=0; j<sommets.size(); j++) {
                if (i == layers.length -1) {
                    sommets.get(j).setDerive(deriveCoucheFin(sommets.get(j), sortieAttendu[j][0]));
                } else {
                    sommets.get(j).setDerive(deriveCoucheCache(sommets.get(j), i));
                    for (Arc arc : getArcs().get(i)) {
                        if (arc.getDepart().getId() == sommets.get(j).getId()) {
                            arc.setPoids(correctWeight(arc.getPoids(), alpha, arc));
                        }
                    }
                }
            }
        }
    }

    //-------------------- affichage service --------------------//
    //----------------- lecture ecriture fichier ------------------//
    public List<String> readFile(File file) throws IOException
    {
        return FileUtils.readLines(file);
    }
    public void writeFileTxt(File file,List<List<String>> allLists) throws Exception
    {
        FileWriter fileW=new FileWriter(file);
        BufferedWriter buffW=null;
        try{
            buffW=new BufferedWriter(fileW);

            //----------- write layer
            buffW.append("couches = ");
            for(int j=0; j<this.layers.length; j++){
                buffW.append(String.valueOf(layers[j]));
                if(j!=this.layers.length-1) buffW.append(",");

            }
            buffW.newLine();
            buffW.newLine();

            //----------- write weight
            for(int i = 0; i<allLists.size(); i++)
            {
                buffW.append("poids" + i + " = ");
                int index= 0;
                for (String poids: allLists.get(i)) {
                    buffW.append(poids);
                    if(index!= allLists.get(i).size()-1) buffW.append(",");
                    index++;
                }

                buffW.newLine();
            }
            System.out.println("entrain d'ecrire TXT");
        }catch (Exception e){
            throw e;
        }finally {
            if(buffW != null){
                buffW.close();
            }
        }
    }

    public void writeFileCSV(File file,List<List<String>> allLists) throws Exception
    {
        FileWriter fileW=new FileWriter(file);
        BufferedWriter buffW=null;
        try{
            buffW=new BufferedWriter(fileW);

            //----------- write layer
            buffW.append("couches;");
            for(int j=0; j<this.layers.length; j++){
                buffW.append(String.valueOf(layers[j])+";");
            }
            buffW.newLine();
            buffW.newLine();

            //----------- write weight
            for(int i = 0; i<allLists.size(); i++)
            {
                buffW.append("poids" + i + ";");
                for (String poids: allLists.get(i)) {
                    buffW.append(poids + ";");
                }

                buffW.newLine();
            }
            System.out.println("entrain d'ecrire CSV");
        }catch (Exception e){
            throw e;
        }finally {
            if(buffW != null){
                buffW.close();
            }
        }
    }

    //-------------- extract poids final
    public void extractPoidsFinal(File file) throws Exception {
        List<List<String>> allLists = new ArrayList<List<String>>();
        for (List<Arc<T>> list_arc : this.arcs) {
            allLists.add(listDoubleToListString(list_arc));
        }
        writeFileCSV(file, allLists);
    }

    //-------------- ListDoubleToListString
    public List<String> listDoubleToListString(List<Arc<T>> list_arc){
        List<String> ret =new ArrayList<String>();
        for (Arc arc : list_arc){
            ret.add(String.valueOf(arc.getPoids()));
        }
        return ret;
    }

    //-------------- build reseau by filereader
    public void buildReseauByFile(File file) throws Exception {

        List<String> allConfig = getDataByFile(file);

        int[] newLayers = getLayerByFile(allConfig.get(0));
        ArrayList<double[]> newWeights = getWeightByFile(allConfig);
        this.buildReseau(newLayers);
        this.setWeights(newWeights);
    }

    //------------- getData By File
    public List<String> getDataByFile(File file) throws Exception {
        List<String> allConfig = readFile(file);
        List<String> confFinal = new ArrayList<String>();
        for (String conf: allConfig) {
            if(!conf.isEmpty()) confFinal.add(conf);
        }
        return confFinal;
    }

    //--------------- get Layer by file
    public int[] getLayerByFile(String line){
        String[] couches = line.split(";");
        int[] newLayers = new int[couches.length -1];
        for (int i=0; i< couches.length-1; i++){
            newLayers[i] = Integer.valueOf(couches[i+1]);
        }
        return newLayers;
    }

    //--------------- get double[] by file
    public double[] getDoubleArrayByFile(String line){
        String[] datas = line.split(";");
        double[] newDatas = new double[datas.length -1];
        for (int i=0; i< datas.length-1; i++){
            newDatas[i] = Double.valueOf(datas[i+1]);
        }
        return newDatas;
    }

    //--------------- get weight by file
    public ArrayList<double[]> getWeightByFile(List<String> allConfig){

        ArrayList<double[]> ret = new ArrayList<double[]>();
        for (int i=1; i<allConfig.size(); i++){
            double[] newWeight = getDoubleArrayByFile(allConfig.get(i));
            ret.add(newWeight);
        }
        return ret;
    }


    //-------------- format data
    public double[] convertTab(double[][] tab) {
        double[] res = new double[tab.length];
        for(int i=0; i<tab.length; i++) {
            res[i] = tab[i][0];
        }
        return res;
    }
    public void displayTab(double[] tab) {
        System.out.print("| ");
        System.out.print(tab[0]);
        for(int i = 1; i<tab.length; i++) {
            System.out.print(" //// ");
            System.out.print(tab[i]);
        }
        System.out.print(" |");
        System.out.println();
    }

    public void afficherReseau(Reseau reseau,double[] entree,double[][] sortieAttendue,double[][] propAvant,double erreur, int i){
        System.out.println("entree "+i);
        displayTab(entree);
        System.out.println("sortie attendue "+i);
        displayTab(convertTab(sortieAttendue));
        System.out.println("propagation en avant indice "+i);
        displayTab(convertTab(propAvant));
        System.out.println("Erreur" + i + "= " +erreur);
        System.out.println();

    }
    //----------------- machine learning ----------------//
    //------------ all learning
    public void networkLearning(List<double[]> allEntrees, List<double[][]> allSortieAttendues,double alpha, double seuil) throws Exception {
        int maxInd = allEntrees.size() - 1;
        int i = 0;
        for (int nbrLoop = 0; nbrLoop < 5000; nbrLoop++){
            oneLearning(allEntrees.get(i), allSortieAttendues.get(i),alpha,seuil,i);
            if (nbSuccessIteration == maxInd + 1) {
                break;
            }
            if (i == maxInd) {
                i = 0;
            } else {
                i++;
            }
        }
    }

    //------------ 1 apprentissage
    public void oneLearning(double[] entrees, double[][] sortieAttendues, double alpha, double seuil, int i) throws Exception {
        double[][] sorties = propagationEnAvant(entrees);
        double erreur = calculErreur(sorties,sortieAttendues);
        afficherReseau(this,entrees,sortieAttendues,sorties,erreur,i);
        if (erreur > seuil) {
            this.retropropagation(sortieAttendues, alpha);
            nbSuccessIteration = 0;
        } else {
            System.out.println("success");
            nbSuccessIteration++;
        }
    }





}
