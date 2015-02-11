package appslattur.appslatturdemo.Gluggar;

import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import appslattur.appslatturdemo.DatabaseHelper.DataBaseHelper;
import appslattur.appslatturdemo.LocationChainStructure.LocationChain;
import appslattur.appslatturdemo.R;
import appslattur.appslatturdemo.Radar.Radar;


public class SaveThisLocation extends ActionBarActivity {
    Radar radar;
    DataBaseHelper mdb;
    ArrayList<LocationChain> locs = new ArrayList<LocationChain>();

    Location myloc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_this_location);

        mdb = new DataBaseHelper(this);
        locs = mdb.getLocationList();
        radar = new Radar(locs, SaveThisLocation.this);


        findViewById(R.id.finishSavingCurrentLocationButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(radar.getLocation() != null){
                    myloc = radar.getLocation();
                    EditText getname= (EditText)findViewById(R.id.currentLocationNameField);
                    String name = getname.getText().toString();
                    makeToast(name);
                    mdb.newLocation(myloc.getLongitude() + "", myloc.getLatitude() + "", name, "1");
                }
            }
        });
    }
    protected void makeToast(String s){
        Toast.makeText(getApplicationContext(), s,
                Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_save_this_location, menu);
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
