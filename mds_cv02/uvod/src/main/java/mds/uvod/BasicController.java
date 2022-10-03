package mds.uvod;

import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("basic")
public class BasicController {

    @GetMapping
    public String hello(){
        return "<h1>hello world!</h1>";
    }

    @GetMapping("list")
    public List<String> testList(){
        return List.of("Hello", "from", "List", "of", "strings");
    }

    @GetMapping("test1")
    public String testParam1(@RequestParam(defaultValue = "DEFAULT") String name){
        return String.format("Hello %s welcome", name);
    }

    @GetMapping("test2")
    // name = "jmeno" pouze prejmenovava parametr v browseru pak ?jmeno="something"
    public String testParam2(@RequestParam(defaultValue = "DEFAULT", name = "jmeno") String name2){
        return String.format("Hello %s welcome", name2);
    }

    @GetMapping("test3")
    public String testParam3(@RequestParam List<String> id){
        return "All IDs are: " + id;
    }

    @GetMapping("form")
    public String helloForm(){
        String html = "";

        return html;
    }

    @GetMapping("student")
    public String student(@RequestParam("name") String userName, @RequestParam("number") String id){
        return String.format("Student: %s  ID: %d", userName, id);
    }
}
