package appslattur.appslatturdemo.DataStructure;

/**
 * Created by arnarjons on 10.4.2015.
 */
public class DBEntryFS extends DBEntryObject {

    ///
    // Error handling
    ///
    public static final String FS_ERROR_ONE = "Initialization failed, pingRadius is lesser than 1";

    ///
    // Entry variables
    ///
    private double latitude;
    private double longitude;
    private String shopName;
    private String cardName;
    private String mallGroup;
    private boolean hasTimeLimit;
    private String longDescription;
    private String shortDescription;
    private boolean isEnable;
    private int pingRadius;

    ///
    // Constructors
    ///

    /**
     * DBEntryFS
     * Database insertion object for Appslattur.db
     * @param id _ID PRIMARY KEY
     * @param latitude latitude
     * @param longitude longitude
     * @param shopName shopname
     * @param cardName cardname
     * @param mallGroup mallgroup
     * @param longDescription longdescription
     * @param shortDescription shortdescription
     * @param isEnable isenabled
     * @param pingRadius pingradius
     *
     * param hasTimeLimit is auto incremented (boolean-int)
     *
     * Insertion does not affect the helper table
     */
    public DBEntryFS(int id,
                     double latitude,
                     double longitude,
                     String shopName,
                     String cardName,
                     String mallGroup,
                     String longDescription,
                     String shortDescription,
                     boolean isEnable,
                     int pingRadius) {
        super(id);

        this.latitude = latitude;
        this.longitude = longitude;
        this.shopName = shopName;
        this.cardName = cardName;
        if(mallGroup == null) this.mallGroup = "General";
        else this.mallGroup = mallGroup;

        this.hasTimeLimit = false;

        this.longDescription = longDescription;
        this.shortDescription = shortDescription;
        this.isEnable = isEnable;
        if(pingRadius > 0) {
            this.pingRadius = pingRadius;
        }
        else  {
            this.pingRadius = -1;
            this.errorMessage = DBEntryFS.FS_ERROR_ONE;
            this.hasError = true;
        }

        if(this.errorMessage == null) {
            this.ENTRY_TYPE = DBEntryObject.DATABASE_INSERT;
            this.DATABASE_DESTINATION = DBEntryObject.DATABASE_TABLE_FS;
        }
    }

    ///
    // DBEntryFS object Get/Set methods
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

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopName() {
        return this.shopName;
    }

    public void setCardName(String cardName) {

    }

    public String getCardName() {
        return this.cardName;
    }

    public void setMallGroup(String mallGroup) {
        this.mallGroup = mallGroup;
    }

    public String getMallGroup() {
        return this.mallGroup;
    }

    /**
     * @param flag hastimelimit
     */
    public void setTimeFlag(boolean flag) {
        this.hasTimeLimit = flag;
    }

    /**
     * @return hastimelimit
     */
    public boolean getTimeFlag() {
        return this.hasTimeLimit;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getLongDescription() {
        return this.longDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getShortDescription() {
        return this.shortDescription;
    }

    /**
     * @param flag isenalbe
     */
    public void setEligability(boolean flag) {
        this.isEnable = flag;
    }

    /**
     * @return isenable
     */
    public boolean isEnabled() {
        return this.isEnable;
    }

    public void setPingRadius(int pingRadius) {
        this.pingRadius = pingRadius;
    }

    public int getPingRadius() {
        return this.pingRadius;
    }

    ///
    // DBEntryFS specialized Get/Set methods
    ///
}
