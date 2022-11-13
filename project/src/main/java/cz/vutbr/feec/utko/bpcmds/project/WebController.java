package cz.vutbr.feec.utko.bpcmds.project;

import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.HandlerMapping;

import cz.vutbr.feec.utko.bpcmds.project.component.ProjectResourceComponent;

@Controller
public class WebController {
        //Component pro zasílání potřebných částí souboru videa
        private ProjectResourceComponent handler;

        // Anotace Autowired vytvoří závislost na objektu handler, který je využit v metodě byterange
        @Autowired
        public WebController(ProjectResourceComponent handler) {
            this.handler = handler;
        }
    public final static String DASH_DIRECTORY = "D:\\MDS\\files\\streams\\MPEG-DASH\\";    
    ArrayList<Video> VideoList = new ArrayList<Video>();
    
    @RequestMapping(value = {"/dash/{file}"}, method = RequestMethod.GET)
    public void streaming(@PathVariable("file") String file, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        File STREAM_FILE = new File(DASH_DIRECTORY + file);

        request.setAttribute(ProjectResourceComponent.ATTR_FILE, STREAM_FILE);
        handler.handleRequest(request, response);
    }
    
    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("type", "stream");
        return "index";
    }

    @GetMapping("/addvideo")
    public String addVideo(){
        return "addvideo.html";
    }

    @RequestMapping(value = "videolibrary", method = {RequestMethod.POST, RequestMethod.GET})
    public String videoLibrary(@RequestParam() String url, @RequestParam() String nazev, Model model){
        if(url.isEmpty()){
            model.addAttribute("urlEmpty", true);
        }
        else if(nazev.isEmpty()){
            model.addAttribute("nameEmpty", true);
        }
        else{
            VideoList.add(new Video(url, nazev));
            if(!VideoList.isEmpty()){
                model.addAttribute("library", VideoList);
            }
        }
        
        return "videolibrary";
    }

    @RequestMapping(value = {"/player/{url}"}, method = RequestMethod.GET)
    public String player(@PathVariable("url") String file, Model model){
        model.addAttribute("url", url);

        return "player";
    }



}
