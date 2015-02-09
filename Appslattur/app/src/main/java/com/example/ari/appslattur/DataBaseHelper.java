package com.example.ari.appslattur;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.location.Location;

import java.util.ArrayList;

/**
 * Created by Ari on 10.1.2015.
 */
class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "TestLocations.db";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_TABLE_LOCATIONS = "Locations";
    private SQLiteDatabase db;

    public DataBaseHelper(Context ctx) {
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
        this.db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tmpTable =
                "CREATE TABLE IF NOT EXISTS "+DATABASE_TABLE_LOCATIONS+" (" +
                        "ID INTEGER PRIMARY KEY AUTOINCREMENT, "+
                        "LONGITUDE TEXT NOT NULL, "+
                        "LATITUDE TEXT NOT NULL, "+
                        "NAME VARCHAR NOT NULL);";
        db.execSQL(tmpTable);
    }

    public void clearTable(){
        this.db.delete(DATABASE_TABLE_LOCATIONS, null, null);
    }

    public void addLine(String s1, String s2,String s3)
    {
        ContentValues values = new ContentValues();
        values.put("LONGITUDE", s1);
        values.put("LATITUDE", s2);
        values.put("NAME", s3);
        this.db.insert(DATABASE_TABLE_LOCATIONS, null, values);
    }

    public String getLocations(){
        String query = "SELECT * FROM "+ DATABASE_TABLE_LOCATIONS+ " ORDER by ID DESC Limit 50";
        Cursor cursor = this.db.rawQuery(query, null);
        String MASSIVESTRING ="";
        if(cursor.moveToFirst()){
            do{

               String _1 = cursor.getString(1);
               String _2 = cursor.getString(2);
               String _3 = cursor.getString(3);

                String comibined = "((Name:"+_3+",Longitude:"+_1+",Latitude:"+_2+"))";
                MASSIVESTRING+=comibined;

            }
            while(cursor.moveToNext());


        }
        return MASSIVESTRING;
    }

    public ArrayList<Location> getLocationList(){
        String query = "SELECT * FROM "+ DATABASE_TABLE_LOCATIONS+ " ORDER by ID DESC Limit 50";
        Cursor cursor = this.db.rawQuery(query, null);
        ArrayList<Location> myLocations = new ArrayList<Location>();
        if(cursor.moveToFirst()){
            do{
                double longitude = Double.parseDouble(cursor.getString(1));
                double latitude = Double.parseDouble(cursor.getString(2));
                String name = cursor.getString(3);
                Location tempLocation = new Location(name);
                tempLocation.setLongitude(longitude);
                tempLocation.setLatitude(latitude);


                myLocations.add(tempLocation);
            }
            while(cursor.moveToNext());


        }
        return myLocations;
    }

    public void establishConnection()
    {
        this.db = this.getWritableDatabase();
    }

    public void closeConnection()
    {
        this.db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //For later use
    }

}
