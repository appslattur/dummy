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
    private ArrayList<Integer> oldIds;

    // Storage Time
    private int STANDARD_STORAGE_INTERVAL = 1000 * 60 * 30;

    public SpamHandler() {
        this.oldIds = new ArrayList<Integer>();
    }

    /////
    // Private Methods
    /////
    private void removeOldId(int id) {
        this.oldIds.remove(id);
    }

    /////
    // Public Methods
    ////

    public boolean isLegal(int id) {
        if(this.oldIds.indexOf(id) == -1) {
            return true;
        }
        else {
            return false;
        }
    }

    public void addId(int id) {

        this.oldIds.add(id);
        int placement = this.oldIds.indexOf(id);
        Timer nTimer = new Timer();
        nTimer.schedule(new SpamTimerTask(id), this.STANDARD_STORAGE_INTERVAL);

    }

    public class SpamTimerTask extends TimerTask {

        private int id;

        public SpamTimerTask(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            removeOldId(this.id);
        }
    }
}
