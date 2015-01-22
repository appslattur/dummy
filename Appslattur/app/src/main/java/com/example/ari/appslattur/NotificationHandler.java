package com.example.ari.appslattur;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

/**
 * Created by Arnar Jónsson on 18.1.2015.
 */
public class NotificationHandler {


     private NotificationCompat.Builder nBuilder;
        private Context context;
        private Uri soundAlert;
        private Intent startingIntent;
        private boolean vibrate;

        private boolean soundFlag;

        public NotificationHandler(Context context, String tickerTitle, String title, String text,
                                   int icon, Uri uri, Intent startingIntent, boolean vibrate, Uri soundAlert,
                                   boolean autoCancel) {


            this.context = context;

            this.soundAlert = soundAlert;
            this.startingIntent = startingIntent;
            this.vibrate = vibrate;

            nBuilder = new NotificationCompat.Builder(this.context);

            nBuilder.setTicker(tickerTitle);
            nBuilder.setContentTitle(title);
            nBuilder.setContentText(text);
            nBuilder.setSmallIcon(icon);

            nBuilder.setAutoCancel(autoCancel);

            try {
                nBuilder.setSound(this.soundAlert);
            }
            catch( NullPointerException e) {
                this.soundFlag = false;
            }
        /*
        try {
            nBuilder.setVibrate(new long[] { 1000 });
        }
        catch (NullPointerException e) {
            //Do Nothing
        }
        */


        }

        public void sendNotification() {

            if(this.startingIntent == null) {
                Intent intent = new Intent();
                PendingIntent pIntent = PendingIntent.getActivity(this.context, 0, intent, 0);
                nBuilder.setContentIntent(pIntent);
            }
            else {
                PendingIntent pIntent = PendingIntent.getActivity(this.context, 0, startingIntent, 0);
                nBuilder.setContentIntent(pIntent);
            }

            Notification notification = nBuilder.build();

            if(!this.soundFlag) {
                notification.defaults = Notification.DEFAULT_SOUND;
            }
        /*
        if(this.vibrate) {
            notification.defaults = Notification.DEFAULT_VIBRATE;
        }
        */
            NotificationManager notificationManager = (NotificationManager) this.context.getSystemService(this.context.NOTIFICATION_SERVICE);
            notificationManager.notify(0, notification);

        }
}
