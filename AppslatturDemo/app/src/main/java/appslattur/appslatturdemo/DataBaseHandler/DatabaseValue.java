package appslattur.appslatturdemo.DataBaseHandler;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by arnarjons on 3.4.2015.
 */
public class DatabaseValue implements Serializable {

    // Private Values for an objective DatabaseEntry
    private int id;
    private String latitude;
    private String longitude;
    private String name;
    private String cardGroup;
    private String mallGroup;
    private int hasTimeLimit;
    private String longDescription;
    private String shortDescription;
    private int isEnabled;
    private int pingRadius;

    private String timeStart;
    private String timeStop;

    // Entry flow control
    // 0 == Initial Entry
    // 1 == Secondary Entry
    // 2 == Third Entry
    private int ENTRY_TYPE;

    /**
     * FS_QUERY == Destined for database table FS
     * FSTS_QUERY == ------------------------- FSTS
     * FSMG_QUERY == ------------------------- FSMG
     */
    public static final int FS_QUERY = 0;
    public static final int FSTS_QUERY = 1;
    public static final int FSMG_QUERY = 2;

    private boolean isFS = false;
    private boolean isFSTS = false;
    private boolean isFSMG = false;

    /**
     * DatabaseValue
     * Handles raw output sent from Appslattur.db
     * @param id Appslattur.db row id
     * @param latitude GPS-coord Latitute
     * @param longitude GSP-coord Longitude
     * @param name Store name
     * @param cardGroup Cardgroup name
     * @param mallGroup Mallgroup name (if no mallgroup put 'General')
     * @param longDescription Long message
     * @param shortDescription Short message
     * @param isEnabled Should it be monitored
     * @param pingRadius Radius of ping-hit
     */
    public DatabaseValue(int id,
                         String latitude,
                         String longitude,
                         String name,
                         String cardGroup,
                         String mallGroup,
                         int hasTimeLimit,
                         String longDescription,
                         String shortDescription,
                         int isEnabled,
                         int pingRadius) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.cardGroup = cardGroup;
        this.mallGroup = mallGroup;
        this.hasTimeLimit = hasTimeLimit;
        this.longDescription = longDescription;
        this.shortDescription = shortDescription;
        this.isEnabled = isEnabled;
        this.pingRadius = pingRadius;

        ENTRY_TYPE = FS_QUERY;
        isFS = true;
    }

    /**
     * DatabaseValue
     * Handles raw output sent from Appslattur.db
     * @param id Appslattur.db row id
     * @param latitude GPS-coord Latitute
     * @param longitude GSP-coord Longitude
     * @param name Store name
     * @param cardGroup Cardgroup name
     * @param mallGroup Mallgroup name (if no mallgroup put 'General')
     * @param longDescription Long message
     * @param shortDescription Short message
     * @param isEnabled Should it be monitored
     * @param pingRadius Radius of ping-hit
     * @param timeStart String representation of time
     * @param timeStop String representation of time
     */
    public DatabaseValue(int id,
                         String latitude,
                         String longitude,
                         String name,
                         String cardGroup,
                         String mallGroup,
                         int hasTimeLimit,
                         String longDescription,
                         String shortDescription,
                         int isEnabled,
                         int pingRadius,
                         String timeStart,
                         String timeStop) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.cardGroup = cardGroup;
        this.mallGroup = mallGroup;
        this.hasTimeLimit = hasTimeLimit;
        this.longDescription = longDescription;
        this.shortDescription = shortDescription;
        this.isEnabled = isEnabled;
        this.pingRadius = pingRadius;
        this.timeStart = timeStart;
        this.timeStop = timeStop;

        ENTRY_TYPE = FS_QUERY;
        isFS = true;
    }

    /**
     * DatabaseValue
     * Handles raw output sent from Appslattur.db
     * @param id Appslattur.db row id
     * @param timeStart String representation of time
     * @param timeStop String representation of time
     */
    public DatabaseValue(int id,
                         String timeStart,
                         String timeStop) {
        this.id = id;
        this.timeStart = timeStart;
        this.timeStop = timeStop;

        ENTRY_TYPE = FSTS_QUERY;
        isFSTS = true;
    }

    /**
     * DatabaseValue
     * Handles raw input which is to be sent to Appslattur.db
     * @param id Appslattur.db row id
     * @param latitude GPS-coord Latitute
     * @param longitude GSP-coord Longitude
     * @param name Store name
     * @param pingRadius Radius of ping-hit
     */
    public DatabaseValue(int id,
                         String latitude,
                         String longitude,
                         String name,
                         int pingRadius) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.pingRadius = pingRadius;

        ENTRY_TYPE = FSMG_QUERY;
        isFSMG = true;
    }


    /**
     * getType()
     * @return Flow control flag ENTRY_TYPE
     */
    public int getType() {
        return this.ENTRY_TYPE;
    }

    // TODO : Double check initial and update logic for correct id handling
    public int getId() {
        if(isFSTS || isFSMG) {
            return this.id;
        }
        return -1;
    }

    public double getLatitude() {
        if(isFS || isFSMG) {
            return Double.parseDouble(this.latitude);
        }
        return -1.0;
    }

    public double getLongitude() {
        if(isFS || isFSMG) {
            return Double.parseDouble(this.longitude);
        }
        return -1.0;
    }

    public String getName() {
        if(isFS || isFSMG) {
            return this.name;
        }
        return null;
    }

    public String getCardGroup() {
        if(isFS) {
            return this.cardGroup;
        }
        return null;
    }

    public String getMallGroup() {
        if(isFS) {
            return this.mallGroup;
        }
        return null;
    }

    public boolean hasTimeLimit() {
        if(isFS) {
            if(hasTimeLimit == 1) {
                return true;
            }
            return false;
        }
        return false;
    }

    public String getLongDescription() {
        if(isFS) {
            return this.longDescription;
        }
        return null;
    }

    public String getShortDescription() {
        if(isFS) {
            return this.shortDescription;
        }
        return null;
    }

    public boolean isEnabled() {
        if(isFS) {
            if(this.isEnabled == 1) {
                return true;
            }
            return false;
        }
        return false;
    }

    public int getPingRadius() {
        if(isFS || isFSMG) {
            return this.pingRadius;
        }
        return -1;
    }

    public String getTimeStart() {
        if(isFS || isFSTS) {
            return this.timeStart;
        }
        return null;
    }

    public String getTimeStop() {
        if(isFS || isFSTS) {
            return this.timeStop;
        }
        return null;
    }
}
