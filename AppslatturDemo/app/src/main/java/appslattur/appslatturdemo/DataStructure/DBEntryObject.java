package appslattur.appslatturdemo.DataStructure;

/**
 * Created by arnarjons on 10.4.2015.
 */
public class DBEntryObject {

    ///
    // Database destination logic
    ///
    private int DATABASE_DESTINATION = -1;

    public static final int DATABASE_TABLE_FS = 0;
    public static final int DATABASE_TABLE_FSTS = 1;
    public static final int DATABASE_TABLE_FSMG = 2;

    ///
    // Database error handling
    ///
    private String errorMessage = "";
    private boolean hasError = true;

    public static final String error_One = "Initialization failed";

    ///
    // Entry Variables
    ///
    private int id;

    ///
    // Constructor
    ///
    public DBEntryObject(int id) {
        this.id = id;
    }

    ///
    // Destination handling
    ///
    public void setDestination(int dest) {
        this.DATABASE_DESTINATION = dest;
    }

    public int getDestination() {
        return this.DATABASE_DESTINATION;
    }

    ///
    // Error handling
    ///
    public void setErrorMessage(String errMSG) {
        this.errorMessage = errMSG;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public boolean isFaulty() {
        return this.hasError;
    }

    ///
    // Entry valiables
    ///
    public int getId() {
        return this.id;
    }



}
