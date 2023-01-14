public class Satellite {
    
    //Pointless, but just in case it's wanted
    public Satellite() {
    }

    //Most useful
    public Satellite(String name, float position) {
        this.name = name;
        this.position = position;
    }

    //All options
    public Satellite(String name, float position, String norad, String cospar, String model, String launchSite,
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

    //Could be very well null, since there's no point to satellites without names
    private String name = "";

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
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
}
