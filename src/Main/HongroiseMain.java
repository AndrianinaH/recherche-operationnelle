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
        int[][] tab = new int[][]{
            {1,2,3,4,5},
            {1,4,2,5,3},
            {3,2,1,5,4},
            {1,2,3,5,4},
            {2,1,4,3,5}
        };
        methodeHongroise.setTableauInitiale(tab);
        methodeHongroise.afficherTableau(methodeHongroise.getTableauInitiale());

        methodeHongroise.methodeHongroise(methodeHongroise.getTableauInitiale());


    }
}
