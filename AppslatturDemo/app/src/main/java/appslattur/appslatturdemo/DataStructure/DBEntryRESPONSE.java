package appslattur.appslatturdemo.DataStructure;

/**
 * Created by arnarjons on 10.4.2015.
 */
public class DBEntryRESPONSE extends DBEntryObject {

    ///
    // Entry variables
    ///
    private String RES_MSG;
    private boolean ERROR_FLAG;

    ///
    // Constructors
    ///

    /**
     * DBEntryRESPONSE
     * Typical Validation response for error handling
     * @param id id
     * @param RES_MSG Response message
     * @param isError Validation flag
     *
     * Response from AsyncTasks for every insertion,
     *                errorResponse for every extractions
     */
    public DBEntryRESPONSE(int id, String RES_MSG, boolean isError) {
        super(id);
        this.RES_MSG = RES_MSG;
        this.ERROR_FLAG = isError;
        this.ENTRY_TYPE = DBEntryObject.DATABASE_RESPONSE;
    }

    ///
    // Entry Get/Set methods
    ///
    public void setResponseMessage(String responseMessage) {
        this.RES_MSG = responseMessage;
    }

    public String getResponseMessage() {
        return this.RES_MSG;
    }

    public boolean isErrorMessage() {
        return this.ERROR_FLAG;
    }


}
