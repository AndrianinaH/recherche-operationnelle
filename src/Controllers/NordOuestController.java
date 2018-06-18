package Controllers;

import NordOuest.NordOuest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Created by Andrianina_pc on 15/06/2018.
 */

@Controller
public class NordOuestController extends BaseController {

    //---------------------- Methode coin nord-ouest

    @RequestMapping("/nord-ouest")
    public ModelAndView index() throws Exception {
        ModelAndView model = new ModelAndView("NordOuest/nord-ouest");
        model.addObject("color", "cyan");
        model.addObject("title", "Recherche opérationnelle");
        model.addObject("titre", "Methode du coin Nord-Ouest");
        model.addObject("noLink", "active");
        return model;
    }

    //----------- recuperation du init
    @RequestMapping(value="/nord-ouest-init", method = RequestMethod.POST)
    public ModelAndView init(@RequestParam("ligne") int ligne,
                             @RequestParam("colonne") int colonne) throws Exception {
        ModelAndView model = new ModelAndView("NordOuest/nord-ouest-tableau");
        model.addObject("color", "cyan");
        model.addObject("title", "Recherche opérationnelle");
        model.addObject("titre", "Methode du coin Nord-Ouest");
        model.addObject("noLink", "active");
        model.addObject("ligne", ligne);
        model.addObject("colonne", colonne);

        return model;
    }
    //----------- recuperation de la matrice
    @RequestMapping(value="/nord-ouest-tableau", method = RequestMethod.POST)
    public ModelAndView tableau(@RequestParam Map<String,String> allRequestParams) throws Exception {
//        { tab00=1, tab01=8, tab02=1, tab03=5, tab04=4, offre0=240, tab10=5,
//          tab11=5, tab12=3, tab13=6, tab14=7, offre1=160, tab20=2, tab21=9,
//          tab22=5, tab23=9, tab24=8, offre2=260, demande0=120, demande1=130,
//          demande2=145, demande3=125, demande4=140, ligne=3, colonne=5}

        int ligne = Integer.parseInt(allRequestParams.get("ligne"));
        int colonne = Integer.parseInt(allRequestParams.get("colonne"));

        double[][] matrix = this.buildDoubleMatrixByParams(allRequestParams, ligne, colonne);
        double[] offres = this.buildDoubleTabByParams(allRequestParams,ligne,"offre");
        double[] demandes = this.buildDoubleTabByParams(allRequestParams,colonne,"demande");

        NordOuest nordOuest = new NordOuest(ligne,colonne,offres,demandes,matrix);
        nordOuest.run();

        //----------- afficher solutions
        offres = this.buildDoubleTabByParams(allRequestParams,ligne,"offre");
        demandes = this.buildDoubleTabByParams(allRequestParams,colonne,"demande");

        ModelAndView model = new ModelAndView("NordOuest/nord-ouest-result");
        model.addObject("color", "cyan");
        model.addObject("title", "Recherche opérationnelle");
        model.addObject("titre", "Methode du coin Nord-Ouest");
        model.addObject("noLink", "active");

        model.addObject("result", nordOuest.getZ());
        model.addObject("tableauInitial", matrix);
        model.addObject("solutions", nordOuest.getSolutions());
        model.addObject("offres", offres);
        model.addObject("demandes", demandes);

        return model;
    }


}
