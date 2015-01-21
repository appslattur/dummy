package com.example.ari.appslattur;

import android.app.IntentService;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Ari on 21.1.2015.
 *
 * Start this service with a list of locations as a paramater, it does the rest.
 */
public class BackgroundServiceStarter extends IntentService {
    private MiniGPS proximityScanner = new MiniGPS(null);

    public BackgroundServiceStarter(){
        this("Nothing");
    }

    public BackgroundServiceStarter(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent workIntent){

        String dataString = workIntent.getDataString();
    }

    @Override
    public void onCreate(){
        startScanning();
    }

    private void startScanning(){

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                proximityScanner.cycle();
            }
        };
        timer.schedule(task, 300000);
    }


    private class MiniGPS{

        Location myLocation;
        LocationManager myLocationManager;
        ArrayList<Location> locations;


        private MiniGPS(ArrayList<Location> l){
            myLocationManager = (LocationManager)getSystemService(LOCATION_SERVICE);
            locations = l;
        }

        private void cycle(){
            if(updateLocation(validateConnection()))scanSurroundings(locations);

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
        private void scanSurroundings(ArrayList<Location> res){

            boolean match = false;
            for(Location l : res){
                if(myLocation.distanceTo(l) <= 50){
                    scanPositive(l);
                    break;
                }
            }
        }

        private void scanPositive(Location location){
            /*
            This function should recive a location to which you are near,
            then send a notification about it.
             */
        }

    }

}




