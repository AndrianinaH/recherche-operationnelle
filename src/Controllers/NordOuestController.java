package Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Andrianina_pc on 15/06/2018.
 */

@Controller
@RequestMapping("/nord-ouest")
public class NordOuestController {

    //---------------------- Methode coin nord-ouest

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index() throws Exception {
        ModelAndView model = new ModelAndView("NordOuest/nord-ouest");
        model.addObject("color", "cyan");
        model.addObject("title", "Recherche opérationnelle");
        model.addObject("titre", "Methode du coin Nord-Ouest");

        model.addObject("noLink", "active");
        return model;
    }
}
