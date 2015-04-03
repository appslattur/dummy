package appslattur.appslatturdemo.DataBaseHandler;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by arnarjons on 3.4.2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "AppslatturDB.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "StudentCard";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_LATITUDE = "Latitude";
    public static final String COLUMN_LONGITUDE = "Longitude";
    public static final String COLUMN_ENABLED = "Enabled";

    private static final String DATABASE_CREATE =
            "CREATE TABLE " + TABLE_NAME + "(" +
                    COLUMN_ID + "INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_LATITUDE + " TEXT NOT NULL, " +
                    COLUMN_LONGITUDE + " TEXT NOT NULL, " +
                    COLUMN_ENABLED + "INTEGER NOT NULL" + ");";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


}
