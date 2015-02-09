package com.example.ari.protogui;

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

    private static final String DATABASE_NAME = "TestLocationsss.db";
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
                        "NAME VARCHAR NOT NULL, "+
                        "ENABLED TEXT NOT NULL);";
        db.execSQL(tmpTable);
    }

    public void clearTable(){
        this.db.delete(DATABASE_TABLE_LOCATIONS, null, null);
    }


    public void addLine(String longitude, String latitude,String name, String enabled)
    {
        ContentValues values = new ContentValues();
        values.put("LONGITUDE", longitude);
        values.put("LATITUDE", latitude);
        values.put("NAME", name);
        values.put("ENABLED", enabled);
        this.db.insert(DATABASE_TABLE_LOCATIONS, null, values);
    }

    public void updateLocation(int id, int enabled){
        ContentValues values = new ContentValues();
        values.put("ENABLED", String.valueOf(enabled));
        this.db.update(DATABASE_TABLE_LOCATIONS, values, "ID="+id, null);
    }


    public void populateTable(){
        String subway = "Subway";
        String tolvutek = "Tölvutek";
        String start = "Start";
        String bootcamp = "Bootcamp";
        String worldclass = "WorldClass";
        String bullan ="Hamborgarabúlla Tómasar";
        String serrano = "Serranó";


        String templong = "66";
        String templat = "-21";

        addLine(templong, templat, subway, "1");
        addLine(templong, templat, subway, "1");
        addLine(templong, templat, subway, "1");
        addLine(templong, templat, subway, "1");
        addLine(templong, templat, subway, "1");

        addLine(templong, templat, tolvutek, "1");

        addLine(templong, templat, start, "1");

        addLine(templong, templat, bootcamp, "1");

        addLine(templong, templat, worldclass, "1");
        addLine(templong, templat, worldclass, "1");
        addLine(templong, templat, worldclass, "1");
        addLine(templong, templat, worldclass, "1");

        addLine(templong, templat, bullan, "1");
        addLine(templong, templat, bullan, "1");
        addLine(templong, templat, bullan, "1");
        addLine(templong, templat, bullan, "1");
        addLine(templong, templat, bullan, "1");
        addLine(templong, templat, bullan, "1");
        addLine(templong, templat, bullan, "1");
        addLine(templong, templat, bullan, "1");
        addLine(templong, templat, bullan, "1");

        addLine(templong, templat, serrano, "1");
        addLine(templong, templat, serrano, "1");
        addLine(templong, templat, serrano, "1");
        addLine(templong, templat, serrano, "1");
        addLine(templong, templat, serrano, "0");
        addLine(templong, templat, serrano, "0");
        addLine(templong, templat, serrano, "0");
        addLine(templong, templat, serrano, "1");
        addLine(templong, templat, serrano, "1");
        addLine(templong, templat, serrano, "1");
        addLine(templong, templat, serrano, "1");
        addLine(templong, templat, serrano, "1");
        addLine(templong, templat, serrano, "1");
        addLine(templong, templat, serrano, "1");
        addLine(templong, templat, serrano, "1");


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
               int enabled = cursor.getInt(4);

                String comibined = "((Name:"+_3+",Longitude:"+_1+",Latitude:"+_2+"))";
                MASSIVESTRING+=comibined;

            }
            while(cursor.moveToNext());


        }
        return MASSIVESTRING;
    }

    public ArrayList<LocationChain> getLocationList(){
        String query = "SELECT * FROM "+ DATABASE_TABLE_LOCATIONS+ " ORDER by ID DESC Limit 50";
        Cursor cursor = this.db.rawQuery(query, null);
        ArrayList<LocationChain> myLocationChain = new ArrayList<LocationChain>();
        if(cursor.moveToFirst()){
            do{
                double longitude = Double.parseDouble(cursor.getString(1));
                double latitude = Double.parseDouble(cursor.getString(2));
                String name = cursor.getString(3);
                int enabled = cursor.getInt(4);
                int id = cursor.getInt(0);
                boolean matched = false;

                for(LocationChain subChain: myLocationChain){
                    if(subChain.getName().equals(name)&& subChain != null) {
                        matched = true;
                        Location temploc = new Location(name);
                        temploc.setLatitude(latitude);
                        temploc.setLongitude(longitude);
                        subChain.newLink(temploc, id, enabled);
                        break;
                    }
                }

                if(!matched){
                    LocationChain tempchain = new LocationChain(name);
                    Location location = new Location(name);

                    location.setLatitude(latitude);
                    location.setLongitude(longitude);

                    tempchain.newLink(location, id, enabled);
                    myLocationChain.add(tempchain);
                }


            }
            while(cursor.moveToNext());


        }
        return myLocationChain;
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
