import java.util.ArrayList;

public abstract class Webpages {
    public static final int NULL = 255;
    
    protected String url = ""; //Probably change to URL class from jsoup or standard java lib

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    protected ArrayList<Satellite> satellites = null;

    public abstract ArrayList<Satellite> getSatellites ();

    public Webpages(String url) {
        this.url = url;
    }

    
}
