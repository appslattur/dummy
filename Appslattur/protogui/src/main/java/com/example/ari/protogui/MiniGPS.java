package com.example.ari.protogui;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import java.util.ArrayList;

/**
 * Created by Ari on 22.1.2015.
 */
public class MiniGPS{

    Location myLocation;
    LocationManager myLocationManager;
    ArrayList<LocationChain> locations;
    MyLocationListener locationlistener = new MyLocationListener();
    String lastPlace ="";
    public Context context;

    public MiniGPS(ArrayList<LocationChain> l, Context contexxt){
        context = contexxt;
        myLocationManager = (LocationManager)context.getSystemService(context.LOCATION_SERVICE);
        locations = l;
        myLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000*1, 5, locationlistener);
    }

    public Location getLocation(){
        return myLocation;
    }

    public void cycle(){
        if(updateLocation(validateConnection()))scanSurroundings(locations);
    }

    public void updateMyLocations(ArrayList<LocationChain> l){
        locations = l;
    }

    public void stop(){
        myLocation = null;
        myLocationManager = null;
        locations = null;
        locationlistener = null;
        context = null;
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
    private void scanSurroundings(ArrayList<LocationChain> res){
        for(LocationChain chain : res){
            for(LocationChain.LocationLink l : chain.getLinks()){
                if(myLocation.distanceTo(l.location) <= 5 && lastPlace != l.location.getProvider() && l.isEnabled()){
                    lastPlace=l.location.getProvider();
                    fartNorification(l.location.getProvider()+"", l.location.getLongitude()+"", l.location.getLatitude()+"");
                    break;
                }
            }

        }
    }
    public void fartNorification(String name, String longi, String lat){
        NotificationHandler nHandler = new NotificationHandler(context.getApplicationContext(),
                name,
                longi,
                lat,
                R.drawable.ic_launcher);
        nHandler.sendNotification();
    }

    protected void scanPositive(){
        NotificationHandler nHandler = new NotificationHandler(context.getApplicationContext(),
                "Random TrickTitle",
                "Random Title",
                "Random Text",
                R.drawable.ic_launcher);
        nHandler.sendNotification();
    }

    private class MyLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {

            updateLocation(validateConnection(), location);
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    }
}
