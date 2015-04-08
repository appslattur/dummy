package appslattur.appslatturdemo.DataBaseHandler;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by arnarjons on 3.4.2015.
 */
public class DatabaseValue implements Serializable {

    // Private Values for an objective DatabaseValue
    private int id;

    private String latitude;
    private String longitude;
    private String cardGroup;
    private String mallGroup;
    private int hasTimeLimit;
    private String longDescription;
    private String shortDescription;
    private int isEnabled;

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

    private String ENTRY_ERROR_1 = "Wrong Value Type Extraction";
    private String ENTRY_ERROR_2 = "Entry value assignment failed";

    public DatabaseValue(int id,
                         String latitude,
                         String longitude,
                         String cardGroup,
                         String mallGroup,
                         int hasTimeLimit,
                         String longDescription,
                         String shortDescription,
                         int isEnabled) {
        try {
            this.id = id;
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

    public DatabaseValue(int id,
                         String latitude,
                         String longitude,
                         String cardGroup,
                         String mallGroup,
                         int hasTimeLimit,
                         String timeStart,
                         String timeStop,
                         String longDescription,
                         String shortDescription,
                         int isEnabled) {
        try {
            this.id = id;
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

    public DatabaseValue(int id,
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
        return id;
    }

    public double getLatitude() {
        switch (ENTRY_TYPE) {
            case INITIAL_QUERY:
                ERROR_DESCRIPTION = NO_ERROR_FOUND;
                return Double.parseDouble(latitude);
            default:
                ERROR_DESCRIPTION = ENTRY_ERROR_1;
                return -1.0;
        }
    }

    public double getLongitude() {
        switch (ENTRY_TYPE) {
            case INITIAL_QUERY:
                ERROR_DESCRIPTION = NO_ERROR_FOUND;
                return Double.parseDouble(longitude);
            default:
                ERROR_DESCRIPTION = ENTRY_ERROR_1;
                return -1.0;
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

    public boolean hasTimeLimit() {
        switch (ENTRY_TYPE) {
            case INITIAL_QUERY:
                ERROR_DESCRIPTION = NO_ERROR_FOUND;
                if(hasTimeLimit == 1) {
                    return true;
                }
                return false;
            default:
                ERROR_DESCRIPTION = ENTRY_ERROR_1;
                return false;
        }
    }

    public String getTimeStart() {
        switch (ENTRY_TYPE) {
            case INITIAL_QUERY:
                switch (NESTED_ENTRY_TYPE) {
                    case NESTED_WOTIMESTAMP:
                        ERROR_DESCRIPTION = ENTRY_ERROR_1;
                        return null;
                    default:
                        ERROR_DESCRIPTION = NO_ERROR_FOUND;
                        return timeStart;
                }
            default:
                ERROR_DESCRIPTION = NO_ERROR_FOUND;
                return timeStart;
        }
    }

    public String getTimeStop() {
        switch (ENTRY_TYPE) {
            case INITIAL_QUERY:
                switch (NESTED_ENTRY_TYPE) {
                    case NESTED_WOTIMESTAMP:
                        ERROR_DESCRIPTION = ENTRY_ERROR_1;
                        return null;
                    default:
                        ERROR_DESCRIPTION = NO_ERROR_FOUND;
                        return timeStop;
                }
            default:
                ERROR_DESCRIPTION = NO_ERROR_FOUND;
                return timeStop;
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

    public boolean isEnabled() {
        switch (ENTRY_TYPE) {
            case INITIAL_QUERY:
                ERROR_DESCRIPTION = NO_ERROR_FOUND;
                if(isEnabled == 1) {
                    return true;
                }
                return false;
            default:
                ERROR_DESCRIPTION = ENTRY_ERROR_1;
                return false;
        }
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
