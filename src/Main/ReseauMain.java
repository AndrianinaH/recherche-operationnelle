package Main;

import Models.Reseau;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrianina_pc on 20/03/2018.
 */
public class ReseauMain {
    public static void main(String[] args) throws Exception {
//        int[] layers = {2, 3, 2, 3};
//        double[] weights0 = {1, 0, 1, -1, 1, 1};
//        double[] weights1 = {1, 1, 1, 1, 1, 1};
//        double[] weights2 = {1, -1, 0, 1, 1, 1};
//        ArrayList<double[]> weights = new ArrayList<>();
//        weights.add(weights0);
//        weights.add(weights1);
//        weights.add(weights2);
//        Reseau<Integer> reseau = new Reseau<Integer>(layers);
//        reseau.setWeights(weights);

        //---------- generation Reseau
        String filename = "reseau.csv";
        File file = new File("D:\\workspaceJEE\\GrapheAlgorithme\\src\\Save\\"+filename);
        Reseau<Integer> reseau = new Reseau<>();
        reseau.buildReseauByFile(file);

        double seuil = 0.00001;
        double alpha = 0.8;

        List<double[]> allEntrees = new ArrayList<double[]> ();
        double[] entree1 = {1, -1};
        double[] entree2 = {1, 0};
        double[] entree3 = {1.5, 1};
        allEntrees.add(entree1);
        allEntrees.add(entree2);
        allEntrees.add(entree3);

        List<double[][]> allSortieAttendues = new ArrayList<double[][]> ();
        double[][] sortieAttendues1 = {{0.9},{0.5},{0.4}};
        double[][] sortieAttendues2 = {{0.4},{0.9},{0.6}};
        double[][] sortieAttendues3 = {{0.3},{0.7},{0.8}};
        allSortieAttendues.add(sortieAttendues1);
        allSortieAttendues.add(sortieAttendues2);
        allSortieAttendues.add(sortieAttendues3);


        //---------- apprentissage
        reseau.networkLearning(allEntrees,allSortieAttendues,alpha, seuil);

        //---------- extract CSV
        reseau.extractPoidsFinal(file);


    }

}
