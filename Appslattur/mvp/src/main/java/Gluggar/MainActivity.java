package Gluggar;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import DatabaseHelper.DataBaseHelper;
import LocationChainStructure.LocationChain;
import Radar.Radar;
import mvp.R;

public class MainActivity extends Activity {
    Radar radar;
    ArrayList<LocationChain> locs = new ArrayList<LocationChain>();
    DataBaseHelper mdb;
    Location myloc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mdb = new DataBaseHelper(this);
        locs = mdb.getLocationList();
        radar = new Radar(locs, this);
        //radar.cycle();
        findViewById(R.id.saveButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveScreen();
            }
        });
        findViewById(R.id.seesavedlocationsbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, viewlocationsactivity.class );
                MainActivity.this.startActivity(intent);
            }
        });

        findViewById(R.id.clearTable).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mdb.clearTable();
                mdb.populateTable();
            }
        });

        //startLoop();


    }

    private void startLoop(){
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                radar.cycle();
                startLoop();
            }
        };

        long delay = 6000;   //x*delay   = x minutes
        long period = 3600000;//x*perdiod = x hours
        timer.schedule(task, delay, 1*period );
    }



    public void updateGPSList(){
        radar.updateMyLocations(mdb.getLocationList());
    }

    protected void makeToast(String s){
        Toast.makeText(getApplicationContext(), s,
                Toast.LENGTH_LONG).show();
    }
    private void saveScreen(){
        setContentView(R.layout.savecurrentlocationlayout);
        findViewById(R.id.finishSavingCurrentLocationButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(radar.getLocation() != null){
                    myloc = radar.getLocation();
                    EditText getname= (EditText)findViewById(R.id.currentLocationNameField);
                    String name = getname.getText().toString();
                    mdb.newLocation(myloc.getLongitude() + "", myloc.getLatitude() + "", name, "1");
                    makeToast("Saved :" + name + ", " + myloc.getLongitude()+ ", "+ myloc.getLatitude());
                    updateGPSList();
                    toggleMainSelectionMenuLayout();
                }
            }
        });


    }
    private void toggleMainSelectionMenuLayout(){
        setContentView(R.layout.activity_main);

        findViewById(R.id.saveButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveScreen();
            }
        });
        findViewById(R.id.seesavedlocationsbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, viewlocationsactivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
        findViewById(R.id.clearTable).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mdb.clearTable();
                mdb.populateTable();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
