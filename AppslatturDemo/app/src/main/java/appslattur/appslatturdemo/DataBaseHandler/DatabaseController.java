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

    private String[] INITIAL_allColumns = {
            DatabaseHelper.INIT_COLUMN_ID,
            DatabaseHelper.INIT_COLUMN_LATITUDE,
            DatabaseHelper.INIT_COLUMN_LONGITUDE,
            DatabaseHelper.INIT_COLUMN_CARDGROUP,
            DatabaseHelper.INIT_COLUMN_MALLGROUP,
            DatabaseHelper.INIT_COLUMN_HASTIMELIMIT,
            DatabaseHelper.INIT_COLUMN_LONGDESCRIPTION,
            DatabaseHelper.INIT_COLUMN_SHORTDESCRIPTION,
            DatabaseHelper.INIT_COLUMN_ENABLED
    };

    private String[] SECONDARY_allColumns = {
            DatabaseHelper.SEC_COLUMN_ID,
            DatabaseHelper.SEC_COLUMN_TIMESTART,
            DatabaseHelper.SEC_COLUMN_TIMESTOP
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

    private long insertInitialEntry(DatabaseEntry entry) {
        ContentValues values = new ContentValues();
        //values.put(INITIAL_allColumns[0], entry.getId());
        values.put(INITIAL_allColumns[1], entry.getLatitude());
        values.put(INITIAL_allColumns[2], entry.getLongitude());
        values.put(INITIAL_allColumns[3], entry.getCardGroup());
        values.put(INITIAL_allColumns[4], entry.getMallGroup());
        values.put(INITIAL_allColumns[5], entry.hasTimeLimit());
        values.put(INITIAL_allColumns[6], entry.getLongDescription());
        values.put(INITIAL_allColumns[7], entry.getShortDescription());
        values.put(INITIAL_allColumns[8], entry.isEnabled());
        long insertId = db.insert(DatabaseHelper.INIT_TABLE_NAME, null, values);
        values.clear();
        return insertId;
    }

    private long insertSecondaryEntry(DatabaseEntry entry) {
        ContentValues values = new ContentValues();
        values.put(SECONDARY_allColumns[0], entry.getId());
        values.put(SECONDARY_allColumns[1], entry.getTimeStart());
        values.put(SECONDARY_allColumns[2], entry.getTimeStop());
        long insertId = db.insert(DatabaseHelper.SEC_TABLE_NAME, null, values);
        values.clear();
        return insertId;
    }

    public long insertEntry(DatabaseEntry entry) {
        long insertState = 0;
        switch (entry.getType()) {
            case DatabaseEntry.INITIAL_QUERY:
                insertState = insertInitialEntry(entry);
                break;
            case DatabaseEntry.SECONDARY_QUERY:
                insertState = insertSecondaryEntry(entry);
                break;
            default:
                break;
        }
        return insertState;
    }


    public ArrayList<DatabaseValue> getInitialTable(int flag) {
        ArrayList<DatabaseValue> allValues = new ArrayList<>();

        final String query = "SELECT * FROM " +
                    DatabaseHelper.INIT_TABLE_NAME +
                    " ORDER BY " + INITIAL_allColumns[0];

        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {
            DatabaseValue dbValue = new DatabaseValue(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getInt(5),
                    cursor.getString(6),
                    cursor.getString(7),
                    cursor.getInt(8)
            );
            allValues.add(dbValue);
            cursor.moveToNext();
        }
        cursor.close();
        return allValues;
    }



    /*
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
    */
    /*
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
*/
    /*
    public void removeEntry(int id) {
        db.delete(DatabaseHelper.TABLE_NAME,
                DatabaseHelper.COLUMN_ID + " = " + id,
                null);
    }
    */

    public int getRowCount() {
        Cursor cursor = db.query(DatabaseHelper.INIT_TABLE_NAME,
                INITIAL_allColumns, null, null, null, null, null);
        int rowCount = cursor.getCount();
        cursor.close();
        return rowCount;
    }


}
