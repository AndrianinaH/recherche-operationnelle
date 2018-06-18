package Controllers;

import Hongroise.MethodeHongroise;
import org.omg.CORBA.INTERNAL;
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
public class IndexController extends BaseController {

    //---------------------- Methode hongroise

    @RequestMapping("/")
    public ModelAndView index() throws Exception {
        ModelAndView model = new ModelAndView("Hongroise/index");
        model.addObject("color", "indigo");
        model.addObject("title", "Recherche opérationnelle");
        model.addObject("titre", "Methode Hongroise");
        model.addObject("hongroiseLink", "active");
        return model;
    }

    //----------- recuperation du init
    @RequestMapping(value="/hongroise-init", method = RequestMethod.POST)
    public ModelAndView init(@RequestParam("init") int init) throws Exception {
        ModelAndView model = new ModelAndView("Hongroise/hongroise-tableau");
        model.addObject("color", "indigo");
        model.addObject("title", "Recherche opérationnelle");
        model.addObject("titre", "Methode Hongroise");
        model.addObject("hongroiseLink", "active");
        model.addObject("init", init);
        return model;
    }

    //----------- recuperation de la matrice
    @RequestMapping(value="/hongroise-tableau", method = RequestMethod.POST)
    public ModelAndView tableau(@RequestParam Map<String,String> allRequestParams) throws Exception {
//        { tab00=17, tab01=15, tab02=9, tab03=5, tab04=12, tab10=16, tab11=16,
//          tab12=10, tab13=5, tab14=10, tab20=12, tab21=15, tab22=14, tab23=11,
//          tab24=5, tab30=4, tab31=8, tab32=14, tab33=17, tab34=13, tab40=13,
//          tab41=9, tab42=8, tab43=12, tab44=17, init=5}
        int init = Integer.parseInt(allRequestParams.get("init"));
        int[][] matrix = this.buildIntMatrixByParams(allRequestParams, init);
        MethodeHongroise methodeHongroise = new MethodeHongroise(init);
        methodeHongroise.setTableauInitiale(matrix);
        methodeHongroise.afficherTableau(methodeHongroise.getTableauInitiale());
        //----------- minimisation
        int minimal = methodeHongroise.minimisationAffectation(methodeHongroise.getTableauInitiale());
        System.out.println("minimal farany= "+minimal);
        //----------- maximisation
        int maximal = methodeHongroise.maximisationAffectation(methodeHongroise.getTableauInitiale());
        System.out.println("maximal farany= "+maximal);

        ModelAndView model = new ModelAndView("Hongroise/hongroise-result");
        model.addObject("color", "indigo");
        model.addObject("title", "Recherche opérationnelle");
        model.addObject("titre", "Methode Hongroise");
        model.addObject("hongroiseLink", "active");

        model.addObject("tableauInitial", methodeHongroise.getTableauInitiale());
        model.addObject("minimal", minimal);
        model.addObject("maximal", maximal);

        return model;
    }

}
