package com.example.ari.appslattur;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Arnar Jónsson on 22.1.2015.
 */
public class NotificationServiceHandler extends IntentService {


    private ParcelDict parcelDict;

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

        Bundle data = intent.getExtras();
        this.parcelDict = (ParcelDict) data.getParcelable("ParcelDict");

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                sendNotification();
            }
        };
        timer.schedule(task, this.parcelDict.getDelay());
    }



    private void sendNotification() {
        NotificationHelper nHelper = new NotificationHelper(getApplicationContext(),
                this.parcelDict.getTickerTitle(),
                this.parcelDict.getTitle(),
                this.parcelDict.getText(),
                this.parcelDict.getLifeSpan());
    }
}
