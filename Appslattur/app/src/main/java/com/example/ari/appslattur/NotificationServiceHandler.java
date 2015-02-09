package com.example.ari.appslattur;

import android.app.IntentService;
import android.content.Intent;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Arnar Jónsson on 22.1.2015.
 */
public class NotificationServiceHandler extends IntentService {


    private String title;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public NotificationServiceHandler(String name) {
        super(name);
    }

    public NotificationServiceHandler() {
        super("NotificationServiceHandler");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        this.title = intent.getStringExtra("titleMessage");
        int delay = intent.getIntExtra("delay", 30000);

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                sendNotification();
            }
        };
        timer.schedule(task, delay);
    }

    private void sendNotification() {
        NotificationHelper nHelper = new NotificationHelper(getApplicationContext(), title);
    }
}
