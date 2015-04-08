package appslattur.appslatturdemo.DataBaseHandler;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by arnarjons on 3.4.2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    // DATABASE NAME AND INITIAL VERSION
    private static final String DATABASE_NAME = "AppslatturDB.db";
    private static final int DATABASE_VERSION = 1;

    // INITIAL TABLE OF THE DATABASE
    public static final String INIT_TABLE_NAME = "StudentCard";
    public static final String INIT_COLUMN_ID = "_ID";
    public static final String INIT_COLUMN_LATITUDE = "latitude";
    public static final String INIT_COLUMN_LONGITUDE = "longitude";
    public static final String INIT_COLUMN_CARDGROUP = "cardgroup";
    public static final String INIT_COLUMN_MALLGROUP = "mallgroup";
    public static final String INIT_COLUMN_HASTIMELIMIT = "hastimelimit";
    public static final String INIT_COLUMN_LONGDESCRIPTION = "longdescription";
    public static final String INIT_COLUMN_SHORTDESCRIPTION = "shortdescription";
    public static final String INIT_COLUMN_ENABLED = "enabled";

    // SECONDARY (HELPER TABLE)
    public static final String SEC_TABLE_NAME = "TimeStamps";
    public static final String SEC_COLUMN_ID = "_ID";
    public static final String SEC_COLUMN_TIMESTART = "timestart";
    public static final String SEC_COLUMN_TIMESTOP = "timestop";

    // INITIAL TABLE CREATE STATEMENT
    private static final String INITIAL_DATABASE_CREATE =
            "CREATE TABLE " + INIT_TABLE_NAME + "(" +
                    INIT_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    INIT_COLUMN_LATITUDE + " TEXT NOT NULL, " +
                    INIT_COLUMN_LONGITUDE + " TEXT NOT NULL, " +
                    INIT_COLUMN_CARDGROUP + " TEXT NOT NULL, " +
                    INIT_COLUMN_MALLGROUP + " TEXT, " +
                    INIT_COLUMN_HASTIMELIMIT + " INTEGER NOT NULL, " +
                    INIT_COLUMN_LONGDESCRIPTION + " TEXT NOT NULL, " +
                    INIT_COLUMN_SHORTDESCRIPTION + " TEXT NOT NULL, " +
                    INIT_COLUMN_ENABLED + " INTEGER NOT NULL" + ");";

    // SECONDARY TABLE CREATE STATEMENT
    private static final String SECONDARY_DATABASE_CREATE =
            "CREATE TABLE " + SEC_TABLE_NAME + "(" +
                    SEC_COLUMN_ID + " INTEGER PRIMARY KEY, " +
                    SEC_COLUMN_TIMESTART + " TEXT NOT NULL, " +
                    SEC_COLUMN_TIMESTOP + " TEXT NOT NULL" + ");";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(INITIAL_DATABASE_CREATE);
        db.execSQL(SECONDARY_DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + INITIAL_DATABASE_CREATE);
        db.execSQL("DROP TABLE OF EXISTS " + SECONDARY_DATABASE_CREATE);
        onCreate(db);
    }


}
