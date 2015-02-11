package appslattur.appslatturdemo.ServiceHandler;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;

import java.util.Timer;
import java.util.TimerTask;

import appslattur.appslatturdemo.DatabaseHelper.DataBaseHelper;
import appslattur.appslatturdemo.NotificationHandler.NotificationHandler;


/**
 * Created by Arnar Jónsson on 9.2.2015.
 */
public class AppService extends Service {

    //Debugging variables
    private boolean debug = true;
    private int debugStage;

    //Notification and logic cheats
    private NotificationHandler nHandler;
    private DataBaseHelper db;

    private Timer serviceTimer = new Timer();
    private int hasBounded;
    private boolean hasStarted;
    private static int nextInterval;

    //Timer.scheduleAtFixedRates exceptionHandler
    private Handler exceptionCanceler = new Handler(Looper.getMainLooper());

    //Binding object
    private final IBinder serviceBinder = new ServiceBinder();


    // Empty Constructor
    public AppService() {

    }

    private void getPendingIntent() {}


    @Override
    public void onCreate() {

        this.nHandler = new NotificationHandler(getBaseContext());
        db = new DataBaseHelper(getBaseContext());
        this.debugStage = 1;
        this.hasBounded = 1;
        this.hasStarted = true;

        if(this.debug) {

        }
    }

    public void initiateLifeCycle() {
        this.serviceTimer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                exceptionCanceler.post(new Runnable() {
                    public void run() {
                        //LifeCycle lC = new LifeCycle(this.nHandler, this.db);
                        //lC.run();
                    }
                });
            }
        }, 1, 50000);
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        initiateLifeCycle();

        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {



        return null;
    }
    /*
    @Override
    public void onRebind(Intent intent) {
        if(this.debug) {

        }
    }

    @Override
    public void onLowMemory() {

        if(this.debug) {

        }
    }
    */
    @Override
    public void onDestroy() {
        if(this.debug) {
            initiateCleanUp();
            stopSelf();
        }
        stopSelf();
    }

    /*
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        //Not Smart enough
    }

    // Tools that i need to understand more about
    */



    private void initiateCleanUp() {
        this.serviceTimer.cancel();
    }

    /*
    private void sendNotification(String tickerTitle, String title, String text) {
        this.nHandler.addNotification(new NotificationData(
                        this.debugStage,
                        tickerTitle,
                        title,
                        text,
                        R.drawable.ic_launcher,
                        RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION),
                        500),
                getPendingIntent());
    }
    */




    public class ServiceBinder extends Binder {
        public ServiceBinder getService() {
            return ServiceBinder.this;
        }
    }

    private class LifeCycle implements Runnable {

        private NotificationHandler nHandler;
        private DataBaseHelper db;

        private LifeCycle(NotificationHandler nHandler, DataBaseHelper db) {
            this.nHandler = nHandler;
            this.db = db;
        }

        public void run() {


        }
    }

}
