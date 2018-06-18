package Main;

import NordOuest.NordOuest;

/**
 * Created by Andrianina_pc on 18/06/2018.
 */
public class NordOuestMain {
    public static void main(String[] args) throws Exception {
        //-------------- initialisation
        double[] offres = {240,160,260};
        double[] demandes = {120,130,145,125,140};

        double[][] couts = new double[][]{
            {1,8,1,5,4},
            {5,5,3,6,7},
            {2,9,5,9,8}
        };
        NordOuest nordOuest = new NordOuest(3,5,offres,demandes,couts);

        //-------------- traitement
        nordOuest.run();

    }
}
