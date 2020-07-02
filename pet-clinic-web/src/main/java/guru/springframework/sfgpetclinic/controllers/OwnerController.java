package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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
    This web binder is kind of Security feature. What it does is that it does not allow to
    web page/web form to modify the id field. As id is our primary key so we
    do not want it to be modified so that's we are using it.
     */
    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    /*
     //   Request Mapping Owner at top of class so it is by defualt like
       // "/owner", "/owner/index" ,"/owner/index.html"

    @RequestMapping({"","/", "/index","/index.html"})
    public String listOwners(Model model){

        //In owners/index.html we sending this value i.e value of all the ownerns. owners is the
        //keyword that index.html will look for.
        //findAll is a function we have implemented in AbstractMapService class.

        model.addAttribute("owners",ownerService.findAll());
        return "owners/index";
    }
     */
    @RequestMapping({"/find"})
    public String findOwners(Model model)
    {
        model.addAttribute("owner", Owner.builder().build());
        return "owners/findOwners";
    }
    //On top of classs we have mentioned /owner so need to give here
    @GetMapping
    public String processFindForm(Owner owner, BindingResult result, Model model){
        // allow parameterless GET request for /owners to return all records
        if (owner.getLastName() == null) {
            owner.setLastName(""); // empty string signifies broadest possible search
        }

        // find owners by last name
        // % we have given so that if someone search prak instead of prakshal then also it give output
        List<Owner> results = ownerService.findAllByLastNameLike("%"+owner.getLastName() + "%");

        if (results.isEmpty()) {
            // no owners found
            result.rejectValue("lastName", "notFound", "not found");
            return "owners/findOwners";
        } else if (results.size() == 1) {
            // 1 owner found
            owner = results.get(0);
            return "redirect:/owners/" + owner.getId();
        } else {
            // multiple owners found
            model.addAttribute("selections", results);
            return "owners/ownersList";
        }
    }

    @GetMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable("ownerId") Long ownerId) {
        ModelAndView mav = new ModelAndView("owners/ownerDetails");
        mav.addObject(ownerService.findById(ownerId));
        return mav;
    }


}
