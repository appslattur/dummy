package appslattur.appslatturdemo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import appslattur.appslatturdemo.DataBaseHandler.DatabaseController;
import appslattur.appslatturdemo.DataBaseHandler.DatabaseEntry;
import appslattur.appslatturdemo.DataBaseHandler.DatabaseEntryTask;
import appslattur.appslatturdemo.DataBaseHandler.DatabaseValue;
import appslattur.appslatturdemo.DataBaseHandler.DatabaseValueTask;
import appslattur.appslatturdemo.DatabaseHelper.DataBaseHelper;
import appslattur.appslatturdemo.DatabaseHelper.FSDatabase;
import appslattur.appslatturdemo.DatabaseHelper.FSDatabaseEntry;
import appslattur.appslatturdemo.GPSListener.GPSHandler;
import appslattur.appslatturdemo.GPSListener.GPSLocation;
import appslattur.appslatturdemo.Gluggar.Listar.MinKort;
import appslattur.appslatturdemo.RadarHandler.RadarScannerIterable;
import appslattur.appslatturdemo.ServiceHandler.AppService;

public class MainActivity extends Activity {
    DataBaseHelper mdb;
            GPSHandler gpsHandler;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gpsHandler = new GPSHandler(MainActivity.this);

        setContentView(R.layout.activity_main);


        Bitmap dickbutt;
        InputStream jizz = null;

        try{
            AssetManager assetManager = this.getAssets();
            jizz = assetManager.open("appslattur.png");
            dickbutt = BitmapFactory.decodeStream(jizz);

            findViewById(R.id.bannerlogoview).setBackground(new BitmapDrawable(dickbutt));

        } catch (IOException e) {
            e.printStackTrace();
        }

        findViewById(R.id.minkortbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MinKort.class);
                MainActivity.this.startActivity(intent);
            }
        });

        findViewById(R.id.populate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataBaseHelper ddddd = new DataBaseHelper(MainActivity.this.getBaseContext());
                ddddd.populateTable();
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent i = new Intent(getApplicationContext(), AppService.class);
                //startService(i);
                //GPSLocation gpsLocation = gpsHandler.getGPSLocation();
                //Toast.makeText(MainActivity.this, "Lat: " + gpsLocation.getLatitude() +
                //    " | lon: " + gpsLocation.getLongitude(), Toast.LENGTH_SHORT).show();

                DatabaseEntry entry = new DatabaseEntry(0.0,
                        0.0,
                        "cardGroup",
                        null,
                        false,
                        "longDesc",
                        "shortDesc",
                        true);
                long please = 0;
                try {
                     please = new DatabaseEntryTask(MainActivity.this).
                            execute(entry).get();
                }
                catch (Exception e) {
                    Toast.makeText(MainActivity.this,
                            "SomeThingWrong", Toast.LENGTH_SHORT).show();
                }

                int stateCheck = 0;
                ArrayList<DatabaseValue> aList = new ArrayList<DatabaseValue>();
                try {
                    aList = new DatabaseValueTask(MainActivity.this).
                            execute().get();
                    stateCheck = 1;
                }
                catch (Exception e) {
                    //Do Nothing
                }
                try {
                    DatabaseValue tv = aList.get(0);
                    String fuckString =
                            "id is : " + tv.getId() + "\n" +
                            " lat is : " + tv.getLatitude() + "\n" +
                            " lon is : " + tv.getLongitude() + "\n" +
                            " cardgroup is : " + tv.getCardGroup() + "\n" +
                            " mallgroup is : " + tv.getMallGroup() + "\n" +
                            " hasTimeLimit is : " + tv.hasTimeLimit() + "\n" +
                            " longDesc is : " + tv.getLongDescription() + "\n" +
                            " shortDesc is : " + tv.getShortDescription() + "\n" +
                            " isEnabled is : " + tv.isEnabled();
                    TextView textView = (TextView) findViewById(R.id.textView);
                    textView.setText(fuckString);

                }
                catch (Exception e) {

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
