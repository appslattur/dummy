package appslattur.appslatturdemo.DataBaseHandler;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by arnarjons on 3.4.2015.
 */
public class DatabaseEntry implements Serializable {

    

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
