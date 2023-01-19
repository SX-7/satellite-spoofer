import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        Flysat fs = new Flysat("https://www.flysat.com/en/satellitelist");
        ArrayList<Satellite> al = fs.getSatellites();
    }
}
