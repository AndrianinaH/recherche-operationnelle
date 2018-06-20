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
public class SimplexController extends BaseController{

    //---------------------- Methode de simplex

    @RequestMapping("/simplex")
    public ModelAndView index() throws Exception {
        ModelAndView model = new ModelAndView("Simplex/simplex");
        model.addObject("color", "deep-purple");
        model.addObject("title", "Programmation linéaire");
        model.addObject("titre", "Methode de simplex");
        model.addObject("simplexLink", "active");
        return model;
    }

    //----------- traitement
    @RequestMapping(value="/simplex-result", method = RequestMethod.POST)
    public ModelAndView tableau(@RequestParam("z") String z, @RequestParam("contraintes") String contraintes) throws Exception {
        // Z= 3a + 2b + 1.5c
        // 0.45a + 0.56b + 0.45c <= 33.6;0.67a + 0.34b + 0.22c <= 25.2;0.34a + 0.084b + 0c <= 10.08
        //----------- traitement
        SimplexeService simplexeService = new SimplexeService();
        ResultatSimplexe resultatSimplexe = simplexeService.simplexe(z,contraintes);


        //----------- formatage des resultats
        double[] vals = resultatSimplexe.getOptimale().getValeurs();
        String[] valeurs = this.arrondirDoubleTab(vals,1);
        String[] variables = resultatSimplexe.getOptimale().getVariables();
        double result = resultatSimplexe.getOptimale().getZ();

        //----------- afficher solutions
        ModelAndView model = new ModelAndView("Simplex/simplex-result");
        model.addObject("color", "deep-purple");
        model.addObject("title", "Programmation linéaire");
        model.addObject("titre", "Methode de simplex");
        model.addObject("simplexLink", "active");

        model.addObject("z", z);
        model.addObject("contraintes", contraintes);
        model.addObject("valeurs", valeurs);
        model.addObject("variables", variables);
        model.addObject("result", MathUtil.formatNumber(result));



        return model;
    }
}
