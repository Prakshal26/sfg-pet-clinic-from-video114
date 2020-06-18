package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/*
Here we have done Request Mapping at top itself that if all the mapping by default inside this class
will go by /owner/<>
 */
@RequestMapping("/owners")
@Controller
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    /*
        Request Mapping Owner at top of class so it is by defualt like
        "/owner", "/owner/index" ,"/owner/index.html"
         */
    @RequestMapping({"","/", "/index","/index.html"})
    public String listOwners(Model model){
        /*
        In owners/index.html we sending this value i.e value of all the ownerns. owners is the
        keyword that index.html will look for.
        findAll is a function we have implemented in AbstractMapService class.
         */
        model.addAttribute("owners",ownerService.findAll());
        return "owners/index";
    }
}
