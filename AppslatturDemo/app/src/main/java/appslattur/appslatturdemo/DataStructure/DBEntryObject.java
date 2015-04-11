package appslattur.appslatturdemo.DataStructure;

import java.io.Serializable;

/**
 * Created by arnarjons on 10.4.2015.
 */
public class DBEntryObject implements Serializable {

    ///
    // DBEntryObject type handles
    ///
    //
    // 0 == blank parent object
    // 1 == Database insertion object
    // 2 == Database extration object
    // 3 == Database custom extraction object
    // 4 == Database utilization command object
    //
    public int ENTRY_TYPE = 0;

    public static final int DATABASE_INSERT = 1;
    public static final int DATABASE_EXTRACT = 2;
    public static final int DATABASE_CUSTOM_EXTRACT = 3;
    public static final int DATABASE_UTIL = 4;
    public static final int DATABASE_RESPONSE = 5;

    ///
    // Database insertion logic
    ///
    public int DATABASE_DESTINATION = -1;

    public static final int DATABASE_TABLE_FS = 0;
    public static final int DATABASE_TABLE_FSTS = 1;
    public static final int DATABASE_TABLE_FSMG = 2;

    ///
    // Database error handling
    ///
    public String errorMessage;
    public boolean hasError = false;

    public static final String error_One = "Initialization failed, id is lesser than 1";

    ///
    // Entry Variables
    ///
    private int id;

    ///
    // Constructor
    ///

    /**
     * DBEntryObject
     * A specially constructed object used to control communication with the
     * android application Appslattur and its in-phone Database (Badly named DatabaseHelper)
     * @param id Represents integer id of a database entry
     */
    public DBEntryObject(int id) {
        if (id > 0) {
            this.id = id;
        }
        else {
            this.id = -1;
            this.hasError = true;
            this.errorMessage = DBEntryObject.error_One;
        }
    }

    ///
    // Destination handling
    ///

    /**
     * @return Entry type of object
     */
    public int getType() {
        return this.ENTRY_TYPE;
    }

    /**
     * @param dest Represents a destination table in the in-phone database
     */
    public void setDestination(int dest) {
        this.DATABASE_DESTINATION = dest;
    }

    /**
     * @return Representation of a destination table in the in-phone database
     */
    public int getDestination() {
        return this.DATABASE_DESTINATION;
    }

    ///
    // Error handling
    ///

    /**
     * @param errMSG Sets the objects error message
     */
    public void setErrorMessage(String errMSG) {
        this.errorMessage = errMSG;
    }

    /**
     * @return The objects error message, if any
     */
    public String getErrorMessage() {
        return this.errorMessage;
    }

    /**
     * @return Validation of the object
     */
    public boolean isFaulty() {
        return this.hasError;
    }

    ///
    // Entry valiables
    ///

    /**
     * @return Id of the DBEntryObject
     */
    public int getId() {
        return this.id;
    }



}
