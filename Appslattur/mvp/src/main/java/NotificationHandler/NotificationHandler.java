package NotificationHandler;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;

/**
 * Created by Arnar Jónsson on 9.2.2015.
 */
public class NotificationHandler {

    private Context context;

    private NotificationManager nManager;
    private int activeId = -1;
    private int notificationNumber = 0;

    public NotificationHandler(Context context) {

        this.context = context;

        this.nManager = (NotificationManager)context.getSystemService(context.NOTIFICATION_SERVICE);


    }

    private Notification createNotification(NotificationData data, PendingIntent pIntent) {
        NotificationCompat.Builder newBuilder = new NotificationCompat.Builder(this.context);

        newBuilder.setTicker(data.getTickerTitle());
        newBuilder.setContentTitle(data.getTitle());

        // TODO: Remove notification number used for debugging
        newBuilder.setContentText(data.getText() + " " + this.notificationNumber);

        // context.getResources().getDrawable(R.drawable.icon); med okkar icon
        newBuilder.setSmallIcon(data.getIcon());

        // Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        newBuilder.setSound(data.getSound());

        newBuilder.setVibrate(new long[] { Long.valueOf(data.getVibrationLength()) } );

        newBuilder.setContentIntent(pIntent);

        newBuilder.setAutoCancel(true);

        return newBuilder.build();
    }

    private boolean isActive() {
        return this.activeId != -1;
    }

    private void cancelNotification() {

        this.nManager.cancel(this.activeId);
        this.activeId = -1;

    }

    private void manageTime(int id) {
        /*
        Timer timer = new Timer();
        TimerTask notificationLife = new TimerTask() {
            @Override
            public void run() {

            }
        };
        timer.schedule(notificationLife, 20000);
        */

        Handler nHandler = new Handler();
        long delayMS = 20000;
        nHandler.postDelayed(new Runnable() {
            public void run() {
                cancelNotification();
            }
        }, delayMS);

    }


    public void addNotification(NotificationData data, PendingIntent pendingIntent) {

        if(isActive()) {
            return;
        }

        this.activeId = data.getId();

        this.notificationNumber++;

        Notification notification = createNotification(data, pendingIntent);

        nManager.notify(data.getId(), notification);

        this.manageTime(data.getId());

    }

    public int getNotificationNumber() {
        return this.notificationNumber;
    }

    public void forceNotificationNumber(int forcedNumber) {
        this.notificationNumber = forcedNumber;
    }

    public int getActiveId() {
        return this.activeId;
    }
}
