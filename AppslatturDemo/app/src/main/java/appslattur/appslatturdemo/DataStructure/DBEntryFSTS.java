package appslattur.appslatturdemo.DataStructure;

/**
 * Created by arnarjons on 10.4.2015.
 */
public class DBEntryFSTS extends DBEntryFS{

    ///
    // Error handling
    ///
    public static final String FSTS_ERROR_ONE = "Initialization failed, timestamps do not match pattern";

    ///
    // Entry variables
    ///
    private String timeStart;
    private String timeStop;

    /**
     * Extension of DBEntryFS
     * @param timeStart timestart
     * @param timeStop timestop
     *
     * Insertion affects both the main table and its helper table
     */
    public DBEntryFSTS(int id,
                     double latitude,
                     double longitude,
                     String shopName,
                     String cardName,
                     String mallGroup,
                     String longDescription,
                     String shortDescription,
                     boolean isEnable,
                     int pingRadius,
                     String timeStart,
                     String timeStop) {

        super(id, latitude, longitude, shopName, cardName,
                mallGroup, longDescription, shortDescription,
                isEnable, pingRadius);

        String pattern = "^\\d{2}:\\d{2}:\\d{2}";

        if(timeStart.matches(pattern) && timeStop.matches(pattern)) {
            this.timeStart = timeStart;
            this.timeStop = timeStop;
            this.ENTRY_TYPE = DBEntryObject.DATABASE_INSERT;
            this.DATABASE_DESTINATION = DBEntryObject.DATABASE_TABLE_FSMG;
        }
        else {
            this.hasError = true;
            this.errorMessage = DBEntryFSTS.FSTS_ERROR_ONE;
        }
    }

    ///
    // DBEntryFSTS object Get/Set methods
    ///

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeStart() {
        return this.timeStart;
    }

    public void setTimeStop(String timeStop) {
        this.timeStop = timeStop;
    }

    public String getTimeStop() {
        return this.timeStop;
    }

    ///
    // DBEntryFS specialized Get/Set methods
    ///



}
