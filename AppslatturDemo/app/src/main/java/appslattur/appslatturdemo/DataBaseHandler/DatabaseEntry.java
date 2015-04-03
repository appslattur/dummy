package appslattur.appslatturdemo.DataBaseHandler;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by arnarjons on 3.4.2015.
 */
public class DatabaseEntry implements Serializable {

    private String latitude;
    private String longitude;
    private String isEnable;

    /**
     * FSDatabaseEntry
     * Converts java variables destined for FSDatabase to SQLite variables
     * This constructor has default group null
     * @param latitude
     * @param longitude
     * @param isEnable
     */
    public DatabaseEntry(double latitude,
                           double longitude,
                           boolean isEnable) {

        this.latitude = Double.toString(latitude);
        this.longitude = Double.toString(longitude);

        if( isEnable ) {
            this.isEnable = "1";
        }
        else {
            this.isEnable = "0";
        }
    }

    /////
    // Get/Set Methods
    /////

    public String getLatitude() {
        return this.latitude;
    }

    public String getLongitude() {
        return this.longitude;
    }

    public int getEnableStatus() {
        return Integer.parseInt(this.isEnable);
    }

    /////
    // Parcelable Methods
    /////

    /*
    public DatabaseEntry(Parcel in) {
        String[] data = new String[3];

        in.writeStringArray(data);
        this.latitude = data[0];
        this.longitude = data[1];
        this.isEnable = data[2];
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {
                this.latitude,
                this.longitude,
                this.isEnable });
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        public DatabaseEntry createFromParcel(Parcel in) {
            return new DatabaseEntry(in);
        }

        public DatabaseEntry[] newArray(int size) {
            return new DatabaseEntry[size];
        }
    };
    */
}
