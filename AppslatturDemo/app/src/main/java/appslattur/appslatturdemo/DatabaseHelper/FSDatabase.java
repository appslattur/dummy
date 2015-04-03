package appslattur.appslatturdemo.DatabaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import appslattur.appslatturdemo.RadarHandler.RadarScannerIterable;

/**
 * Created by arnarjons on 2.4.2015.
 */
public class FSDatabase extends SQLiteOpenHelper {

    private static final String DATABAS_NAME = "FSDatabase.db";
    private static final String DATABASE_MAIN_TABLE = "StudentCard";

    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public FSDatabase(Context context) {
        super(context, DATABAS_NAME, null, DATABASE_VERSION);
        this.db = this.getWritableDatabase();
    }

    public void onCreate(SQLiteDatabase db) {
        String newTable =
                "CREATE TABLE IF NOT EXISTS " + DATABASE_MAIN_TABLE + " (" +
                        "ID INTEGER PRIMARY KEY AUTOINCREMENT, "+
                        "LATITUDE TEXT NOT NULL, "  +
                        "LONGITUDE TEXT NOT NULL, " +
                        "ENABLED INTEGER NOT NULL"  +   ");";
        db.execSQL(newTable);
    }



    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addEntry(FSDatabaseEntry entry) {
        ContentValues values = new ContentValues();
        values.put("LATITUDE", entry.getLatitude());
        values.put("LONGITUDE", entry.getLongitude());
        values.put("ENABLED", entry.getEnableStatus());
        this.db.insert(this.DATABASE_MAIN_TABLE, null, values);
        values.clear();
    }

    public void addEntries(ArrayList<FSDatabaseEntry> entries) {
        for(FSDatabaseEntry entry : entries) {
            addEntry(entry);
        }
    }

    public ArrayList<RadarScannerIterable> getIterableArrayList() {
        ArrayList<RadarScannerIterable> iterableObjects =
                new ArrayList<RadarScannerIterable>();

        String query = "SELECT (ID, LATITUDE, LONGITUDE, ENABLED) FROM " + this.DATABASE_MAIN_TABLE;

        Cursor cursor = this.db.rawQuery(query, null);

        if( cursor.moveToFirst() ) {

            do {
                int id = cursor.getInt(0);
                String latitude = cursor.getString(1);
                String longitude = cursor.getString(2);
                int isEnabled = cursor.getInt(3);
                RadarScannerIterable rsIterable = new
                        RadarScannerIterable(   id,
                                                latitude,
                                                longitude,
                                                isEnabled);

                iterableObjects.add(rsIterable);
            }
            while (cursor.moveToNext());

        }

        cursor.close();

        if( iterableObjects.size() < 1 ) {
            return null;
        }

        return iterableObjects;
    }
}
