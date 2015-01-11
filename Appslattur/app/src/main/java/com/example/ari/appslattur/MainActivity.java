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

        findViewById(R.id.GPSBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeToast(myGPSHelper.getGPS());
            }
        });
        findViewById(R.id.PUSHBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeToast(myPushNotificationHelper.pushNotification());
            }
        });
        findViewById(R.id.TIMEBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeToast(myRealTimeHelper.getRealTime());
            }
        });
        findViewById(R.id.CONNECTIVITYBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeToast(myConnectivityHelper.getAvailibleConnections());
            }
        });

        findViewById(R.id.GPSActivityBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(MainActivity.this, GPSLocationsActivity.class);
                MainActivity.this.startActivity(myintent);
            }
        });
    }

    //Toasts a String
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
