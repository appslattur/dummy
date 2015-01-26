package com.example.ari.appslattur;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.test.ApplicationTestCase;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/*
Multi window single activity.  This class toggles layout files and initializes buttons
instead of starting another activity.
 */


public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        //////////////
        // DUMMIE IMMORTAL SERVICE TESTING
        //////////////
        if(isServiceRunning("DummyService")) {
            Intent serviceIntent = new Intent(this,DummyService.class);
            startService(serviceIntent);
            bindService(serviceIntent,getApplicationContext().bindService(),0);
        }
        else {

        }

        startService(new Intent(this, DummyService.class));
        //////////////
        // DUMMIE IMMORTAL SERVICE TESTING ends here
        //////////////
        */
        Button button = (Button)findViewById(R.id.noti);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                new NotificationHelper(new NotificationData(getApplicationContext(),
                        "TestTickerTitle",
                        "TestTitle",
                        "TestText",
                        true,
                        false));
            }
        });

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /////
    // Service related testing
    /////
    private boolean isServiceRunning(String serviceClassName) {
        ActivityManager activityManager = (ActivityManager)getApplicationContext().
                getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> services = activityManager.getRunningServices(
                Integer.MAX_VALUE);
        for(ActivityManager.RunningServiceInfo runningServiceInfo: services) {
            if(runningServiceInfo.service.getClassName().equals(serviceClassName)) {
                return true;
            }
        }
        return false;
    }
    /////
    // Service related testing stopped
    /////
}
