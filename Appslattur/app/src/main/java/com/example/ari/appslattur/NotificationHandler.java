package com.example.ari.appslattur;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

/**
 * Created by Arnar Jónsson on 18.1.2015.
 */
public class NotificationHandler {

    private NotificationCompat.Builder nBuilder;
    private Context context;

    public NotificationHandler(Context context, String tickerTitle, String title, String text, int icon) {

        this.context = context;

        nBuilder = new NotificationCompat.Builder(this.context);

        nBuilder.setTicker(tickerTitle);
        nBuilder.setContentTitle(title);
        nBuilder.setContentText(text);
        nBuilder.setSmallIcon(icon);

    }

    public void sendNotification() {

        Intent intent = new Intent();
        PendingIntent pIntent = PendingIntent.getActivity(this.context, 0, intent, 0);
        nBuilder.setContentIntent(pIntent);

        Notification notification = nBuilder.build();
        notification.flags = Notification.FLAG_AUTO_CANCEL;

        NotificationManager notificationManager = (NotificationManager) this.context.getSystemService(this.context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);

    }
}
