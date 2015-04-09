package appslattur.appslatturdemo.RadarHandler;

import java.io.Serializable;

/**
 * @author Arnar Jonsson
 * @version 0.1
 * @since  9.4.2015.
 */
public class RadarIterable implements Serializable {

    private int id;
    private double latitude;
    private double longitude;
    private int pingRadius;
    private boolean hasTimeLimit = false;
    private String timeStart;
    private String timeStop;

    /**
     * RadarIterable
     * Data object to check against Radar GPS coordinates
     */

    /////
    // Get/Set Method
    /////

    /**
     * @param id ID of object
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return ID of object
     */
    public int getId() {
        return this.id;
    }

    /**
     * @param lati A String representation of a number (tag == double)
     */
    public void setLatitude(String lati) {
        try {
            this.latitude = Double.parseDouble(lati);
        }
        catch (NumberFormatException e) {
            this.latitude = 0.0;
        }
    }

    /**
     * @return Latitude coordinate of the object
     */
    public double getLatitude() {
        return this.latitude;
    }

    /**
     * @param longi A String representation of a number (tag == double)
     */
    public void setLongitude(String longi) {
        try {
            this.longitude = Double.parseDouble(longi);
        }
        catch (NumberFormatException e) {
            this.longitude = 0.0;
        }
    }

    /**
     * @return Longitude coordinate of the object
     */
    public double getLongitude() {
        return this.longitude;
    }

    /**
     * @param pingRadius GPS-coord margin of errors, in meters
     */
    public void setPingRadius(int pingRadius) {
        this.pingRadius = pingRadius;
    }

    public void setTimeLimitFlag(int flag) {
        if(flag == 1) this.hasTimeLimit = true;
        else this.hasTimeLimit = false;
    }

    public boolean getTimeLimitFlag() {
        return this.hasTimeLimit;
    }

    /**
     * @return GPS-coord margin of errors, in meters
     */
    public int getPingRadius() {
        return this.pingRadius;
    }

    /**
     * @param timeStart A String representation of the timeframe '[HH:MM:SS]'
     */
    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;

        if(this.timeStop != null) {
            this.hasTimeLimit = true;
        }
    }

    /**
     * @return A String representation of a time start counter for the object
     *      if allowed, null otherwise
     */
    public String getTimeStart() {
        if(hasTimeLimit) return this.timeStart;
        else return null;
    }

    /**
     * @param timeStop A String representation of the timeframe '[HH:MM:SS]'
     */
    public void setTimeStop(String timeStop) {
        this.timeStop = timeStop;

        if(this.timeStart != null) {
            this.hasTimeLimit = true;
        }
    }

    /**
     * @return A String representation of a time stop counter for the object
     *      if allowed, null otherwise
     */
    public String getTimeStop() {
        if(hasTimeLimit) return this.timeStop;
        else return null;
    }

    /////
    // Public Methods
    /////

    /**
     * @return True if both timestamps (timeStart and timeStop) have been initialized
     */
    public boolean hasTimeLimit() {
        return this.hasTimeLimit;
    }

}
