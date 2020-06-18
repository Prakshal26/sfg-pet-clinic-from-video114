package guru.springframework.sfgpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
In main application page we have given spring application. So that Spring Application will go and look for the controller.
It will come here as we have given it controller, likewise it will look for all the controller.
 */
@Controller
public class IndexController {

    /*
    1.RequestMapping: Whenever Request comes to root directory or index or index.html this operation wil take place
        i.e this will cater the need of index. html page or home page or root direcotry.
    2. One thing to notice here is that it is returning index that means it will only return to
        request which will come from index page. In resources/templates we have created an index.html
        page which means it will only return with values to that page only.
            return "index";
     */

    @RequestMapping({"", "/", "index", "index.html"})
    public String index() {

        return "index";
    }
}
