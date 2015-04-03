package appslattur.appslatturdemo.DataBaseHandler;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by arnarjons on 3.4.2015.
 */
public class DatabaseValue implements Serializable {

    private String id;
    private String latitude;
    private String longitude;
    private String isEnabled;

    /**
     * RadarScannerIterable
     * Object that contains minimal information for GPS-coord matching
     * @param id
     * @param latitude
     * @param longitude
     * @param isEnabled
     */
    public DatabaseValue(int id, String latitude, String longitude, int isEnabled) {

        this.id = Integer.toString(id);

        this.latitude = latitude;
        this.longitude = longitude;

        this.isEnabled = Integer.toString(isEnabled);
    }

    /////
    // Get/Set Methods
    /////

    public int getId() {
        try {
            return Integer.parseInt(this.id);
        }
        catch ( NumberFormatException e ) {
            return -1;
        }
    }

    public double getLatitude() {
        try {
            return Double.parseDouble(this.latitude);
        }
        catch ( NumberFormatException e ) {
            return -1.0;
        }
    }

    public double getLongitude() {
        try {
            return Double.parseDouble(this.longitude);
        }
        catch ( NumberFormatException e ) {
            return -1.0;
        }
    }

    public boolean isEnabled() {
        if(this.isEnabled.equals("1")) {
            return true;
        }
        return false;
    }

    /////
    // Parcelabel implementation
    /////

    /*
    public DatabaseValue(Parcel in) {
        String[] data = new String[4];

        in.readStringArray(data);
        this.id = data[0];
        this.latitude = data[1];
        this.longitude = data[2];
        this.isEnabled = data[3];
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] { this.id,
                this.latitude,
                this.longitude,
                this.isEnabled });
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        public DatabaseValue createFromParcel(Parcel in) {
            return new DatabaseValue(in);
        }

        public DatabaseValue[] newArray(int size) {
            return new DatabaseValue[size];
        }
    };
    */
}
