import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SatBeams extends Webpages {

	public SatBeams(String url) {
		super(url);
	}

	@Override
	public ArrayList<Satellite> getSatellites() {

		ArrayList<Satellite> al = new ArrayList<Satellite>();
		try {
			Document doc = Jsoup.connect(super.getUrl()).get();
			
			Elements media = doc.select("table [cellpadding=0][cellspacing=0][class=sat_grid][id=sat_grid]");

			for (Element row : media.select(".class_tr")) {
				ArrayList<String> names = new ArrayList<String>();
				Elements cells = row.select("td");
				String name = cells.get(3).text();
				names.add(name);
				String position = cells.get(1).text();
				String norad = cells.get(4).text();
				String cospar = cells.get(5).text();
				String model = cells.get(6).text();
				String launchSite = cells.get(8).text();
				String launchDate = cells.get(10).text();
				String producer = "no data";
				String[] tmp = new String[2];
				tmp = position.split(" ");
				Float tmp2 = Float.parseFloat(tmp[0]);
				al.add(new Satellite(names,tmp2,norad,cospar,model,launchSite,launchDate,producer));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		return al;
	}
    
}