package com.example.ari.protogui.Radar;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;

import com.example.ari.protogui.LocationChainStructure.LocationChain;

import java.util.ArrayList;

/**
 * Created by Ari on 22.1.2015.
 */
public class MiniGPS {

    Location myLocation;
    LocationManager myLocationManager;
    ArrayList<LocationChain> locations;
    String lastPlace ="";
    public Context context;

    public MiniGPS(ArrayList<LocationChain> l, Context contexxt){
        context = contexxt;
        myLocationManager = (LocationManager)context.getSystemService(context.LOCATION_SERVICE);
        locations = l;

    }

    public Location getLocation(){
        return myLocation;
    }

    public Location cycle()
    {
        myLocationManager = null;
        myLocationManager = (LocationManager)context.getSystemService(context.LOCATION_SERVICE);
        if(updateLocation(validateConnection())){
            Location temp = scanSurroundings(locations);
            if(temp != null)return temp;
        }
        return null;

    }

    public void updateMyLocations(ArrayList<LocationChain> l){
        locations = l;
    }


    private boolean updateLocation(boolean connectionIsValid, Location location){
        if(connectionIsValid){
            this.myLocation = location;
            return true;
        }
        return false;
    }

    private boolean updateLocation(boolean connectionIsValid){

        if(connectionIsValid){
            this.myLocation = myLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            return true;
        }
        return false;
    }

    private boolean validateConnection(){
        if(myLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
            return true;
        }
        return false;
    }


    //This function calls scanPositive if required.
    private Location scanSurroundings(ArrayList<LocationChain> res){
        for(LocationChain chain : res){
            for(LocationChain.LocationLink l : chain.getLinks()){
                if(myLocation.distanceTo(l.getLocation()) <= 5 && lastPlace != l.getLocation().getProvider() && l.isEnabled()){
                    lastPlace=l.getLocation().getProvider();
                    return l.getLocation();

                }
            }

        }
        return null;
    }

}
