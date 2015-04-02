package appslattur.appslatturdemo.GPSListener;

/**
 *  @author Arnar Jonsson
 *  @author Ari Freyr Gudmundsson
 *  @version 0.1
 *  @since 02.04.2015
 */
public class GPSLocation {

    private double latitude;
    private double longitude;

    /**
     * GPSLocation
     * @param latitude Latitude of the location
     * @param longitude Longitude of the location
     */
    public GPSLocation(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }


}
