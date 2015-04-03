package appslattur.appslatturdemo.DataBaseHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by arnarjons on 3.4.2015.
 */
public class DatabaseController {

    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;
    private String[] allColumns = {
            DatabaseHelper.COLUMN_ID,
            DatabaseHelper.COLUMN_LATITUDE,
            DatabaseHelper.COLUMN_LONGITUDE,
            DatabaseHelper.COLUMN_ENABLED
    };

    public DatabaseController(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void insertEntry(DatabaseEntry entry) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_LATITUDE, entry.getLatitude());
        values.put(DatabaseHelper.COLUMN_LONGITUDE, entry.getLongitude());
        values.put(DatabaseHelper.COLUMN_ENABLED, entry.getEnableStatus());
        long insertId = db.insert(DatabaseHelper.TABLE_NAME, null, values);
    }

    public void insertEntries(ArrayList<DatabaseEntry> entries) {
        for(DatabaseEntry entry : entries) {
            insertEntry(entry);
        }
    }

    public DatabaseValue retrieveSpecificValue(int id) {
        Cursor cursor = db.query(DatabaseHelper.TABLE_NAME,
                allColumns,
                DatabaseHelper.COLUMN_ID + " = " + id,
                null, null, null, null);
        cursor.moveToFirst();
        DatabaseValue value = new DatabaseValue(cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getInt(3));
        cursor.close();

        return value;
    }

    public ArrayList<DatabaseValue> retrieveAllValues() {
        ArrayList<DatabaseValue> allValues = new ArrayList<>();

        Cursor cursor = db.query(DatabaseHelper.TABLE_NAME,
                allColumns, null, null, null, null, null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {
            DatabaseValue value = new DatabaseValue(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3)
            );
            allValues.add(value);
        }
        cursor.close();

        return allValues;
    }

    public void removeEntry(int id) {
        db.delete(DatabaseHelper.TABLE_NAME,
                DatabaseHelper.COLUMN_ID + " = " + id,
                null);
    }

    public int getRowCount() {
        Cursor cursor = db.query(DatabaseHelper.TABLE_NAME,
                allColumns, null, null, null, null, null);
        int rowCount = cursor.getCount();
        cursor.close();
        return rowCount;
    }

}
