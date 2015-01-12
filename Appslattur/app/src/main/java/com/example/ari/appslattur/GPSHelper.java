package com.example.ari.appslattur;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;

import java.util.ArrayList;

/**
 * Created by Ari on 9.1.2015.
 */
public class GPSHelper {
    LocationManager myLocationManager;
    ArrayList<Location> hardLocations = new ArrayList<Location>();
    public GPSHelper(Context ctx){
        myLocationManager = (LocationManager)ctx.getSystemService(ctx.LOCATION_SERVICE);
        initHardLocations();
    }
    /*
    This function only returns a string value for gps, this function is not intented
    for any other use than to print out the phones location for visual confirmation.
     */
    public String getGPS(){

        if(getNetworkValidity()){
            try {
                //Get GPS Coords
                Location location = myLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                String latitude = "Lat:"+location.getLatitude();
                String longitude = "Long:"+location.getLongitude();
                return latitude + ".." + longitude;
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "No Location";
    }
    /*
    This function returns a valid Location object from either availible source(this is not complete)
    which can be used for distance calculation and other relevant functions.
     */
    public Location getRawGPS(){
        if(getNetworkValidity()){
            try {
                //Get GPS Coords
                Location location = myLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

                return location;
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    /*
    Hardcoded initilazation for handpicked locations, new locations can be added
    and priximityDetector will include them in its search without modification.
     */
    private void initHardLocations(){
        Location haskolatorg = new Location("Absolute");
            haskolatorg.setLongitude(64.139992);
            haskolatorg.setLatitude(-21.950347);


        Location nord = new Location("Absolute");
            nord.setLongitude(64.139657);
            nord.setLatitude(-21.955915);

        Location taeknigardur = new Location("Absolute");
            taeknigardur.setLongitude(64.139391);
            taeknigardur.setLatitude(-21.955272);

        hardLocations.add(haskolatorg);
        hardLocations.add(nord);
        hardLocations.add(taeknigardur);


    }

    /*
    Runs throu the list of hardcoded locations and returns the first one
    within 50m.  If not match is found it returns null.
     */
    public Location proximityScanner(Location myLocation){

        int range = 50;

        for(Location l : hardLocations){
            if(myLocation.distanceTo(l) <= 50){
                return l;
            }
        }

        return null;
    }

    private boolean getGPSValidity(){
        return myLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    private boolean getNetworkValidity(){
        return myLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }
}
