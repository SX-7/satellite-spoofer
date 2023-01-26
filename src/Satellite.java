import java.util.ArrayList;

public class Satellite implements Comparable<Satellite>{
    
    public static final int NAME = 0;
    public static final int POSITION = 1;
    private static int sortOption = NAME;

    //Pointless, but just in case it's wanted
    public Satellite() {
    }

    //Most useful
    public Satellite(String name, float position) {
        this.name.set(0, name);
        this.position = position;
    }

    //All options
    public Satellite(ArrayList<String> name, float position, String norad, String cospar, String model, String launchSite,
            String launchDate, String producer) {
        this.name = name;
        this.position = position;
        this.norad = norad;
        this.cospar = cospar;
        this.model = model;
        this.launchSite = launchSite;
        this.launchDate = launchDate;
        this.producer = producer;
    }

    private ArrayList<String> name = new ArrayList<String>();

    public ArrayList<String> getName() {
        return this.name;
    }

    public void setName(ArrayList<String> name) {
        this.name = name;
    }

    //Just in case we don't know
    private float position = Webpages.NULL;

    public float getPosition() {
        return this.position;
    }

    public void setPosition(float position) {
        this.position = position;
    }

    //Now few optional vals. 
    private String norad = "";

    public String getNorad() {
        return this.norad;
    }

    public void setNorad(String norad) {
        this.norad = norad;
    }

    private String cospar = "";

    public String getCospar() {
        return cospar;
    }

    public void setCospar(String cospar) {
        this.cospar = cospar;
    }

    private String model = "";

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    private String launchSite = "";

    public String getLaunchSite() {
        return launchSite;
    }

    public void setLaunchSite(String launchSite) {
        this.launchSite = launchSite;
    }

    private String launchDate = "";

    public String getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(String launchDate) {
        this.launchDate = launchDate;
    }

    private String producer = "";

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    @Override
    public String toString() {
        return "Satellite [names=" + name + ", position=" + position + ", norad=" + norad + ", cospar=" + cospar
                + ", model=" + model + ", launchSite=" + launchSite + ", launchDate=" + launchDate + ", producer="
                + producer + "]";
    }

    public int compareTo(Satellite arg0) {
        switch (sortOption) {
            case NAME:
                if(null==arg0.getName().get(0)){
                    return 1;
                }
                return arg0.getName().get(0).compareToIgnoreCase(this.getName().get(0));        
            case POSITION:
                if(arg0.getPosition()==Webpages.NULL){
                    return 1;
                }
                return (int) (this.getPosition()-arg0.getPosition());
            default:
                return 0;
        }        
    }
    
    public static void setSortOption(int option){
        sortOption = switch (option) {
            case NAME,POSITION:
                yield option;
            default:
                throw new IllegalArgumentException("Unsupported option: "+option);
        };
    }
}
