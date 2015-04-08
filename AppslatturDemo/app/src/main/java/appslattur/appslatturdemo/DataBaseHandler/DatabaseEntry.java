package appslattur.appslatturdemo.DataBaseHandler;

import android.nfc.FormatException;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.ContactsContract;

import java.io.Serializable;

/**
 * Created by arnarjons on 3.4.2015.
 */
public class DatabaseEntry implements Serializable {

    // Private Values for an objective DatabaseEntry
    private int id;

    private double latitude;
    private double longitude;
    private String cardGroup;
    private String mallGroup;
    private boolean hasTimeLimit;
    private String longDescription;
    private String shortDescription;
    private boolean isEnabled;

    private String timeStart;
    private String timeStop;

    // Entry flow control

    // 0 == created
    // 1 == in progress
    // 2 == finished and ready to be cleaned up
    private int ENTRY_STATE = 0;

    // 0 == Initial Entry
    // 1 == Secondary Entry
    private int ENTRY_TYPE;
    public static final int INITIAL_QUERY = 0;
    public static final int SECONDARY_QUERY = 1;

    // 0 == Initial without timestamp
    // 1 == Initial with timestamp
    private int NESTED_ENTRY_TYPE;
    public static final int NESTED_WOTIMESTAMP = 0;
    public static final int NESTED_TIMESTAMP = 1;

    // Debugging and errorHandling values

    // TODO : place error and snipping strings into strings.xml
    private String NO_ERROR_FOUND = "No Errors found";

    private String ERROR_DESCRIPTION = "";

    private String ENTRY_ERROR_1 = "Wrong Entry Type Extraction";
    private String ENTRY_ERROR_2 = "Entry value assignment failed";



    // Database entry for Initial Database Table without timestamps
    public DatabaseEntry(double latitude,
                         double longitude,
                         String cardGroup,
                         String mallGroup,
                         boolean hasTimeLimit,
                         String longDescription,
                         String shortDescription,
                         boolean isEnabled) {
        try {
            this.latitude = latitude;
            this.longitude = longitude;
            this.cardGroup = cardGroup;
            this.mallGroup = mallGroup;
            this.hasTimeLimit = hasTimeLimit;
            this.longDescription = longDescription;
            this.shortDescription = shortDescription;
            this.isEnabled = isEnabled;

            ERROR_DESCRIPTION = NO_ERROR_FOUND;
        }
        catch ( Exception e) {
            ERROR_DESCRIPTION = ENTRY_ERROR_2;
        }
        ENTRY_TYPE = INITIAL_QUERY;
        NESTED_ENTRY_TYPE = NESTED_WOTIMESTAMP;

    }

    // Database entry for Initial Database Table with timestamps
    public DatabaseEntry(double latitude,
                         double longitude,
                         String cardGroup,
                         String mallGroup,
                         boolean hasTimeLimit,
                         String timeStart,
                         String timeStop,
                         String longDescription,
                         String shortDescription,
                         boolean isEnabled) {
        try {
            this.latitude = latitude;
            this.longitude = longitude;
            this.cardGroup = cardGroup;
            this.mallGroup = mallGroup;
            this.hasTimeLimit = hasTimeLimit;
            this.timeStart = timeStart;
            this.timeStop = timeStop;
            this.longDescription = longDescription;
            this.shortDescription = shortDescription;
            this.isEnabled = isEnabled;

            ERROR_DESCRIPTION = NO_ERROR_FOUND;
        }
        catch ( Exception e) {
            ERROR_DESCRIPTION = ENTRY_ERROR_2;
        }
        ENTRY_TYPE = INITIAL_QUERY;
        NESTED_ENTRY_TYPE = NESTED_TIMESTAMP;

    }

    // Database entry for Secondary Database Table
    public DatabaseEntry(int id,
                         String timeStart,
                         String timeStop) {
        try {
            this.id = id;
            this.timeStart = timeStart;
            this.timeStop = timeStop;

            ERROR_DESCRIPTION = NO_ERROR_FOUND;
        }
        catch (Exception e) {
            ERROR_DESCRIPTION = ENTRY_ERROR_2;
        }
        ENTRY_TYPE = SECONDARY_QUERY;
        NESTED_ENTRY_TYPE = -1;

    }

    /////
    // Flow Control Methods
    /////
    public int getType() {
        return ENTRY_TYPE;
    }

