package appslattur.appslatturdemo.GPSListener;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import java.util.Comparator;

/**
 * Created by arnarjons on 2.4.2015.
 */
public class GPSHandler {

    private Context context;

    private Location userLocation;

    private LocationListener locListener;

    private LocationManager lManager;


    private double MIN_TIME_INTERVAL = 1000 * 60 * 2.5;

    public GPSHandler(Context context) {
        this.context = context;

        this.locListener = new GPSLocListener();

        this.lManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        this.lManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this.locListener);
    }

    /////
    // Get/Set methods
    /////

    public void setContext(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return this.context;
    }

    /////
    // Public methods
    /////
    public GPSLocation getGPSLocation() {

        try {
            Location loc = this.lManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            GPSLocation gpsLocation = new GPSLocation(loc.getLatitude(), loc.getLongitude());
            return gpsLocation;
        }
        catch (Exception e) {
            Log.w("Exception", e);
            return new GPSLocation(0, 0);
        }

    }

    /////
    // Private methods
    /////
    private GPSLocation createGPSLocation(double lati, double longi) {
        return new GPSLocation(lati, longi);
    }

    private long minToSeconds(double min) {
        try {
            double seconds = min * 60 * 1000;
            long time = (long) seconds;
            return time;
        }
        catch ( NumberFormatException e ) {
            long time = 150000;
            return time;
        }
    }


    /**
     * GPSLocListener
     *
     */
    private class GPSLocListener implements LocationListener {

        @Override
        public void onLocationChanged(Location loc) {
            userLocation = loc;
        }

        @Override
        public void onProviderDisabled(String provider) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extra) {

        }
    }
}
