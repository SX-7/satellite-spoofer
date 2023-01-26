import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        ArrayList<Satellite> sl1 = new Flysat("https://www.flysat.com/en/satellitelist").getSatellites();
        ArrayList<Satellite> sl2 = new SatBeams("https://www.satbeams.com/satellites").getSatellites();
        ArrayList<Satellite> sl3 = new KigOfSatParser("https://pl.kingofsat.net/satellites.php").getSatellites();
        ArrayList<Satellite> sl4 = new LyngSat("https://www.lyngsat.com").getSatellites();
        for (int i=0;i<1;i++) {
            System.out.println(sl1.get(i));
        }
        for (int i=0;i<1;i++) {
            System.out.println(sl2.get(i));
        }
        for (int i=0;i<1;i++) {
            System.out.println(sl3.get(i));
        }
        for (int i=0;i<1;i++) {
            System.out.println(sl4.get(i));
        }
        Satellite.setSortOption(Satellite.POSITION);
        java.util.Collections.sort(sl1,sl1.get(0));
        for (int i=0;i<1;i++) {
            System.out.println(sl1.get(i));
        }
    }
}
