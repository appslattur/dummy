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

    private int DATABASE_ENTRY_CODE;

    public DatabaseEntryTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        dbController = new DatabaseController(context);
        try {
            dbController.open();
            DATABASE_ENTRY_CODE = 1;
        }
        catch (SQLException e) {
            DATABASE_ENTRY_CODE = 0;
        }

    }

    @Override
    protected Long doInBackground(DatabaseEntry... entry) {

        if(entry == null) {
            return new Long(-1);
        }

        switch (DATABASE_ENTRY_CODE) {
            case 1:
                if(entry.length == 1) {
                    return dbController.insertEntry(entry[0]);
                }
                else {
                    for(DatabaseEntry ent : entry) {
                        long insertId = dbController.insertEntry(ent);
                        if(insertId == -1) {
                            return  insertId;
                        }
                    }
                    return new Long(1);
                }
            default:
                return new Long(-1);
        }
    }

    @Override
    protected void onPostExecute(Long id) {
        dbController.close();
    }
}
