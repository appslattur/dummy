package appslattur.appslatturdemo.DataStructure;

/**
 * Created by arnarjons on 10.4.2015.
 */
public class DBEntryFSMG extends DBEntryObject {

    ///
    // Error handling
    ///
    public static final String FSMG_ERROR_ONE = "Initialization failed, pingRadius is lesser than 1";

    ///
    // Entry variables
    ///
    private double latitude;
    private double longitude;
    private String name;
    private int pingRadius;

    ///
    // Constructors
    ///

    /**
     * DBEntryFSMN
     * Database insertion object of Appslattur.db
     * @param id id
     * @param latitude latitude
     * @param longitude longitude
     * @param name name
     * @param pingRadius pingradius
     *
     * Insertion affects the third table THIRD
     */
    public DBEntryFSMG(int id,
                       double latitude,
                       double longitude,
                       String name,
                       int pingRadius) {

        super(id);

        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        if(pingRadius < 1) {
            this.hasError = true;
            this.errorMessage = DBEntryFSMG.FSMG_ERROR_ONE;
        }
        else {
            this.pingRadius = pingRadius;
            this.ENTRY_TYPE = DBEntryObject.DATABASE_INSERT;
            this.DATABASE_DESTINATION = DBEntryObject.DATABASE_TABLE_FSMG;
        }
    }

    ///
    // Entry Get/Set methods
    ///

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setPingRadius(int i) {
        this.pingRadius = i;
    }

    public int getPingRadius() {
        return this.pingRadius;
    }

    ///
    // DBEntryFSMG specialized Get/Set methods
    ///
    public String getLatitudeAsString() {
        return this.latitude + "";
    }

    public String getLongitudeAsString() {
        return this.longitude+"";
    }

}
