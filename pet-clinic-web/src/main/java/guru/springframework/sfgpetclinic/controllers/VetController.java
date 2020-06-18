package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.boot.actuate.autoconfigure.endpoint.condition.ConditionalOnAvailableEndpoint;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VetController {

    private final VetService vetService;

    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    /*
        This time our index page is in vets/index folder that's why we had to return "vets/index"
        If we would have given return "index". the main index page which is in template folder would
        have been called.
         */
    @RequestMapping({"/","/vets", "/vets/index","/vets/index.html"})
    public String listVets(Model model) {

        model.addAttribute("vets",vetService.findAll());

        return "vets/index";
    }

}
