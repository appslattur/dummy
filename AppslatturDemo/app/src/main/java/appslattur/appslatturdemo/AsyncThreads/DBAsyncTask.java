package appslattur.appslatturdemo.AsyncThreads;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.sql.SQLException;

import appslattur.appslatturdemo.DataBaseHandler.DatabaseController;
import appslattur.appslatturdemo.DataStructure.DBEntryFS;
import appslattur.appslatturdemo.DataStructure.DBEntryObject;
import appslattur.appslatturdemo.DataStructure.DBEntryRESPONSE;

/**
 * Created by arnarjons on 10.4.2015.
 */
public class DBAsyncTask extends AsyncTask<DBEntryObject, Void, DBEntryObject[]> {

    ///
    // Task variables
    ///
    private Context context;
    private boolean DATABASE_CONN = false;

    private boolean ERROR_RESPONSE_FLAG = false;
    private DBEntryRESPONSE errorResponse[] = new DBEntryRESPONSE[1];

    ///
    // Accesspoint to a Database
    ///
    private DatabaseController dbController;

    public DBAsyncTask(Context context) {
        this.context = context;

        this.dbController = new DatabaseController(context);
    }

    @Override
    protected void onPreExecute() {
        dbController = new DatabaseController(context);
        try {
            dbController.open();
            DATABASE_CONN = true;
        }
        catch (SQLException e) {
            ERROR_RESPONSE_FLAG = true;
            DBEntryRESPONSE newError = new DBEntryRESPONSE(
                    40001,
                    "Failed to connect to database",
                    true
            );
            errorResponse[0] = newError;
        }
    }

    @Override
    protected DBEntryObject[] doInBackground(DBEntryObject... args) {
        if(ERROR_RESPONSE_FLAG) {
            return errorResponse;
        }

        int inputLength = args.length;

        ///
        // Responses
        ///
        switch (args[0].getType()) {
            case DBEntryObject.DATABASE_INSERT:
                try {
                    DBEntryRESPONSE[] newResponse = new DBEntryRESPONSE[1];
                    newResponse[0] = dbController.insertEntry(args);
                    return newResponse;
                }
                catch (Exception e) {
                    Log.w("Exception", e);
                    ERROR_RESPONSE_FLAG = true;
                    DBEntryRESPONSE newError = new DBEntryRESPONSE(
                            40003,
                            "SQL execution resolved in termination",
                            true
                    );
                    errorResponse[0] = newError;
                    return errorResponse;
                }
            default:
                ERROR_RESPONSE_FLAG = true;
                DBEntryRESPONSE newError = new DBEntryRESPONSE(
                        40002,
                        "Failed to recognize entry flow in Asynctask-default",
                        true
                );
                errorResponse[0] = newError;
                return errorResponse;
        }

    }

    @Override
    protected void onPostExecute(DBEntryObject[] args) {
        dbController.close();
    }
}
