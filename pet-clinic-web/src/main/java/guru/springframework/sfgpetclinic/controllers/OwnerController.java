package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;

import java.util.List;

/*
Here we have done Request Mapping at top itself that if all the mapping by default inside this class
will go by /owner/<>
 */
@RequestMapping("/owners")
@Controller
public class OwnerController {

    private static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "owners/createOrUpdateOwnerForm";
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

    /*
    This is the page when someone click on find owner. IT will display a search box where user will
    search for the owner.
     */
    @RequestMapping({"/find"})
    public String findOwners(Model model)
    {
        model.addAttribute("owner", Owner.builder().build());
        return "owners/findOwners";
    }
    //On top of classs we have mentioned /owner so need to give here
    /*
    This is the mapping when user has clicked on search, it will redirect to this page with the owner
    details that user has searched for.
    In findOwners we have created a form whose action is /owners i.e this function.
     */
    @GetMapping
    public String processFindForm(Owner owner, BindingResult result, Model model){
        // allow parameterless GET request for /owners to return all records
        if (owner.getLastName() == null) {
            owner.setLastName(""); // empty string signifies broadest possible search. That is
            //if user has not entered anything it will display all the owners present.
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
    /*
    It display the owner details including its pet and pet type.
    It display the details based on requested id.
     */
    @GetMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable Long ownerId) {
        ModelAndView mav = new ModelAndView("owners/ownerDetails");
        mav.addObject(ownerService.findById(ownerId));
        return mav;
    }

    @GetMapping("/new")
    public String initCreationForm(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/new")
    public String processCreationForm(@Valid Owner owner, BindingResult result) {
        if (result.hasErrors()) {
            return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
        } else {
            Owner savedOwner =  ownerService.save(owner);
            return "redirect:/owners/" + savedOwner.getId();
        }
    }

    @GetMapping("/{ownerId}/edit")
    public String initUpdateOwnerForm(@PathVariable Long ownerId, Model model) {
        model.addAttribute(ownerService.findById(ownerId));
        return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/{ownerId}/edit")
    public String processUpdateOwnerForm(@Valid Owner owner, BindingResult result, @PathVariable Long ownerId) {
        if (result.hasErrors()) {
            return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
        } else {
            owner.setId(ownerId);
            Owner savedOwner = ownerService.save(owner);
            return "redirect:/owners/" + savedOwner.getId();
        }
    }

}

