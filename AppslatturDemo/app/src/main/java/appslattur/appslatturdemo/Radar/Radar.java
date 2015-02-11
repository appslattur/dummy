package appslattur.appslatturdemo.Radar;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;

import java.util.ArrayList;

import appslattur.appslatturdemo.LocationChainStructure.LocationChain;


/**
 * Created by Ari on 22.1.2015.
 */
public class Radar {

    Location myLocation;
    LocationManager myLocationManager;
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
            this.myLocation = myLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            this.myLocation = myLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
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
