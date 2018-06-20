package Controllers;

import Simplex.GomoryService;
import Simplex.Math.MathUtil;
import Simplex.Result.ResultatNEGomory;
import Simplex.Result.ResultatSimplexe;
import Simplex.SimplexeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;

/**
 * Created by Andrianina_pc on 20/06/2018.
 */
@Controller
public class GomoryController extends BaseController{

    //---------------------- Methode de simplex

    @RequestMapping("/gomory")
    public ModelAndView index() throws Exception {
        ModelAndView model = new ModelAndView("Gomory/gomory");
        model.addObject("color", "blue");
        model.addObject("title", "Programmation linéaire en nombre entier");
        model.addObject("titre", "Methode coupe de gomory");
        model.addObject("gomoryLink", "active");
        return model;
    }

    //----------- traitement
    @RequestMapping(value="/gomory-result", method = RequestMethod.POST)
    public ModelAndView tableau(@RequestParam("z") String z, @RequestParam("contraintes") String contraintes) throws Exception {
        // Z= 5x + 4y
        // x + y <= 5;10x + 6y <= 45;3x + 2y <= 13

        //----------- traitement
        GomoryService gomoryService = new GomoryService();
        ResultatNEGomory resultatNEGomory = gomoryService.calculerNEGomory(z,contraintes,new ArrayList<>());

        //----------- formatage des resultats
        double[] vals = resultatNEGomory.getOptimaleEnEntier().getValeurs();
        String[] valeurs = this.arrondirDoubleTab(vals,2);
        String[] variables = resultatNEGomory.getOptimaleEnEntier().getVariables();
        double result = resultatNEGomory.getOptimaleEnEntier().getZ();

        //----------- afficher solutions
        ModelAndView model = new ModelAndView("Gomory/gomory-result");
        model.addObject("color", "red");
        model.addObject("title", "Programmation linéaire en nombre entier");
        model.addObject("titre", "Methode coupe de gomory");
        model.addObject("gomoryLink", "active");

        model.addObject("z", z);
        model.addObject("contraintes", contraintes);
        model.addObject("valeurs", valeurs);
        model.addObject("variables", variables);
        model.addObject("result", MathUtil.roundDecimal(result));



        return model;
    }
}