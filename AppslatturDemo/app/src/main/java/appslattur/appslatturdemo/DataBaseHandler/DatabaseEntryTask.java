package appslattur.appslatturdemo.DataBaseHandler;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import java.sql.SQLException;

/**
 * @author Arnar Jonsson
 * @version 0.1
 * @since  8.4.2015.
 */
public class DatabaseEntryTask extends AsyncTask<DatabaseEntry, Void, Long> {

    private Context context;
    private DatabaseController dbController;

    private boolean DATABASE_CONN = false;

    /**
     * DatabaseEntryTask(context)
     * AsyncTask that handles data entry tasks for the Appslattur application
     * Started with a filled DatabaseEntry object
     * Returns the id of the new row (or the last row inserted) on successful entry,
     * and -1 if the entry failed.
     * The first entry to fail when inserting multiple objects will result in Task termination
     * The task will also return -1 if connection is not established with the database
     * @param context Context of the calling application
     */
    public DatabaseEntryTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        dbController = new DatabaseController(context);
        try {
            dbController.open();
            DATABASE_CONN = true;
        }
        catch (SQLException e) {
            // Do Nothing
        }

    }

    @Override
    protected Long doInBackground(DatabaseEntry... entry) {

        if(!DATABASE_CONN) {
            return new Long(-1);
        }

        if(entry == null || entry.length == 0) {
            return new Long(-1);
        }

        if(entry.length == 1) {
            try {
                return dbController.insertEntry(entry[0]);
            }
            catch (Exception e) {
                return new Long(-1);
            }
        }
        else {
            long insertId = new Long(0);
            for(DatabaseEntry ent : entry) {
                try {
                    insertId = dbController.insertEntry(ent);
                }
                catch (Exception e) {
                    return new Long(-1);
                }
                if(insertId < 0) {
                    return insertId;
                }
            }
            return insertId;
        }
    }

    @Override
    protected void onPostExecute(Long id) {
        dbController.close();
    }
}
