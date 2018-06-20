package Main;

import Simplex.GomoryService;
import Simplex.Result.ResultatNEGomory;
import Simplex.Result.ResultatSimplexe;
import Simplex.SimplexeService;

import java.util.ArrayList;

/**
 * Created by Andrianina_pc on 20/06/2018.
 */
public class SimplexMain {
    public static void main(String[] args) throws Exception {

        //-------------- initialisation
        String contraintes = "x + y <= 5;10x + 6y <= 45";
        String z = "Z= 5x + 4y";

        SimplexeService simplexeService = new SimplexeService();
        ResultatSimplexe resultatSimplexe = simplexeService.simplexe(z,contraintes);

        GomoryService gomoryService = new GomoryService();
        ResultatNEGomory resultatNEGomory = gomoryService.calculerNEGomory(z,contraintes,new ArrayList<>());

        System.out.println("wawawa");

    }
}
