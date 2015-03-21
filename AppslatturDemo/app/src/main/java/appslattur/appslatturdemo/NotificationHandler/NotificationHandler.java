package appslattur.appslatturdemo.NotificationHandler;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;

import appslattur.appslatturdemo.Gluggar.SpecificDetails;

/**
 * Created by Arnar Jónsson on 9.2.2015.
 */
public class NotificationHandler {


    // The ApplicationContext
    private Context context;

    // A non-static NotificationManager
    private NotificationManager nManager;

    // The Active Id;
    private int activeId = -1;

    // The last Active Id;
    private int lastActiveId = -1;

    // Current state of the NotificationManager
    private boolean isActive;

    // Interval between available notifications
    private int NOTIFICATION_INTERVAL;
    private int DEFAULT_NOTIFICATION_INTERVAL = 60000;

    // Number of notifications since last initialation
    private int NOTIFICATION_NO = 0;

    // The NotificationPoppulator
    NotificationPoppulator nPoppulator;

    public NotificationHandler(Context context) {

        this.context = context;

        this.NOTIFICATION_INTERVAL = this.DEFAULT_NOTIFICATION_INTERVAL;

        this.isActive = false;

        this.nPoppulator = new NotificationPoppulator(context);

        this.nManager = (NotificationManager)context.getSystemService(context.NOTIFICATION_SERVICE);


    }

    private Notification createNotification(NotificationData data) {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this.context);

        builder.setTicker(data.getTickerTitle());
        builder.setContentTitle(data.getTitle());

        builder.setContentText(data.getText());

        // context.getResources().getDrawable(R.drawable.icon); med okkar icon
        builder.setSmallIcon(data.getIcon());

        // Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        builder.setSound(data.getSound());

        builder.setVibrate(new long[] { Long.valueOf(data.getVibrationLength()) } );

        // Startin intents
        Intent nIntent = new Intent(this.context, SpecificDetails.class);
        nIntent.putExtra("locationID", Integer.toString(data.getId()));
        PendingIntent pIntent = PendingIntent.getActivity(this.context, 0, nIntent, 0);

        builder.setContentIntent(pIntent);

        builder.setAutoCancel(true);

        return builder.build();
    }




    public void addNotification(int id) {

        if(this.isActive || this.activeId == id) {
            return;
        }

        NotificationData nData = nPoppulator.createNotificationData(id);

        Notification notification = createNotification(nData);

        this.activeId = id;
        this.isActive = true;

        this.nManager.notify(id, notification);

        // TODO: remove debbugging trials
        CountDownTimer cTimer = new CountDownTimer(1000 * 32, 1000 * 15) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                nManager.cancel(activeId);
                activeId = -1;
                isActive = false;
            }
        }.start();

        //Runnable r = new UpdateActive(id, 1000 * 60 * 5);
        //new Thread(r).start();

        //this.manageTime(data.getId());

    }

    




}
