package com.example.ari.appslattur;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends Activity {

    GPSHelper myGPSHelper;
    PushNotificationHelper myPushNotificationHelper;
    RealTimeHelper myRealTimeHelper;
    ConnectivityHelper myConnectivityHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Helpers return strings, finish helper functions without modifications
        //to this class
        myGPSHelper = new GPSHelper(MainActivity.this);
        myPushNotificationHelper = new PushNotificationHelper();
        myRealTimeHelper = new RealTimeHelper();
        myConnectivityHelper = new ConnectivityHelper(MainActivity.this);

        findViewById(R.id.gpspingButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeToast(myGPSHelper.getGPS());
            }
        });
        findViewById(R.id.pushnotificationButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeToast(myPushNotificationHelper.pushNotification());
            }
        });
        findViewById(R.id.realtimeButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeToast(myRealTimeHelper.getRealTime());
            }
        });
        findViewById(R.id.displayconnectionsButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeToast(myConnectivityHelper.getAvailibleConnections());
            }
        });

        findViewById(R.id.gotogpsActivityButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(MainActivity.this, GPSLocationsActivity.class);
                MainActivity.this.startActivity(myintent);
            }
        });


        /*
        Much is simplified here since we dont need to know what location we are near,
        knowing that we can detect priximity within 50m is sufficient for now
         */
        findViewById(R.id.proximityButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myGPSHelper.proximityScanner(myGPSHelper.getRawGPS()) != null){
                    makeToast("You are near something!");
                };
            }
        });
    }

    /*
    Simplified toast making for convinience.
     */
    private void makeToast(String s){
        Toast.makeText(getApplicationContext(), s,
                Toast.LENGTH_LONG).show();
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
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
