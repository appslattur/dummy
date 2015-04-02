package appslattur.appslatturdemo.DatabaseHelper;

/**
 * @author Arnar Jonsson
 * @version 0.1
 * @since 02.04.2015
 */
public class FSDatabaseEntry {

    private String name;
    private double latitude;
    private double longitude;
    private boolean isEnable;
    private String shortDescription;
    private String longDescription;
    private String discountText;
    private String group;

    /**
     * FSDatabaseEntry
     * Converts java variables destined for FSDatabase to SQLite variables
     * This constructor has default group null
     * @param name
     * @param latitude
     * @param longitude
     * @param isEnable
     * @param shortDescription
     * @param longDescription
     * @param discountText
     */
    public FSDatabaseEntry(String name,
                           double latitude,
                           double longitude,
                           boolean isEnable,
                           String shortDescription,
                           String longDescription,
                           String discountText) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.isEnable = isEnable;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.discountText = discountText;
        this.group = null;
    }

    /**
     * FSDatabaseEntry
     * Converts java variables destined for FSDatabase to SQLite variables
     * @param name
     * @param latitude
     * @param longitude
     * @param isEnable
     * @param shortDescription
     * @param longDescription
     * @param discountText
     * @param group
     */
    public FSDatabaseEntry(String name,
                           double latitude,
                           double longitude,
                           boolean isEnable,
                           String shortDescription,
                           String longDescription,
                           String discountText,
                           String group) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.isEnable = isEnable;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.discountText = discountText;
        this.group = group;
    }

    public String getName() {
        return this.name;
    }

    public String getLatitude() {
        return Double.toString(this.latitude);
    }

    public String getLongitude() {
        return Double.toString(this.longitude);
    }

    public int getEnableStatus() {
        if(this.isEnable) {
            return 1;
        }
        return 0;
    }

    public String getShortDescription() {
        return this.shortDescription;
    }

    public String getLongDescription() {
        return this.longDescription;
    }

    public String getDiscountText() {
        return this.discountText;
    }

    public String getGroup() {
        return this.group;
    }
}
