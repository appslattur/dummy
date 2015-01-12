package com.example.ari.appslattur;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class GPSLocationsActivity extends Activity {
    GPSHelper myGPSHelper;
    DataBaseHelper myLocations;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpslocations);

        myGPSHelper = new GPSHelper(GPSLocationsActivity.this);
        myLocations= new DataBaseHelper(this);


        //Buttons on Main layout
        findViewById(R.id.gpspingButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeToast(myGPSHelper.getGPS());
            }
        });
        findViewById(R.id.gotosavecurrentlocationMenuButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleBottom();
            }
        });

        findViewById(R.id.distancetopoleButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myGPSHelper.getRawGPS() != null){
                    checkPoleDistance(myGPSHelper.getRawGPS());
                }else goError();
            }
        });


        //BottomLayout, sublayout branches from SAVECURRENTBtn
        findViewById(R.id.finishsavingcurrentlocationButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myGPSHelper.getRawGPS() == null)goError();
                else {
                    saveLocation(myGPSHelper.getRawGPS(), getLocationName());
                    toggleTop();
                }
            }
        });

        findViewById(R.id.seesavedlocationsButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeToast(myLocations.getLocations());
            }
        });

        findViewById(R.id.gotocreatelocationMenuButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleCreate();
            }
        });
        //Sublayout branching from CREATEBtn,
        findViewById(R.id.finishcreatinglocationButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createLocation();
            }
        });
    }

    private void checkPoleDistance(Location myLocation){
        Location northpole = new Location("Abs");
        northpole.setLatitude(90);
        northpole.setLongitude(0);

        double distance = myLocation.distanceTo(northpole);
        makeToast(distance+"m");
    }

    private void saveLocation(Location location, String name){
        myLocations.addLine(location.getLongitude()+"", location.getLatitude()+"", name);
        makeToast("Saved!");
    }

    private void goError(){
        makeToast("No Location Availible!");
    }

    private void createLocation(){
        Location newLoc = new Location("NETWORK");
        EditText lat = (EditText)findViewById(R.id.latitudeField);
        EditText lng = (EditText)findViewById(R.id.longitudeField);
        EditText nm = (EditText)findViewById(R.id.nameField2);
        if(checkIfFieldIsValid(lat) && checkIfFieldIsValid(lng) && checkIfFieldIsValid(nm)) {
            try {
                newLoc.setLatitude(Double.parseDouble(lat.getText().toString()));
                newLoc.setLongitude(Double.parseDouble(lng.getText().toString()));
                //myLocations.addLine(lat.getText().toString(), lng.getText().toString(), nm.getText().toString());
                saveLocation(newLoc, nm.getText().toString());
                makeToast("Saved!");
                toggleTop();
            } catch (Exception e) {
                //Log something
            }
        }else  makeToast("All fields must be filled!");
    }

    private String getLocationName(){
        EditText field =(EditText)findViewById(R.id.nameField);
        if(checkIfFieldIsValid(field)){
            return field.getText().toString();
        }
        return "NoName";
    }

    private void toggleBottom(){
        findViewById(R.id.topLayout).setVisibility(View.GONE);
        findViewById(R.id.bottomLayout).setVisibility(View.VISIBLE);
    }
    private void toggleTop(){
        findViewById(R.id.bottomLayout).setVisibility(View.GONE);
        findViewById(R.id.CREATELayout).setVisibility(View.GONE);
        findViewById(R.id.topLayout).setVisibility(View.VISIBLE);
    }
    private void toggleCreate(){
        findViewById(R.id.topLayout).setVisibility(View.GONE);
        findViewById(R.id.CREATELayout).setVisibility(View.VISIBLE);
    }

    private boolean checkIfFieldIsValid(EditText field){
      if(field.getText().toString().trim().equals("")){
          return false;
      }else return true;
    }

    private void makeToast(String s){
        Toast.makeText(getApplicationContext(), s,
                Toast.LENGTH_LONG).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.gpslocations, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