    public String getWorkingStatus() {
        return ERROR_DESCRIPTION;
    }

    /////
    // Get/Set Methods
    /////

    public int getId() {
        switch (ENTRY_TYPE) {
            case SECONDARY_QUERY:
                ERROR_DESCRIPTION = NO_ERROR_FOUND;
                return id;
            default:
                return -1;
        }
    }

    public String getLatitude() {
        switch (ENTRY_TYPE) {
            case INITIAL_QUERY:
                ERROR_DESCRIPTION = NO_ERROR_FOUND;
                return Double.toString(latitude);
            default:
                ERROR_DESCRIPTION = ENTRY_ERROR_1;
                return null;
        }
    }

    public String getLongitude() {
        switch (ENTRY_TYPE) {
            case INITIAL_QUERY:
                ERROR_DESCRIPTION = NO_ERROR_FOUND;
                return Double.toString(longitude);
            default:
                ERROR_DESCRIPTION = ENTRY_ERROR_1;
                return null;
        }
    }

    public String getCardGroup() {
        switch (ENTRY_TYPE) {
            case INITIAL_QUERY:
                ERROR_DESCRIPTION = NO_ERROR_FOUND;
                return cardGroup;
            default:
                ERROR_DESCRIPTION = ENTRY_ERROR_1;
                return null;
        }
    }

    public String getMallGroup() {
        switch (ENTRY_TYPE) {
            case INITIAL_QUERY:
                ERROR_DESCRIPTION = NO_ERROR_FOUND;
                return mallGroup;
            default:
                ERROR_DESCRIPTION = ENTRY_ERROR_1;
                return null;
        }
    }

    public int hasTimeLimit() {
        switch (ENTRY_TYPE) {
            case INITIAL_QUERY:
                ERROR_DESCRIPTION = NO_ERROR_FOUND;
                if(hasTimeLimit) {
                    return 1;
                }
                return 0;
            default:
                ERROR_DESCRIPTION = ENTRY_ERROR_1;
                return -1;
        }
    }

    public String getTimeStart() {
        switch (ENTRY_TYPE) {
            case INITIAL_QUERY:
                switch (NESTED_TIMESTAMP) {
                    case NESTED_TIMESTAMP:
                        ERROR_DESCRIPTION = NO_ERROR_FOUND;
                        return timeStart;
                    default:
                        ERROR_DESCRIPTION = ENTRY_ERROR_1;
                        return null;
                }
            case SECONDARY_QUERY:
                ERROR_DESCRIPTION = NO_ERROR_FOUND;
                return timeStart;
            default:
                ERROR_DESCRIPTION = ENTRY_ERROR_1;
                return null;
        }
    }

    public String getTimeStop() {
        switch (ENTRY_TYPE) {
            case INITIAL_QUERY:
                switch (NESTED_TIMESTAMP) {
                    case NESTED_TIMESTAMP:
                        ERROR_DESCRIPTION = NO_ERROR_FOUND;
                        return timeStop;
                    default:
                        ERROR_DESCRIPTION = ENTRY_ERROR_1;
                        return null;
                }
            case SECONDARY_QUERY:
                ERROR_DESCRIPTION = NO_ERROR_FOUND;
                return timeStop;
            default:
                ERROR_DESCRIPTION = ENTRY_ERROR_1;
                return null;
        }
    }

    public String getLongDescription() {
        switch (ENTRY_TYPE) {
            case INITIAL_QUERY:
                ERROR_DESCRIPTION = NO_ERROR_FOUND;
                return longDescription;
            default:
                ERROR_DESCRIPTION = ENTRY_ERROR_1;
                return null;
        }
    }

    public String getShortDescription() {
        switch (ENTRY_TYPE) {
            case INITIAL_QUERY:
                ERROR_DESCRIPTION = NO_ERROR_FOUND;
                return shortDescription;
            default:
                ERROR_DESCRIPTION = ENTRY_ERROR_1;
                return null;
        }
    }

    public int isEnabled() {
        switch (ENTRY_TYPE) {
            case INITIAL_QUERY:
                ERROR_DESCRIPTION = NO_ERROR_FOUND;
                if(isEnabled) {
                    return 1;
                }
                return 0;
            default:
                ERROR_DESCRIPTION = ENTRY_ERROR_1;
                return -1;
        }
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
