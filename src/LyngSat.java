import java.util.ArrayList;
import org.jsoup.*;
import org.jsoup.nodes.Document;

public class LyngSat extends  Webpages{

    public LyngSat(String url) {
        super("https://www.lyngsat.com/");
        parseSubpage("asia.html");
        parseSubpage("europe.html");
        parseSubpage("atlantic.html");
        parseSubpage("america.html");
    }
    @Override
    public ArrayList<Satellite> getSatellites() {
        return null;
    }

    //This method will parse satellites from different subpages
    private ArrayList<Satellite> parseSubpage(String subpage) {
        String url = this.url + subpage;
        return null;
    }
}
