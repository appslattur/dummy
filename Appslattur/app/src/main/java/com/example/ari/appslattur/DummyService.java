package com.example.ari.appslattur;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Arnar Jónsson on 22.1.2015.
 */
public class DummyService extends Service {

    private final int notificationInterval = 1000 * 60 * 60;

    @Override
    public void onCreate() {
        // The service is being created
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        TimerTask timerTask = new TimerTask() {
            public void run() {
                NotificationServiceHelper tempNSH = new NotificationServiceHelper(
                        getApplicationContext(),
                        "Random Title",
                        10 * 1000);
            }
        };

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(timerTask, 5 * 60 * 1000, 5 * 60 * 1000);
        return START_STICKY;
    }
    @Override
    public IBinder onBind(Intent intent) {
        // A client is binding to the service with bindService()
        return null;
    }
    @Override
    public boolean onUnbind(Intent intent) {
        // All clients have unbound with unbindService()
        return false;
    }
    @Override
    public void onRebind(Intent intent) {
        // A client is binding to the service with bindService(),
        // after onUnbind() has already been called
    }
    @Override
    public void onDestroy() {
        // The service is no longer used and is being destroyed
    }

}
