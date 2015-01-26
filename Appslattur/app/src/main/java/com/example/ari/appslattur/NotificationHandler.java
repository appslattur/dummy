package com.example.ari.appslattur;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Arnar Jónsson on 26.1.2015.
 */
public class NotificationHandler implements Runnable {

    private boolean isRunning;
    private NotificationData data;
    private NotificationCompat.Builder nBuilder;

    public NotificationHandler(NotificationData data) {
        this.data = data;
        this.isRunning = true;
    }

    @Override
    public void run() {

        nBuilder = new NotificationCompat.Builder(this.data.getContext());
        nBuilder.setTicker(this.data.getTickerTitle());
        nBuilder.setContentTitle(this.data.getTitle());
        nBuilder.setSubText(this.data.getText());

        Intent intent = new Intent();
        PendingIntent pintent = PendingIntent.getActivity(data.getContext(), 0, intent, 0);
        nBuilder.setContentIntent(pintent);

        Notification notification = nBuilder.build();
        notification.defaults = Notification.DEFAULT_SOUND;

        Context nContext = this.data.getContext();

        final NotificationManager nManager = (NotificationManager)nContext.getSystemService(
                nContext.NOTIFICATION_SERVICE);
        nManager.notify(0, notification);

        if(!this.data.isEternal()) {
            Timer timer = new Timer();
            TimerTask tTask = new TimerTask() {
                @Override
                public void run() {
                    nManager.cancel(0);
                }
            };
            timer.schedule(tTask, 20000);
        }


    }
}
