package appslattur.appslatturdemo.DatabaseHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by arnarjons on 2.4.2015.
 */
public class FSDatabase extends SQLiteOpenHelper {

    public static final String DATABAS_NAME = "FSDatabase.db";
    public static final String DATABASE_MAIN_TABLE = "StudentCard";

    public static final int DATABASE_VERSION = 1;

    public FSDatabase(Context context) {
        super(context, DATABAS_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        String newTable =
                "CREATE TABLE IF NOT EXISTS " + DATABASE_MAIN_TABLE + " (" +
                        "ID INTEGER PRIMARY KEY AUTOINCREMENT, "+
                        "NAME VARCHAR NOT NULL, "   +
                        "LATITUDE TEXT NOT NULL, "  +
                        "LONGITUDE TEXT NOT NULL, " +
                        "ENABLED INTEGER NOT NULL, "   +
                        "SHORTDESC TEXT NOT NULL, " +
                        "LONGDESC TEXT NOT NULL, "  +
                        "DISCOUNT TEXT"             +
                        "GROUP TEXT"                + ");";
        db.execSQL(newTable);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
