package Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Andrianina_pc on 15/06/2018.
 */

@Controller
@RequestMapping("/")
public class IndexController {

    //---------------------- Methode hongroise

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index() throws Exception {
        ModelAndView model = new ModelAndView("Hongroise/index");
        model.addObject("color", "indigo");
        model.addObject("title", "Recherche opérationnelle");
        model.addObject("titre", "Methode Hongroise");

        model.addObject("hongroiseLink", "active");
        return model;
    }

}
