package com.example.ari.appslattur;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/*
Multi window single activity.  This class toggles layout files and initializes buttons
instead of starting another activity.
 */


public class MainActivity extends Activity {

    GPSHelper myGPSHelper;
    PushNotificationHelper myPushNotificationHelper;
    ConnectivityHelper myConnectivityHelper;
    DataBaseHelper myLocations;

    int searchRange = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Helpers return strings, finish helper functions without modifications
        //to this class
        myGPSHelper = new GPSHelper(MainActivity.this);
        myPushNotificationHelper = new PushNotificationHelper();
        myConnectivityHelper = new ConnectivityHelper(MainActivity.this);
        myLocations= new DataBaseHelper(this);



        findViewById(R.id.locationServiceButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(canScan()){
                    makeToast("Location service available");
                }
                else makeToast("Location service unavailable");
            }
        });


        findViewById(R.id.gotoCreateNewLocationButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleCreateNewLocationLayout();
            }
        });
        findViewById(R.id.gotoSaveCurrentButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleSaveCurrentLocationLayout();
            }
        });
        findViewById(R.id.gotosetrangelayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleSetRange();
            }
        });
        findViewById(R.id.scanButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(canScan()) {
                    searchLocationsInRange();
                }else{
                    makeToast("Location service unavailable");
                }
            }
        });
        findViewById(R.id.clearlocations).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myLocations.clearTable();
            }
        });
        findViewById(R.id.noti).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationServiceHelper nSHelper = new NotificationServiceHelper(
                        getApplicationContext(), "Random Title", 30000);
            }
        });
    }

    private void toggleSaveCurrentLocationLayout(){
        setContentView(R.layout.savecurrentlocationlayout);

        findViewById(R.id.finishSavingCurrentLocationButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(myGPSHelper.getRawGPS() == null)goError();
                else {
                    saveLocation(myGPSHelper.getRawGPS(), getLocationName((EditText)findViewById(R.id.currentLocationNameField)));
                    toggleMainSelectionMenuLayout();
                }
            }
        });

    }

    private void toggleCreateNewLocationLayout(){
       setContentView(R.layout.createnewlocationlayout);

        findViewById(R.id.finishNewLocationButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createLocation();
            }
        });
    }

    private void toggleMainSelectionMenuLayout(){
       setContentView(R.layout.activity_main);
        findViewById(R.id.locationServiceButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(canScan()){
                    makeToast("Location service available");
                }
                else makeToast("Location service unavailable");
            }
        });


        findViewById(R.id.gotoCreateNewLocationButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleCreateNewLocationLayout();
            }
        });
        findViewById(R.id.gotoSaveCurrentButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleSaveCurrentLocationLayout();
            }
        });
        findViewById(R.id.gotosetrangelayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleSetRange();
            }
        });
        findViewById(R.id.scanButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(canScan()) {
                    searchLocationsInRange();
                }else{
                    makeToast("Location service unavailable");
                }
            }
        });
        findViewById(R.id.clearlocations).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myLocations.clearTable();
            }
        });

        findViewById(R.id.noti).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationServiceHelper nSHelper = new NotificationServiceHelper(
                        getApplicationContext(), "Random Title", 30000);
            }
        });
    }

    private void toggleSetRange(){
        setContentView(R.layout.searchrange);
        findViewById(R.id.finishsettingrangebutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleMainSelectionMenuLayout();
            }
        });
        getSeekBarValue();
    }

    protected void searchLocationsInRange(){
        ArrayList<Location> locations = myLocations.getLocationList();
        boolean match = false;
        for(Location l : locations){
            if(myGPSHelper.getRawGPS().distanceTo(l) <= searchRange){

                String name = l.getProvider();
                makeToast("Close to : "+ name);
                match=true;
                break;
            }
        }
        if(!match)makeToast("Nothing in range");
    }

    //Toasts a String
    protected void makeToast(String s){
        Toast.makeText(getApplicationContext(), s,
                Toast.LENGTH_LONG).show();
    }

    protected void createLocation(){
        Location newLoc = new Location("NETWORK");
        EditText lat = (EditText)findViewById(R.id.latitudeField);
        EditText lng = (EditText)findViewById(R.id.longitudeField);
        EditText nm = (EditText)findViewById(R.id.newLocationNameField);
        if(checkIfFieldIsValid(lat) && checkIfFieldIsValid(lng) && checkIfFieldIsValid(nm)) {
            try {
                newLoc.setLatitude(Double.parseDouble(lat.getText().toString()));
                newLoc.setLongitude(Double.parseDouble(lng.getText().toString()));
                saveLocation(newLoc, nm.getText().toString());
                makeToast("Saved!");
                toggleMainSelectionMenuLayout();
            } catch (Exception e) {
                //Log something
            }
        }else  makeToast("All fields must be filled!");
    }

    protected boolean checkIfFieldIsValid(EditText field){
        if(field.getText().toString().trim().equals("")){
            return false;
        }else return true;
    }

    protected void saveLocation(Location location, String name){
        myLocations.addLine(location.getLongitude()+"", location.getLatitude()+"", name);
        makeToast("Saved!");
    }

    protected void goError(){
        makeToast("No Location Available!");
    }

    protected String getLocationName(EditText et){

        if(checkIfFieldIsValid(et)){
            return et.getText().toString();
        }
        return "NoName";
    }

    protected boolean canScan(){

        if(myGPSHelper.getRawGPS() != null)return true;
        return false;
    }

    protected void getSeekBarValue(){
        SeekBar myBar =(SeekBar)findViewById(R.id.seekBar);
        myBar.setProgress(searchRange);
        TextView mytext = (TextView)findViewById(R.id.displayrangeView);
        mytext.setText(searchRange+"m");
        myBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                int value = seekBar.getProgress();
                TextView mytext = (TextView)findViewById(R.id.displayrangeView);
                mytext.setText(value+"m");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                searchRange = seekBar.getProgress();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.back) {
            toggleMainSelectionMenuLayout();
        }
        return super.onOptionsItemSelected(item);
    }
}
