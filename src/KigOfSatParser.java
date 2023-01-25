import java.util.ArrayList;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class KigOfSatParser extends Webpages{

    public KigOfSatParser(String url) {
        super(url);
    }
    
    private static float parsePosition(String position) {
        Float posNumber = Float.parseFloat(position.substring(0,position.length()-2));
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
        
        Elements sats = document.select("div#cbfreq > table > tbody > tr");
        for (Element sat : sats) {
//        	
            Satellite satellite = new Satellite();
            ArrayList<String> name = new ArrayList<String>();
            name.add(sat.select("td:eq(1) > a").text());
            satellite.setName(name);
            satellite.setPosition(parsePosition(sat.select("td:eq(0) > a").text()));
            satellite.setNorad(sat.select("td:eq(2").text());
            satellites.add(satellite);
            System.out.println(satellite);
        }
        return satellites;
    }
    
//    For test purposes
//    public static void main(String[] args) {
//    	String url = "https://pl.kingofsat.net/satellites.php";
//    	KigOfSatParser kigOfSat = new KigOfSatParser(url);
//    	kigOfSat.getSatellites();
//    }
}
