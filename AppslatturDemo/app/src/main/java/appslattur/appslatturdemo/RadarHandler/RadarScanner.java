package appslattur.appslatturdemo.RadarHandler;

import android.content.Context;
import android.util.Log;

import appslattur.appslatturdemo.GPSListener.GPSHandler;
import appslattur.appslatturdemo.GPSListener.GPSLocation;

/**
 * @author Ari Freyr Gudmundsson
 * @author Arnar Jonsson
 * @version 0.1
 * @since 02.04.2015
 */
public class RadarScanner {

    private Context context;

    private GPSHandler gpsHandler;

    /**
     * RadarScanner
     * An object that tries to match current GPS-coords with
     * longitude and latitude values stored in a given database
     *
     * @param context Context of the application
     */
    public RadarScanner(Context context) {
        this.context = context;

        this.gpsHandler = new GPSHandler(context);
    }

    /////
    // Get/Set methods
    /////

    /**
     * Sets the context of the object
     * @param context
     */
    public void setContext(Context context) {
        this.context = context;
    }

    /**
     * Returns current context of the object
     * @return Context context
     */
    public Context getContext() {
        return this.context;
    }

    /////
    // Public Methods
    /////

    /**
     * Scan()
     * Performs a RadarScan, retrieves the last known location of
     * the device and matches it with an ArrayList provided by the
     * database handler
     * @return id of a specific row in the database if its location matches the users
     *  location, -1 otherwise
     */
    public int Scan() {

        try {
            GPSLocation gpsLocation = getCurrentLocation();
            double latitude = gpsLocation.getLatitude();
            double longitude = gpsLocation.getLongitude();
            //TODO : check for potentially matching latitudes and longitudes
            return 0;
        }
        catch ( Exception e  ) {
            Log.w("Exception", e);
            return -1;
        }

    }

    /////
    // Private methods
    /////

    /**
     * @return GPSLocation, Current location according to the GPSHandler
     */
    private GPSLocation getCurrentLocation() {
        try {
            return this.gpsHandler.getGPSLocation();
        }
        catch (Exception e) {
            Log.w("Exception", e);
            return new GPSLocation(-1, -1);
        }
    }
}
