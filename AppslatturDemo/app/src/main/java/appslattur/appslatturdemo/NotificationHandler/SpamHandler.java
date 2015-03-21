package appslattur.appslatturdemo.NotificationHandler;

import android.util.Log;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by manielm on 3/21/2015.
 */
public class SpamHandler {

    // Storage for ids and theyr independet timers
    private ArrayList<Integer> oldIds;
    private ArrayList<Timer> timerTaskArray;

    // Storage Time
    private int STANDARD_STORAGE_INTERVAL = 1000 * 60 * 30;

    public SpamHandler() {
        this.oldIds = new ArrayList<Integer>();
        this.timerTaskArray = new ArrayList<Timer>();
    }

    /////
    // Private Methods
    /////
    private void removeOldId(int id) {
        int placement = this.oldIds.indexOf(id);

        try {
            this.oldIds.remove(placement);
        }
        catch ( Exception e ) {
            Log.w("Exception", e);
        }

        try {
            this.timerTaskArray.remove(placement);
        }
        catch ( Exception r ) {
            Log.w("Exception", r);
        }

        this.oldIds.trimToSize();
        this.timerTaskArray.trimToSize();
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
        if( this.oldIds.indexOf(id) != -1) {
            return;
        }

        this.oldIds.add(id);
        int placement = this.oldIds.indexOf(id);
        Timer nTimer = new Timer();
        nTimer.schedule(new SpamTimerTask(id), this.STANDARD_STORAGE_INTERVAL);
        this.timerTaskArray.add(placement, nTimer);

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
