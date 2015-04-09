package appslattur.appslatturdemo.DataBaseHandler;

import android.content.Context;
import android.os.AsyncTask;

import appslattur.appslatturdemo.RadarHandler.RadarIterable;

/**
 * Created by arnarjons on 9.4.2015.
 */
public class DatabaseIterationTask extends AsyncTask<Integer, Void, RadarIterable[]> {

    private Context context;
    private String controlString;

    private DatabaseController dbController;

    private boolean DATABASE_CONN = false;

    /**
     * DatabaseIterationTask(context, controlString)
     * Handles data extraction destined for the Appslattur applications Radar object
     * Returns an array of RadarIterable objects that correspond to the function chosen by the
     * constructor argument controlString
     * Returns null on ether failure or if the task could not connect to the Appslattur database
     * @param context Context of the calling application
     * @param controlString String that controls the flow of the task
     */
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
    protected RadarIterable[] doInBackground(Integer... args) {
        if(!DATABASE_CONN) {
            return null;
        }

        switch (controlString) {
            case "RadarIterations":
                try {
                    return dbController.getRadarIterables(0);
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
