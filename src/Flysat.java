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
        Float posNumber = Float.parseFloat(position.substring(0,position.length()-3));
        String director = position.substring(position.length()-1);
        if(director.equals(new String("W"))){
            posNumber=posNumber*(-1);
        }
        return posNumber;
        
    }

    @Override
    public ArrayList<Satellite> getSatellites() {
        ArrayList <Satellite> satellites = new ArrayList<Satellite>();
        Document document = null;
        try {
            document = Jsoup.connect(url).get();
        } catch (Exception e) {
            return satellites;
        }
        Elements sats = document.select("tr:has(> td > font > a)");
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
