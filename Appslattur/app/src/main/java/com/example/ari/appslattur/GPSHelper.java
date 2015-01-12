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
