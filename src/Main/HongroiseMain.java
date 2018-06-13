package Main;

import Hongroise.Case;
import Hongroise.MethodeHongroise;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrianina_pc on 11/06/2018.
 */
public class HongroiseMain {
    public static void main(String[] args) throws Exception {

        //-------------- initialisation
        MethodeHongroise methodeHongroise = new MethodeHongroise();
//        int[][] tab = new int[][]{
//            {1,2,3,4,5},
//            {1,4,2,5,3},
//            {3,2,1,5,4},
//            {1,2,3,5,4},
//            {2,1,4,3,5}
//        };
        int[][] tab = new int[][]{
                {17,15,9,5,12},
                {16,16,10,5,10},
                {12,15,14,11,5},
                {4,8,14,17,13},
                {13,9,8,12,17}
        };
        methodeHongroise.setTableauInitiale(tab);
        methodeHongroise.afficherTableau(methodeHongroise.getTableauInitiale());

        //----------- minimisation
//        methodeHongroise.minimisationAffectation(methodeHongroise.getTableauInitiale());

        //----------- maximisation
        methodeHongroise.maximisationAffectation(methodeHongroise.getTableauInitiale());



    }
}
