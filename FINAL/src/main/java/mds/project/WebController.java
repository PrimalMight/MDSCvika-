package mds.project;

import mds.project.component.ProjectResourceComponent;
import mds.project.model.VideoLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

import static mds.project.FilePaths.*;

@Controller
public class WebController {


    private ProjectResourceComponent handler;

    @Autowired
    public WebController(ProjectResourceComponent handler) {
        this.handler = handler;
    }

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping(value = "video", method = {RequestMethod.POST})
    public String video(@RequestParam(defaultValue = VIDEO_FROM_URL) String url, @RequestParam(defaultValue = "1000") String width, Model model){
        model.addAttribute("url", url);
        model.addAttribute("width", width);

        return "video";
    }

    @RequestMapping(value = {"/dash/{stream}/{file}"}, method = RequestMethod.GET)
    public void streaming(
            @PathVariable("stream") String stream,
            @PathVariable("file") String file,
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        File STREAM_FILE = new File(DASH_DIRECTORY + stream + "\\" + file);


        request.setAttribute(ProjectResourceComponent.ATTR_FILE, STREAM_FILE);
        handler.handleRequest(request, response);
    }

    @RequestMapping(value = {"/player/{stream}"}, method = RequestMethod.GET)
    public String player(@PathVariable("stream") String stream, Model model){
        model.addAttribute("stream", stream);

        return "player";
    }

    @GetMapping("/videocollection")
    public String videoCollection(Model model) throws IOException {
        VideoLibrary library = new VideoLibrary();
        model.addAttribute("library", library);


        return "videocollection";
    }

}
