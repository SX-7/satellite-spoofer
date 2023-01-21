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
            ArrayList<String> names = new ArrayList<String>();
            Elements fields = sat.select("a");
            if(fields.size()==2){
                names.add(fields.eq(0).text());
                satellite.setName(names);
                satellite.setPosition(parsePosition(fields.eq(1).text()));
                satellites.add(satellite);
            } else if (fields.size()==3){
                names.add(fields.eq(1).text());
                satellite.setName(names);
                satellite.setPosition(parsePosition(fields.eq(2).text()));
                satellites.add(satellite);
            }
            
        }
        return satellites;
    }
    
}
