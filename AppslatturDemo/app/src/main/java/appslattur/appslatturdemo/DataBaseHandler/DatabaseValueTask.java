package appslattur.appslatturdemo.DataBaseHandler;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by arnarjons on 8.4.2015.
 */
public class DatabaseValueTask extends AsyncTask<Integer, Void, DatabaseValue> {

    private Context context;
    private String controlString;

    private DatabaseController dbController;

    private int DATABASE_ENTRY_CODE = 0;

    public DatabaseValueTask(Context context, String controlString) {
        this.context = context;
        this.controlString = controlString;

        dbController = new DatabaseController(context);
    }


    @Override
    protected void onPreExecute() {
        try {
            dbController.open();
            DATABASE_ENTRY_CODE = 1;
        }
        catch (SQLException e) {
            // Do nothing
        }
    }

    @Override
    protected DatabaseValue doInBackground(Integer... args) {
        if(DATABASE_ENTRY_CODE != 1) {
            return null;
        }

        try {
            switch (controlString) {
                case "StudentCard":
                    try {
                        return dbController.getInitialEntry(args[0]);
                    }
                    catch (Exception e) {
                        return null;
                    }
                case "TimeStamp":
                    try {
                        return dbController.getSecondaryEntry(args[0]);
                    }
                    catch (Exception e) {
                        return null;
                    }
                default:
                    return null;
            }
        }
        catch (Exception e) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(DatabaseValue value) {
        dbController.close();
    }
}
