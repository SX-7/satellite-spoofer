import java.util.ArrayList;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Flysat extends Webpages {

    private String url = "https://www.flysat.com/en/satellitelist";


    public Flysat(String url) {
        super(url);
    }

    private static float parsePosition(String position) {
        return Float.parseFloat(position.substring(0,position.length()-3));
    }

    @Override
    public ArrayList<Satellite> getSatellites() {
        Document document = null;
        try {
            document = Jsoup.connect(url).get();
        } catch (Exception e) {
            return null;
        }
        Elements sats = document.select("tr:has(> td > font > a)");
        ArrayList <Satellite> satellites = new ArrayList<Satellite>();
        for (Element sat : sats) {
            Satellite satellite = new Satellite();
            satellite.setName(sat.select("td:eq(1)").text());
            try {
                satellite.setPosition(parsePosition(sat.select("td:eq(2)").text()));
            } catch (Exception ei) {
                satellite.setName(sat.select("td:eq(0)").text());
                try {
                    satellite.setPosition(parsePosition(sat.select("td:eq(1)").text()));    
                } catch (Exception eo) {
                    //this shouldn't happen
                    System.err.println(eo);
                    continue;
                }
                
            }
            
            satellites.add(satellite);
        }
        return satellites;
    }
    
}
