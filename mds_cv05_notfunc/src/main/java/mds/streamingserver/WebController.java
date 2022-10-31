package mds.streamingserver;

import mds.streamingserver.components.MyResourceHttpRequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerMapping;
import org.thymeleaf.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.util.Objects;

@Controller
public class WebController {

    private MyResourceHttpRequestHandler handler;

    @Autowired
    public WebController(MyResourceHttpRequestHandler handler){
        this.handler = handler;
    }

    private final static File MP4_FILE = new File("D:\\MDS\\files\\videos\\bbb_1080p.mp4");

    @PostMapping(value = "/video")
    public String video(){
        return "videoMP4";
    }
    @ResponseBody
    @GetMapping(path = "/file", produces = "video/mp4")
    public FileSystemResource wholeFile() {
        return new FileSystemResource(MP4_FILE);
    }
    @GetMapping("byterange")
    public void byterange(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(MyResourceHttpRequestHandler.ATTR_FILE, MP4_FILE);
        handler.handleRequest(request, response);
    }
    @GetMapping
    public String home(){
        return "index";
    }
    @RequestMapping(path = "/player", method = {RequestMethod.GET, RequestMethod.POST})
    public String player(Model model,
                         @RequestParam String url,
                         @RequestParam(defaultValue = "false") boolean muted,
                         @RequestParam(defaultValue = "false") boolean autoplay,
                         @RequestParam(defaultValue = "1000px") String width) {
        if(StringUtils.isEmpty((url))) {
            model.addAttribute("error", "true");
        }
        model.addAttribute("url", url);
        model.addAttribute("muted", muted ? "true" : "");
        model.addAttribute("autoplay", autoplay ? "true" : "");
        model.addAttribute("width",width);
        return "player";
    }


    //------------------------------------ ADAPTIVNI STREAM ---------------------------------------------------

    private final static String HLS_PATH = "D:\\MDS\\files\\streams\\HLS\\";
    private final static String DASH_PATH = "D:\\MDS\\files\\streams\\MPEG-DASH\\";


    @RequestMapping(value = {"/dash/{file}", "/hls/{file}", "/hls/{quality}/{file}"}, method = RequestMethod.GET)
    public void adaptive_streaming(@PathVariable("file") String file, @PathVariable(value = "quality", required = false) String quality, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        File STREAM_FILE = null;

        String handle = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);

        switch (handle){
            case "/dash/{file}":
                STREAM_FILE = new File(DASH_PATH + file);
                break;
            case "/hls/{file}":
                STREAM_FILE = new File(HLS_PATH + file);
                break;
            case "/hls/{quality}/{file}":
                STREAM_FILE = new File(HLS_PATH + quality + "\\"+ file);
                break;
            default:
                STREAM_FILE = null;
        }

        request.setAttribute(MyResourceHttpRequestHandler.ATTR_FILE, STREAM_FILE);
        handler.handleRequest(request, response);
    }


    @RequestMapping(value = "dashPlayer", method = {RequestMethod.GET, RequestMethod.POST})
    public String dashPlayer(Model model, @RequestParam String choose){
        model.addAttribute("url", choose);

        return "dashPlayer";
    }

    @RequestMapping(value = "gallery", method = {RequestMethod.GET})
    public String gallery(){

        return "gallery";
    }

}
