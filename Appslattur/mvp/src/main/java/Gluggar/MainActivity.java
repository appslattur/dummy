package Gluggar;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import NotificationHandler.NotificationHandler;
import ServiceHandler.AppService;


public class MainActivity extends ActionBarActivity {

    private NotificationHandler nHandler;
    private boolean isBounded;
    AppService.ServiceBinder sBinder;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            AppService.ServiceBinder serviceBinder = (AppService.ServiceBinder) service;
            sBinder = serviceBinder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(is.arnarjons.MainActivity.R.layout.activity_main);

        this.nHandler = new NotificationHandler(getApplicationContext());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(is.arnarjons.MainActivity.R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == is.arnarjons.MainActivity.R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void initiateServiceStart() {
        startService(new Intent(getBaseContext(), AppService.class));
    }

    public void initiateServiceDestruction() {
        stopService(new Intent(getBaseContext(), AppService.class));
    }
}
