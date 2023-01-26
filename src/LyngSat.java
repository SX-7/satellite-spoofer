import java.io.FilterOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class LyngSat extends  Webpages{

    public LyngSat(String url) {
        super("https://www.lyngsat.com/");
        satellites = new ArrayList<Satellite>();
        parseSubpage("asia.html");
        parseSubpage("europe.html");
        parseSubpage("atlantic.html");
        parseSubpage("america.html");
    }
    @Override
    public ArrayList<Satellite> getSatellites() {
        return satellites;
    }

    //This method will parse satellites from different subpages
    private void parseSubpage(String subpage) {
        String url = this.url + subpage;
        Document document;
        try {
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            System.out.println(e);
            return;
        }
        Elements rows = getTableRows(document);

        //Removing elements with wildcard attributes in names (for example "incl.")
        for (int i = 0; i < rows.size(); i++) {
            String found = rows.get(i).select("td > font > font > i").toString();
            if (!found.equals("")) {
                rows.remove(i);
            }
        }

        int i = 0;
        while (i < rows.size()) {
            Satellite currentSatellite = new Satellite();
            ArrayList<String> names = new ArrayList<String>();
            float position = Webpages.NULL;

            //Get position value
            position = Float.parseFloat(getNthFieldText(rows.get(i), 1).split("°")[0]);

            //Change to negative depending on the direction
            switch (getNthFieldText(rows.get(i), 1).split("°")[1]) {
                case "E" -> position *= -1.0;
                case "W" -> position *= 1.0;
            }

            names = new ArrayList<String>(List.of(getNthFieldText(rows.get(i), 2).split("/")));

            //If the first field of a row has a rowspan
            if (rows.get(i).select("td:nth-child(1)").attr("rowspan") != "") {
                int rowCount = Integer.parseInt(rows.get(i).select("td:nth-child(1)").attr("rowspan"));

                //First satellite is parsed normally

                currentSatellite.setPosition(position);
                currentSatellite.setName(names);
                satellites.add(currentSatellite);
                i++;

                //Iterate through the following elements in that rowspan and add satellites with changed names
                for(int j = 1; j < rowCount; j++) {
                    names = new ArrayList<String>(List.of(getNthFieldText(rows.get(i), 1).split("/")));
                    currentSatellite.setName(names);
                    satellites.add(currentSatellite);
                    i++;
                }
            } else {
                currentSatellite.setPosition(position);
                currentSatellite.setName(names);
                satellites.add(currentSatellite);
                i++;
            }
            
        }
    }

    private Elements getTableRows(Document doc) {

        Elements rows = new Elements();

        Elements firstTableRows = doc.select("body > div > table > tbody > tr > td:nth-child(2) > table:nth-child(8) > tbody > tr > td:nth-child(1) > table > tbody > tr");
        Elements secondTableRows = doc.select("body > div > table > tbody > tr > td:nth-child(2) > table:nth-child(8) > tbody > tr > td:nth-child(2) > table > tbody > tr");

        rows.addAll(firstTableRows);
        rows.addAll(secondTableRows);

        return  rows;
    }

    private String getNthFieldText(Element row, Integer index) {
        return row.select("td:nth-child(" + index + ")").text();
    }

}
