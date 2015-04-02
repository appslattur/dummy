package appslattur.appslatturdemo.NotificationHandler;

import android.util.Log;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by manielm on 3/21/2015.
 */
public class SpamHandler {

    // Storage for oldIds
    private ArrayList<String> oldIds;

    // Storage Time
    private int STANDARD_STORAGE_INTERVAL = 1000 * 60 * 30;

    public SpamHandler() {
        this.oldIds = new ArrayList<String>();
    }

    /////
    // Private Methods
    /////
    private void removeOldId(String id) {
        this.oldIds.remove(id);
    }

    /////
    // Public Methods
    ////

    public boolean isLegal(int id) {
        if(this.oldIds.indexOf(id) == -1) {
            return true;
        }

        return false;
    }

    public void addId(int id) {

        String newId = "";
        try {
            newId = Integer.toString(id);
        }
        catch (Exception e) {
            Log.w("Exception", e);
            return;
        }

        this.oldIds.add(newId);

        Timer nTimer = new Timer();
        nTimer.schedule(new SpamTimerTask(newId), this.STANDARD_STORAGE_INTERVAL);

    }

    public class SpamTimerTask extends TimerTask {

        private String id;

        public SpamTimerTask(String id) {
            this.id = id;
        }

        @Override
        public void run() {
            removeOldId(this.id);
        }
    }
}
