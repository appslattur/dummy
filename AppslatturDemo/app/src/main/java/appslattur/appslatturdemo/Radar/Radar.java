package appslattur.appslatturdemo.Radar;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import java.util.ArrayList;

import appslattur.appslatturdemo.LocationChainStructure.LocationChain;


/**
 * Created by Ari on 22.1.2015.
 */
public class Radar {

    Location myLocation;
    LocationManager myLocationManager;
    LocationListener peeper;
    ArrayList<LocationChain> locations;
    String lastPlace ="";
    public Context context;

    /*
    Notkun:  Radar myscanner = new Radar(db.getLocationsList(), activity.this)
            myscanner.cycle() - scannar hvort notandi sé nálægt staðsetningu í geymslu
            og skilar gildi ef svo er.
     */
    public Radar(ArrayList<LocationChain> l, Context ctx){
        context = ctx;
        myLocationManager = (LocationManager)context.getSystemService(context.LOCATION_SERVICE);
        locations = l;
        peeper = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                myLocation = location;
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
        };
        myLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,0,0,peeper);
    }



    public Location getLocation(){
        if(updateLocation(validateConnection())){
            return myLocation;
        }
        return null;
    }
    /*
        Skilar location hlut í augnablikinu ef hann er innan marka, annars skilar hann null.
     */
    public int cycle()
    {
        if(updateLocation(validateConnection())){
            int temp = scanSurroundings(locations);
            if(temp != -1)return temp;
        }
        return -1;
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
    public int getDistanceToNearestLocation(){
        int dist = Integer.MAX_VALUE;
        for(LocationChain chain : locations) {
            for (LocationChain.LocationLink l : chain.getLinks()) {
                float tempdist = myLocation.distanceTo(l.getLocation());
                if(tempdist < dist){
                    dist = (int)tempdist;
                }
            }
        }
        return dist;
    }

    private boolean updateLocation(boolean connectionIsValid){
        if(connectionIsValid){
            Criteria c = new Criteria();

            this.myLocation = myLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            //this.myLocation = myLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            return true;
        }
        return false;
    }

    private boolean validateConnection(){
        if(myLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) || myLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            return true;
        }
        return false;
    }


    //This function calls scanPositive if required.
    private int scanSurroundings(ArrayList<LocationChain> res){
        for(LocationChain chain : res){
            for(LocationChain.LocationLink l : chain.getLinks()){
                if(myLocation.distanceTo(l.getLocation()) <= 5 && l.isEnabled()){
                    return l.getId();
                }
            }

        }
        return -1;
    }

}
