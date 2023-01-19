import java.util.ArrayList;

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
        url = url + subpage;
        satellites.add(null);
        return null;
    }
}
