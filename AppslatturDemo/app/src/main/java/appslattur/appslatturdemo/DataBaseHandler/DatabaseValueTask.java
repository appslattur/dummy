package appslattur.appslatturdemo.DataBaseHandler;

import android.content.Context;
import android.os.AsyncTask;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by arnarjons on 8.4.2015.
 */
public class DatabaseValueTask extends AsyncTask<Integer, Void, ArrayList<DatabaseValue>> {

    private Context context;

    private DatabaseController dbController;

    private int DATABASE_ENTRY_CODE = 0;

    public DatabaseValueTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        try {
            dbController.open();
            DATABASE_ENTRY_CODE = 1;
        }
        catch (SQLException e) {

        }
    }

    @Override
    protected ArrayList<DatabaseValue> doInBackground(Integer... args) {
        if(DATABASE_ENTRY_CODE != 1) {
            return null;
        }

        try {
            return dbController.getInitTable();
        }
        catch (Exception e) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(ArrayList<DatabaseValue> aList) {
        dbController.close();
    }
}
