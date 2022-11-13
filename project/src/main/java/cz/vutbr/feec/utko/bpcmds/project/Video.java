package cz.vutbr.feec.utko.bpcmds.project;

public class Video {
    private String URL = null;
    private String name = null;


    public String getURL() {
        return URL;
    }

    public void setURL(String uRL) {
        URL = uRL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Video(String URL, String name){
        this.URL = URL;
        this.name = name;
    }
    
    
}
