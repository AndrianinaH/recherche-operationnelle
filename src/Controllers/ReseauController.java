package Controllers;

import Models.Reseau;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Andrianina_pc on 19/06/2018.
 */

@Controller
public class ReseauController extends BaseController {

    //---------------------- Methode coin nord-ouest

    @RequestMapping("/neurone")
    public ModelAndView index() throws Exception {
        ModelAndView model = new ModelAndView("Neurone/reseau");
        model.addObject("color", "teal");
        model.addObject("title", "Network Learning");
        model.addObject("titre", "Réseau de neurone");
        model.addObject("neuroneLink", "active");
        return model;
    }

    //----------- recuperation du init pour apprentissage
    @RequestMapping(value="/neurone-init", method = RequestMethod.POST)
    public ModelAndView init(@RequestParam("entrer") int entrer,
                             @RequestParam("dataParEntrer") int dataParEntrer,
                             @RequestParam("sortie") int sortie,
                             @RequestParam("dataParSortie") int dataParSortie,
                             @RequestParam("seuil") String seuil,
                             @RequestParam("alpha") String alpha) throws Exception {

        ModelAndView model = new ModelAndView("Neurone/reseau-tableau");
        model.addObject("color", "teal");
        model.addObject("title", "Network Learning");
        model.addObject("titre", "Réseau de neurone");
        model.addObject("neuroneLink", "active");
        model.addObject("entrer", entrer);
        model.addObject("dataParEntrer", dataParEntrer);
        model.addObject("sortie", sortie);
        model.addObject("dataParSortie", dataParSortie);
        model.addObject("seuil", seuil);
        model.addObject("alpha", alpha);

        return model;
    }

    //----------- recuperation du init pour prédiction
    @RequestMapping(value="/neurone-prediction-init", method = RequestMethod.POST)
    public ModelAndView predictInit(@RequestParam("dataParEntrer") int dataParEntrer) throws Exception {

        //----------- afficher solutions
        ModelAndView model = new ModelAndView("Neurone/reseau-prediction");
        model.addObject("color", "teal");
        model.addObject("title", "Network Learning");
        model.addObject("titre", "Réseau de neurone");
        model.addObject("neuroneLink", "active");
        model.addObject("dataParEntrer", dataParEntrer);
        return model;
    }

    //----------- recuperation de la matrice
    @RequestMapping(value="/neurone-tableau", method = RequestMethod.POST)
    public ModelAndView tableau(@RequestParam Map<String,String> allRequestParams,
                                @RequestParam("file") MultipartFile file) throws Exception {
        //--------------- initialisation
        int entrer = Integer.parseInt(allRequestParams.get("entrer"));
        int dataParEntrer = Integer.parseInt(allRequestParams.get("dataParEntrer"));
        int sortie = Integer.parseInt(allRequestParams.get("sortie"));
        int dataParSortie = Integer.parseInt(allRequestParams.get("dataParSortie"));

        //-------------- entrees
        List<double[]> allEntrees = this.buildListDoubleByParams(allRequestParams,entrer,dataParEntrer,"entrer");

        //-------------- sorties
        List<double[][]> allSorties = this.buildListDoubleTabTabByParams(allRequestParams,sortie,dataParSortie,"sortie");

        //-------------- parametres de calcul
        double seuil = Double.parseDouble(allRequestParams.get("seuil"));
        double alpha = Double.parseDouble(allRequestParams.get("alpha"));

        Reseau<Integer> reseau = new Reseau<>();
        File csvConfig = this.convertSpringFile(file);
        reseau.buildReseauByFile(csvConfig);
        //------------------------ apprentissage
        reseau.networkLearning(allEntrees,allSorties,alpha, seuil);

        //---------- extract CSV
        reseau.extractPoidsFinal(csvConfig);

        //----------- afficher solutions
        ModelAndView model = new ModelAndView("Neurone/reseau-prediction");
        model.addObject("color", "teal");
        model.addObject("title", "Network Learning");
        model.addObject("titre", "Réseau de neurone");
        model.addObject("neuroneLink", "active");
        model.addObject("dataParEntrer", dataParEntrer);

        return model;
    }

    @RequestMapping(value="/neurone-prediction", method = RequestMethod.POST)
    public ModelAndView predict(@RequestParam Map<String,String> allRequestParams,
                                @RequestParam("file") MultipartFile file) throws Exception {
        //--------------- initialisation
        int dataParEntrer = Integer.parseInt(allRequestParams.get("dataParEntrer"));

        Reseau<Integer> reseau = new Reseau<>();
        File csvConfig = this.convertSpringFile(file);
        reseau.buildReseauByFile(csvConfig);
        csvConfig.delete();

        //---------- prédiction
        double[] inputGiven = this.buildDoubleTabByParams(allRequestParams,dataParEntrer,"entrerPredit");
        double[][]sortiePredits = reseau.propagationEnAvant(inputGiven);

        //----------- afficher solutions
        ModelAndView model = new ModelAndView("Neurone/reseau-result");
        model.addObject("color", "teal");
        model.addObject("title", "Network Learning");
        model.addObject("titre", "Réseau de neurone");
        model.addObject("neuroneLink", "active");

        model.addObject("entrerPredits", inputGiven);
        model.addObject("sortiePredits", sortiePredits);


        return model;
    }

}
