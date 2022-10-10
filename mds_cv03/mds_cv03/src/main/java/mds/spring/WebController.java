package mds.spring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WebController {

    @GetMapping("static")
    public String staticPage(){
        return "staticpage";
    }

    @GetMapping("dynamic")
    public String dynamicPage(Model model){
        model.addAttribute("name", "JJ");
        return "dynamicpage";
    }

    // -----------------------------------------

    @GetMapping("myself")
    public String myself(){
        return "myself_page";
    }

    @GetMapping("alice")
    public String alice(Model model){
        model.addAttribute("style", "alice");
        model.addAttribute("name", "alice");
        return "template";
    }

    @GetMapping("bob")
    public String bob(Model model){
        model.addAttribute("style", "bob");
        model.addAttribute("name", "bob");
        return "template";
    }

}
