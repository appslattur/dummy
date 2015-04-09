package appslattur.appslatturdemo.DataBaseHandler;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Arnar Jonsson
 * @version 0.1
 * @since  8.4.2015.
 */
public class DatabaseValueTask extends AsyncTask<Integer, Void, DatabaseValue[]> {

    private Context context;
    private String controlString;

    private DatabaseController dbController;

    private boolean DATABASE_CONN = false;

    /**
     * DatabaseValueTask(context, controlString)
     * AsyncTask that handles data extraction tasks for the Appslattur application
     * Started with an ID representation of a row placement in the Appslattur database,
     * which table you want to retrieve from is controlled by the constructor argument
     * controlString
     * Starting it with no specific ID returns all rows of the table controlled by the
     * constructor argument controlString
     * Returns a DatabaseValue array containing ether a single result (task called by id)
     * or all results (task called without id) of the specified table
     * The task will return null on ether failure or if it failed to connect to the Appslattur
     * database
     * @param context Context of the calling application
     * @param controlString TableTag (name) of the table you want to retrieve data from
     */
    public DatabaseValueTask(Context context, String controlString) {
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
        catch (SQLException e) {
            // Do nothing
        }
    }

    @Override
    protected DatabaseValue[] doInBackground(Integer... args) {
        if(!DATABASE_CONN) {
            return null;
        }

        DatabaseValue[] returnValues = new DatabaseValue[1];
        try {
            int id = args[0];
            if(controlString.equals("StudentCard")) returnValues[0] = dbController.getFSEntry(id);
            if(controlString.equals("FSTimeStamp")) returnValues[0] = dbController.getFSTSEntry(id);
            if(controlString.equals("FSMallGroup")) returnValues[0] = dbController.getFSMGEntry(id);
            return returnValues;
        }
        catch (NullPointerException e) {
            try {
                if(controlString.equals("StudentCard")) returnValues = dbController.getFSTable();
                if(controlString.equals("FSTimeStamp")) returnValues = dbController.getFSTSTable();
                if(controlString.equals("FSMallGroup")) returnValues = dbController.getFSMGTable();
                return returnValues;
            }
            catch (Exception f) {
                return null;
            }
        }
        catch (Exception e) {
            return null;
        }


    }

    @Override
    protected void onPostExecute(DatabaseValue[] value) {
        dbController.close();
    }
}
