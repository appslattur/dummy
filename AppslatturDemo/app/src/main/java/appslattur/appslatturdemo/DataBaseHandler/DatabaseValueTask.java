package appslattur.appslatturdemo.DataBaseHandler;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by arnarjons on 8.4.2015.
 */
public class DatabaseValueTask extends AsyncTask<Integer, Void, DatabaseValue[]> {

    private Context context;
    private String controlString;

    private DatabaseController dbController;

    private boolean DATABASE_CONN = false;

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
