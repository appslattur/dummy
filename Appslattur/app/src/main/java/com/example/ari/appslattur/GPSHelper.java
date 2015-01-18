package com.example.ari.appslattur;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;

/**
 * Created by Ari on 9.1.2015.
 */
public class GPSHelper {
    LocationManager myLocationManager;
    public GPSHelper(Context ctx){
        myLocationManager = (LocationManager)ctx.getSystemService(ctx.LOCATION_SERVICE);
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
        return "";
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
        if(getGPSValidity()){
            try {
                //Get GPS Coords
                Location location = myLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                return location;
            }catch (Exception e) {
                e.printStackTrace();
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
