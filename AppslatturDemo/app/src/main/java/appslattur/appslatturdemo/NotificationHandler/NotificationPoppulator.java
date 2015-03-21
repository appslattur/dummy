package appslattur.appslatturdemo.NotificationHandler;

import android.content.Context;
import android.media.RingtoneManager;
import android.net.Uri;

import appslattur.appslatturdemo.DatabaseHelper.DataBaseHelper;
import appslattur.appslatturdemo.R;

/**
 * Created by Arnar Jonsson on 3/21/2015.
 */
public class NotificationPoppulator {

    private Context context;

    public NotificationPoppulator(Context context) {
        this.context = context;
    }

    /////
    // Get/Set Methods
    /////

    public Context getContext() {
        return this.context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    /////
    // Private Methods
    /////

    private int getDefaultMipMap() {
        return R.mipmap.ic_launcher;
    }

    private Uri getDefaultRingtone() {
        return RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
    }

    /////
    // Public Methods
    /////

    public NotificationData createNotificationData(int id) {
        NotificationData nData = new NotificationData();

        DataBaseHelper dbHelper = new DataBaseHelper(this.context);

        nData.setId(id);
        nData.setTickerTitle(getContext().getString(R.string.tickerTitle));
        nData.setTitle(dbHelper.getShortDescById(id));
        nData.setText(dbHelper.getLongDescById(id));
        nData.setIcon(getDefaultMipMap());
        nData.setSound(getDefaultRingtone());
        nData.setVibrationLength(500);

        return nData;
    }
}
