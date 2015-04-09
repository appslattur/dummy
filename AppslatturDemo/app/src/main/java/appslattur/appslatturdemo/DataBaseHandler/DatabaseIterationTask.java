package appslattur.appslatturdemo.DataBaseHandler;

import android.content.Context;
import android.os.AsyncTask;

import appslattur.appslatturdemo.RadarHandler.RadarIterable;

/**
 * Created by arnarjons on 9.4.2015.
 */
public class DatabaseIterationTask extends AsyncTask<Void, Void, RadarIterable[]> {

    private Context context;
    private String controlString;

    private DatabaseController dbController;

    private boolean DATABASE_CONN = false;

    public DatabaseIterationTask(Context context, String controlString) {
        this.context = context;
        this.controlString = controlString;

        dbController = new DatabaseController(context);
    }

    @Override
    protected void onPreExecute() {
        try {
            dbController.open();
            DATABASE_CONN = true;
        }
        catch (Exception e) {
            // Do nothing
        }
    }

    @Override
    protected RadarIterable[] doInBackground(Void... args) {
        if(!DATABASE_CONN) {
            return null;
        }

        switch (controlString) {
            case "RadarIterations":
                try {
                    return dbController.getRadarIterables();
                }
                catch (Exception e) {
                    return null;
                }
            default:
                return null;
        }
    }

    @Override
    protected void onPostExecute(RadarIterable[] iterables) {
        dbController.close();
    }
}
