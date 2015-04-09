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
 * @author Arnar Jonsson
 * @version 0.2
 * @since  3.4.2015.
 */
public class DatabaseController {

    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    private String[] FS_allColumns = {
            DatabaseHelper.FS_COLUMN_ID,
            DatabaseHelper.FS_COLUMN_LATITUDE,
            DatabaseHelper.FS_COLUMN_LONGITUDE,
            DatabaseHelper.FS_COLUMN_SHOPNAME,
            DatabaseHelper.FS_COLUMN_CARDGROUP,
            DatabaseHelper.FS_COLUMN_MALLGROUP,
            DatabaseHelper.FS_COLUMN_HASTIMELIMIT,
            DatabaseHelper.FS_COLUMN_LONGDESCRIPTION,
            DatabaseHelper.FS_COLUMN_SHORTDESCRIPTION,
            DatabaseHelper.FS_COLUMN_ENABLED,
            DatabaseHelper.FSMG_COLUMN_PINGRADIUS
    };

    private String[] FSTS_allColumns = {
            DatabaseHelper.FSTS_COLUMN_ID,
            DatabaseHelper.FSTS_COLUMN_TIMESTART,
            DatabaseHelper.FSTS_COLUMN_TIMESTOP
    };

    private String[] FSMG_allColumns = {
            DatabaseHelper.FSMG_COLUMN_ID,
            DatabaseHelper.FSMG_COLUMN_LATITUDE,
            DatabaseHelper.FSMG_COLUMN_LONGITUDE,
            DatabaseHelper.FSMG_COLUMN_NAME,
            DatabaseHelper.FSMG_COLUMN_PINGRADIUS
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

    private long insertFSEntry(DatabaseEntry entry) {
        //TODO : HANDLE ID LOGIC CHANGES IF NEED BE
        ContentValues values = new ContentValues();
        //values.put(FS_allColumns[0], entry.getId());
        values.put(FS_allColumns[1], entry.getLatitude());
        values.put(FS_allColumns[2], entry.getLongitude());
        values.put(FS_allColumns[3], entry.getName());
        values.put(FS_allColumns[4], entry.getCardGroup());
        values.put(FS_allColumns[5], entry.getMallGroup());
        values.put(FS_allColumns[6], entry.hasTimeLimit());
        values.put(FS_allColumns[7], entry.getLongDescription());
        values.put(FS_allColumns[8], entry.getShortDescription());
        values.put(FS_allColumns[9], entry.isEnabled());
        values.put(FS_allColumns[10], entry.getPingRadius());
        long insertId = db.insert(DatabaseHelper.FS_TABLE_NAME, null, values);
        values.clear();
        //TODO : INSERT IF TIMESTAMP FOLLOWS
        return insertId;
    }

    private long insertFSTSEntry(DatabaseEntry entry) {
        ContentValues values = new ContentValues();
        values.put(FSTS_allColumns[0], entry.getId());
        values.put(FSTS_allColumns[1], entry.getTimeStart());
        values.put(FSTS_allColumns[2], entry.getTimeStop());
        long insertId = db.insert(DatabaseHelper.FSTS_TABLE_NAME, null, values);
        values.clear();
        return insertId;
    }

    private long insertFTMGEntry(DatabaseEntry entry) {
        ContentValues values = new ContentValues();
        values.put(FSMG_allColumns[0], entry.getId());
        values.put(FSMG_allColumns[1], entry.getLatitude());
        values.put(FSMG_allColumns[2], entry.getLongitude());
        values.put(FSMG_allColumns[3], entry.getName());
        values.put(FSMG_allColumns[4], entry.getPingRadius());
        long insertId = db.insert(DatabaseHelper.FSMG_TABLE_NAME, null, values);
        values.clear();
        return insertId;
    }

    public long insertEntry(DatabaseEntry entry) {
        long insertState = -1;
        switch (entry.getType()) {
            case DatabaseEntry.FS_QUERY:
                insertState = insertFSEntry(entry);
                break;
            case DatabaseEntry.FSTS_QUERY:
                insertState = insertFSTSEntry(entry);
                break;
            case DatabaseEntry.FSMG_QUERY:
                insertState = insertFTMGEntry(entry);
            default:
                break;
        }
        return insertState;
    }


    public ArrayList<DatabaseValue> getFSTable() {
        ArrayList<DatabaseValue> allValues = new ArrayList<>();

        final String queryFS = "SELECT * FROM " +
                    DatabaseHelper.FS_TABLE_NAME +
                    " ORDER BY " + FS_allColumns[0];

        Cursor cursor = db.rawQuery(queryFS, null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {
            DatabaseValue dbValue = new DatabaseValue(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getInt(6),
                    cursor.getString(7),
                    cursor.getString(8),
                    cursor.getInt(9),
                    cursor.getInt(10)
            );
            allValues.add(dbValue);
            cursor.moveToNext();
        }
        cursor.close();
        return allValues;
    }

    public ArrayList<DatabaseValue> getFSTSTable() {
        ArrayList<DatabaseValue> allValues = new ArrayList<>();

        final String query = "SELECT * FROM " +
                DatabaseHelper.FSTS_TABLE_NAME +
                " ORDER BY " + FSTS_allColumns[0];

        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {
            DatabaseValue dbValue = new DatabaseValue(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2)
            );
            allValues.add(dbValue);
            cursor.moveToNext();
        }
        cursor.close();
        return allValues;
    }

    public ArrayList<DatabaseValue> getFSMGTable() {
        ArrayList<DatabaseValue> allValues = new ArrayList<>();

        final String queryFSMG = "SELECT * FROM " +
                DatabaseHelper.FSMG_TABLE_NAME +
                " ORDER BY " + FSMG_allColumns[0];

        Cursor cursor = db.rawQuery(queryFSMG, null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {
            DatabaseValue dbValue = new DatabaseValue(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getInt(4)
            );
            allValues.add(dbValue);
            cursor.moveToNext();
        }
        cursor.close();
        return allValues;
    }

    public DatabaseValue getFSEntry(int id) {
        final String queryFSE = "SELECT * FROM " +
                DatabaseHelper.FS_TABLE_NAME +
                " WHERE " + DatabaseHelper.FS_COLUMN_ID + " = " + id;

        Cursor cursor = db.rawQuery(queryFSE, null);
        cursor.moveToFirst();

        DatabaseValue dbValue = new DatabaseValue(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5),
                cursor.getInt(6),
                cursor.getString(7),
                cursor.getString(8),
                cursor.getInt(9),
                cursor.getInt(10)
        );

        cursor.close();

        return dbValue;
    }

    public DatabaseValue getFSTSEntry(int id) {
        final String queryFSTSE = "SELECT * FROM " +
                DatabaseHelper.FSTS_TABLE_NAME +
                " WHERE " + DatabaseHelper.FSTS_COLUMN_ID + " = " + id;

        Cursor cursor = db.rawQuery(queryFSTSE, null);
        cursor.moveToFirst();

        DatabaseValue dbValue = new DatabaseValue(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2)
        );

        cursor.close();

        return dbValue;
    }

    public DatabaseValue getFSMGEntry(int id) {
        final String queryFSMGE = "SELECT * FROM " +
                DatabaseHelper.FSMG_TABLE_NAME +
                " WHERE " + DatabaseHelper.FSMG_COLUMN_ID + " = " + id;

        Cursor cursor = db.rawQuery(queryFSMGE, null);
        cursor.moveToFirst();

        DatabaseValue dbValue = new DatabaseValue(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getInt(4)
        );

        cursor.close();

        return dbValue;
    }

    /*
    public int getRowCount() {
        Cursor cursor = db.query(DatabaseHelper.INIT_TABLE_NAME,
                INITIAL_allColumns, null, null, null, null, null);
        int rowCount = cursor.getCount();
        cursor.close();
        return rowCount;
    }
    */

}
