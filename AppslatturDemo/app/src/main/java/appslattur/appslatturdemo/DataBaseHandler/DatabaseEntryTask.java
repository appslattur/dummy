package appslattur.appslatturdemo.DataBaseHandler;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import java.sql.SQLException;

/**
 * Created by arnarjons on 8.4.2015.
 */
public class DatabaseEntryTask extends AsyncTask<DatabaseEntry, Void, Long> {

    private Context context;
    private DatabaseController dbController;

    private boolean DATABASE_CONN = false;

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
