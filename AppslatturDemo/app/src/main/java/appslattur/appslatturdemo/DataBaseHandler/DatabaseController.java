package appslattur.appslatturdemo.DataBaseHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import java.sql.SQLException;
import java.util.ArrayList;

import appslattur.appslatturdemo.RadarHandler.RadarIterable;

/**
 * @author Arnar Jonsson, Ari Freyr Gudmundsson
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

    /**
     * DatabaseController
     * Allows connection to the Appslattur database
     * Do not use unless called inside an <b>AsyncTask</b>
     * or other <b>threads</b> separated from the main thread
     * @param context From the calling application
     */
    public DatabaseController(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    /**
     * Opens the Appslattur database
     * @throws SQLException
     */
    public void open() throws SQLException {
        db = dbHelper.getWritableDatabase();
    }

    /**
     * Closes the Appslattur database
     */
    public void close() {
        dbHelper.close();
    }

    /////
    // DatabaseEntryTask functions
    /////
    // TODO : Add insert primary key error handling check thingies, app does not die if err occ.

    /**
     * insertFSEntry(DatabaseEntry entry)
     * Inserts a single DatabaseEntry to Appslattur database
     * main table, and its helper table if needs be
     * @param entry DatabaseEntry destined for insertion
     * @return ID of the new table row if successful, -1 if unsuccessful
     */
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

    /**
     * insertFSTSEntry(DatabaseEntry entry)
     * Inserts a single DatabaseEntry into Appslattur database
     * secondary helper table
     * @param entry DatabaseEntry destined for insertion
     * @return ID of the new table row if successful, -1 if unsuccessful
     */
    private long insertFSTSEntry(DatabaseEntry entry) {
        ContentValues values = new ContentValues();
        values.put(FSTS_allColumns[0], entry.getId());
        values.put(FSTS_allColumns[1], entry.getTimeStart());
        values.put(FSTS_allColumns[2], entry.getTimeStop());
        long insertId = db.insert(DatabaseHelper.FSTS_TABLE_NAME, null, values);
        values.clear();
        return insertId;
    }

    /**
     * insertFSMGEntry(DatabaseEntry entry)
     * @param entry DatabaseEntry destined for insertion
     * @return ID of the new table row if successful, -1 if unsuccessful
     */
    private long insertFSMGEntry(DatabaseEntry entry) {
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

    /**
     * insertEntry(DatabaseEntry entry)
     * Control flow function of the DatabaseEntryTask thread
     * @param entry DatabaseEntry destined for insertion
     * @return ID of the new table row if successful, -1 if unsuccessful
     */
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
                insertState = insertFSMGEntry(entry);
            default:
                break;
        }
        return insertState;
    }

    /////
    // DatabaseValueTask functions
    /////

    /**
     * getFSTable()
     * @return All rows of the Appslattur database main table
     */
    public DatabaseValue[] getFSTable() {
        DatabaseValue[] allValues =
                new DatabaseValue[getRowCount(DatabaseHelper.FS_TABLE_NAME)];

        final String queryFS = "SELECT * FROM " +
                    DatabaseHelper.FS_TABLE_NAME +
                    " ORDER BY " + FS_allColumns[0] + ";";

        Cursor cursor = db.rawQuery(queryFS, null);
        cursor.moveToFirst();

        int rowCount = 0;
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
            allValues[rowCount++] = dbValue;
            cursor.moveToNext();
        }
        cursor.close();
        return allValues;
    }

    /**
     * getFSTSTable()
     * @return All rows of the Appslattur database second (helper) table
     */
    public DatabaseValue[] getFSTSTable() {
        DatabaseValue[] allValues =
                new DatabaseValue[getRowCount(DatabaseHelper.FSTS_TABLE_NAME)];

        final String query = "SELECT * FROM " +
                DatabaseHelper.FSTS_TABLE_NAME +
                " ORDER BY " + FSTS_allColumns[0] + ";";

        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        int valueCount = 0;
        while(!cursor.isAfterLast()) {
            DatabaseValue dbValue = new DatabaseValue(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2)
            );
            allValues[valueCount++] = dbValue;
            cursor.moveToNext();
        }
        cursor.close();
        return allValues;
    }

    /**
     * getFSMGTable()
     * @return All rows of the Appslattur database third (helper) table
     */
    public DatabaseValue[] getFSMGTable() {
        DatabaseValue[] allValues =
                new DatabaseValue[getRowCount(DatabaseHelper.FSMG_TABLE_NAME)];

        final String queryFSMG = "SELECT * FROM " +
                DatabaseHelper.FSMG_TABLE_NAME +
                " ORDER BY " + FSMG_allColumns[0] + ";";

        Cursor cursor = db.rawQuery(queryFSMG, null);
        cursor.moveToFirst();

        int rowCount = 0;
        while(!cursor.isAfterLast()) {
            DatabaseValue dbValue = new DatabaseValue(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getInt(4)
            );
            allValues[rowCount++] = dbValue;
            cursor.moveToNext();
        }
        cursor.close();
        return allValues;
    }

    /**
     * getFSEntry(id)
     * Retrieves specific data from Appslattur database main table
     * @param id ID of row to retrieve
     * @return DatabaseValue corresponding to the retrieved row data
     */
    public DatabaseValue getFSEntry(int id) {
        final String queryFSE = "SELECT * " +
                "FROM " + DatabaseHelper.FS_TABLE_NAME +
                " WHERE " + DatabaseHelper.FS_COLUMN_ID + " = " + id + ";";

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

    /**
     * getFSTSEntry(id)
     * Retrieves specific data from Appslatturs database second table
     * @param id ID of row to retrieve
     * @return DatabaseValue corresponding to the retrieved row data
     */
    public DatabaseValue getFSTSEntry(int id) {
        final String queryFSTSE = "SELECT * " +
                "FROM " + DatabaseHelper.FSTS_TABLE_NAME +
                " WHERE " + DatabaseHelper.FSTS_COLUMN_ID + " = " + id + ";";

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

    /**
     * getFSMGEntry(id)
     * Retrieves specific data from Appslattur third table
     * @param id ID of row to retrieve
     * @return DatabaseValue corresponding to the retrieved row data
     */
    public DatabaseValue getFSMGEntry(int id) {
        final String queryFSMGE = "SELECT * " +
                "FROM " + DatabaseHelper.FSMG_TABLE_NAME +
                " WHERE " + DatabaseHelper.FSMG_COLUMN_ID + " = " + id + ";";

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

    /////
    // DatabaseIterationTask functions
    /////

    /////
    //  Special static integers for special Iteration functions

        public static final int ITERABLE_SWITCH_ONE = 0;
        public static final String ITERABLE_KEY_ONE = "General";

    //
    /////

    /**
     * getRI_FIRSTSWITCH()
     * @return Array of RadarIterable objects according to the rules:
     *                  (id, latitude, longitude, hasTimeLimit)
     *                  from table FS where mallgroup = general
     *                  (id, timeStart, timeStop)
     *                  from table FSTS (if needed)
     *                  (id, latitude, longitude)
     *                  from table FSMG
     */
    private RadarIterable[] getRI_FIRSTSWITCH() {

        final String query_FS = "SELECT (" +
                DatabaseHelper.FS_COLUMN_ID + ", " +
                DatabaseHelper.FS_COLUMN_LATITUDE + ", " +
                DatabaseHelper.FS_COLUMN_LONGITUDE + ", " +
                DatabaseHelper.FS_COLUMN_HASTIMELIMIT + ", " +
                DatabaseHelper.FS_COLUMN_PINGRADIUS + ") " +
                "FROM " + DatabaseHelper.FS_TABLE_NAME + " " +
                "WHERE " + DatabaseHelper.FS_COLUMN_MALLGROUP + " " +
                "= " + ITERABLE_KEY_ONE + ";";

        Cursor cursor_FS = db.rawQuery(query_FS, null);

        RadarIterable[] iterable_dump_FS =
                new RadarIterable[cursor_FS.getCount()];

        cursor_FS.moveToFirst();
        int dumpCount = 0;
        while(!cursor_FS.isAfterLast()) {

            RadarIterable rIterable = new RadarIterable();

            rIterable.setId(cursor_FS.getInt(0));
            rIterable.setLatitude(cursor_FS.getString(1));
            rIterable.setLongitude(cursor_FS.getString(2));
            rIterable.setTimeLimitFlag(cursor_FS.getInt(3));
            rIterable.setPingRadius(cursor_FS.getInt(4));

            iterable_dump_FS[dumpCount++] = rIterable;

            cursor_FS.moveToNext();
        }

        cursor_FS.close();

        String query_FSTS_UNPREPARED = "SELECT (" +
                DatabaseHelper.FSTS_COLUMN_TIMESTART + ", " +
                DatabaseHelper.FSTS_COLUMN_TIMESTOP + ") " +
                "FROM " + DatabaseHelper.FSTS_TABLE_NAME + " " +
                "WHERE " + DatabaseHelper.FSTS_COLUMN_ID + " " +
                "= ";

        for(RadarIterable FS_DUMP : iterable_dump_FS) {
            if(FS_DUMP.getTimeLimitFlag()) {
                String query_FSTS =
                        query_FSTS_UNPREPARED +
                        FS_DUMP.getId() + ";";
                Cursor cursor_FSTS = db.rawQuery(query_FSTS, null);
                // TODO : Check For Infinite Loop on Single rawQuery moveToFirst() call
                cursor_FSTS.moveToFirst();

                FS_DUMP.setTimeStart(cursor_FSTS.getString(0));
                FS_DUMP.setTimeStop(cursor_FSTS.getString(1));

                cursor_FSTS.close();
            }
        }

        final String query_FSMG = "SELECT (" +
                DatabaseHelper.FSMG_COLUMN_ID + ", " +
                DatabaseHelper.FSMG_COLUMN_LATITUDE + ", " +
                DatabaseHelper.FSMG_COLUMN_LONGITUDE + ", " +
                DatabaseHelper.FSMG_COLUMN_PINGRADIUS + ") " +
                "FROM " + DatabaseHelper.FSMG_TABLE_NAME + ";";

        Cursor cursor_FSMG = db.rawQuery(query_FSMG, null);

        RadarIterable[] iterable_dump_FSMG =
                new RadarIterable[cursor_FSMG.getCount()];

        cursor_FSMG.moveToFirst();
        dumpCount = 0;
        while(!cursor_FSMG.isAfterLast()) {

            RadarIterable rIterable = new RadarIterable();
            rIterable.setId(cursor_FSMG.getInt(0));
            rIterable.setLatitude(cursor_FSMG.getString(1));
            rIterable.setLongitude(cursor_FSMG.getString(2));
            rIterable.setPingRadius(cursor_FSMG.getInt(3));

            iterable_dump_FSMG[dumpCount++] = rIterable;

            cursor_FSMG.moveToNext();
        }

        cursor_FSMG.close();

        RadarIterable[] allValues = new RadarIterable
                [iterable_dump_FS.length + iterable_dump_FSMG.length];

        dumpCount = 0;
        for(RadarIterable rI1 : iterable_dump_FS) {
            allValues[dumpCount++] = rI1;
        }

        for(RadarIterable rI2 : iterable_dump_FSMG) {
            allValues[dumpCount++] = rI2;
        }

        return allValues;
    }

    /**
     * getRadarIterables
     * @param staticHelpId Flow control signal
     * @return Array of RadarIterable objects according to the rule
     *              stated by the staticHelpId
     */
    public RadarIterable[] getRadarIterables(int staticHelpId) {
        switch (staticHelpId) {
            case ITERABLE_SWITCH_ONE:
                return getRI_FIRSTSWITCH();
            default:
                return null;
        }
    }

    /////
    // DatabaseUpdateTask functions
    /////

    /////
    // Utility methods
    /////

    /**
     * getRowCount(tableName)
     * @param tableName Name of the table you want to check on
     * @return Row count of the table in question
     */
    public int getRowCount(String tableName) {

        final String FScount =
                        "SELECT " + DatabaseHelper.FS_COLUMN_ID +
                        " FROM " + DatabaseHelper.FS_TABLE_NAME + ";";
        final String FSTScount =
                "SELECT " + DatabaseHelper.FSTS_COLUMN_ID +
                        " FROM " + DatabaseHelper.FSTS_TABLE_NAME + ";";
        final String FSMGcount =
                "SELECT " + DatabaseHelper.FSMG_COLUMN_ID +
                        " FROM " + DatabaseHelper.FSMG_TABLE_NAME + ";";


        Cursor cursor;

        switch (tableName) {
            case DatabaseHelper.FS_TABLE_NAME:
                cursor = db.rawQuery(FScount, null);
                return cursor.getCount();
            case DatabaseHelper.FSTS_TABLE_NAME:
                cursor = db.rawQuery(FSTScount, null);
                return cursor.getCount();
            case DatabaseHelper.FSMG_TABLE_NAME:
                cursor = db.rawQuery(FSMGcount, null);
                return cursor.getCount();
            default:
                return 0;

        }
    }


}
