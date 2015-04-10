package appslattur.appslatturdemo;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import appslattur.appslatturdemo.DataBaseHandler.DatabaseEntry;
import appslattur.appslatturdemo.DataBaseHandler.DatabaseEntryTask;
import appslattur.appslatturdemo.DataBaseHandler.DatabaseValue;
import appslattur.appslatturdemo.DataBaseHandler.DatabaseValueTask;


public class TestingActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);

        final TextView textView = (TextView) findViewById(R.id.outputView);

        Button entryButton = (Button) findViewById(R.id.entryButton);
        entryButton.setOnClickListener(new View.OnClickListener() {
            int idNo = 0;
            @Override
            public void onClick(View v) {
                long insertId = 0;

                DatabaseEntry[] dbEntryFS = new DatabaseEntry[5];
                DatabaseEntry[] dbEntryFSTS = new DatabaseEntry[5];
                DatabaseEntry[] dbEntryFSMG = new DatabaseEntry[5];


                for(int i = 0; i < 5; i ++) {
                    DatabaseEntry dbEFS = new DatabaseEntry(
                            0.0,
                            0.0,
                            "text",
                            "text",
                            "text",
                            "text",
                            "text",
                            true,
                            1

                    );
                    dbEntryFS[i] = dbEFS;

                    DatabaseEntry dbEFSTS = new DatabaseEntry(
                            i+1,
                            "clockText",
                            "clockText"
                    );
                    dbEntryFSTS[i] = dbEFSTS;

                    DatabaseEntry dbEFSMG = new DatabaseEntry(
                            i+1,
                            0.0,
                            0.0,
                            "name",
                            1
                    );
                    dbEntryFSMG[i] = dbEFSMG;
                }
                /*
                FS_ENTRY_TEST
                DatabaseEntry dbEntry = new DatabaseEntry(
                        0.0,
                        0.0,
                        "text",
                        "text",
                        "text",
                        "text",
                        "text",
                        true,
                        1

                );
                */
                /*
                FSTS_ENTRY_TEST
                DatabaseEntry dbEntry = new DatabaseEntry(
                        idNo++,
                        "clockText",
                        "clockText"
                );
                */
                /*
                FSMG_ENTY_TEST
                DatabaseEntry dbEntry = new DatabaseEntry(
                        idNo++,
                        0.0,
                        0.0,
                        "name",
                        1
                );
                */
                try {
                    insertId = new DatabaseEntryTask(getApplicationContext()).
                            execute(dbEntryFS).get();
                    insertId = new DatabaseEntryTask(getApplicationContext()).
                            execute(dbEntryFSTS).get();
                    insertId = new DatabaseEntryTask(getApplicationContext()).
                            execute(dbEntryFSMG).get();
                }
                catch (Exception e) {
                    insertId = -1;
                }
                textView.setText("Last inserted item in database has id : " +
                            insertId);
            }
        });

        Button valueButton = (Button) findViewById(R.id.valueButton);
        valueButton.setOnClickListener(new View.OnClickListener() {
            int idTest = 0;
            @Override
            public void onClick(View v) {
                String outputValue = "";
                DatabaseValue[] dbValueFS = new DatabaseValue[1];
                DatabaseValue[] dbValueFSTS = new DatabaseValue[1];
                DatabaseValue[] dbValueFSMG = new DatabaseValue[1];
                try {
                    // TODO : Constructor argument controlString needs to be swapped between tests
                    // StudentCard
                    // FSTimeStamp
                    // FSMallGroup
                    dbValueFS = new DatabaseValueTask(getApplicationContext(),"StudentCard").
                            execute().get();
                    dbValueFSTS = new DatabaseValueTask(getApplicationContext(),"FSTimeStamp").
                            execute().get();
                    dbValueFSMG = new DatabaseValueTask(getApplicationContext(),"FSMallGroup").
                            execute().get();



                    outputValue = "Output from value/s is/are following : " + "\n";

                    /*
                    for(DatabaseValue out : dbValueFS) {
                        switch (out.getType()) {
                            case DatabaseValue.FS_QUERY:
                                outputValue +=
                                        "Id : " + out.getId() +
                                                " lat is : " + out.getLatitude() +
                                                " lon is : " + out.getLongitude() +
                                                " name is : " + out.getName() +
                                                " cG is : " + out.getCardGroup() +
                                                " mG is : " + out.getMallGroup() +
                                                " lD is : " + out.getLongDescription() +
                                                " sD is : " + out.getShortDescription() +
                                                " iE is : " + out.hasTimeLimit() +
                                                " pR is : " + out.getPingRadius();
                                break;
                            case DatabaseValue.FSTS_QUERY:
                                outputValue +=
                                        "id is : " + out.getId()
                                                + " tS is : " + out.getTimeStart()
                                                + " tT is : " + out.getTimeStop();
                                break;
                            case DatabaseValue.FSMG_QUERY:
                                outputValue +=
                                        "id is : " + out.getId()
                                                + " lat is : " + out.getLatitude()
                                                + " lon is : " + out.getLongitude()
                                                + " name is : " + out.getName()
                                                + " pR is : " + out.getPingRadius();
                                break;
                            default:
                                break;
                        }
                    }
                    */

                    for(DatabaseValue out : dbValueFSTS) {
                        switch (out.getType()) {
                            case DatabaseValue.FS_QUERY:
                                outputValue +=
                                        "Id : " + out.getId() +
                                                "lat is : " + out.getLatitude() +
                                                "lon is : " + out.getLongitude() +
                                                "name is : " + out.getName() +
                                                "cG is : " + out.getCardGroup() +
                                                "mG is : " + out.getMallGroup() +
                                                "lD is : " + out.getLongDescription() +
                                                "sD is : " + out.getShortDescription() +
                                                "iE is : " + out.hasTimeLimit() +
                                                "pR is : " + out.getPingRadius();
                                break;
                            case DatabaseValue.FSTS_QUERY:
                                outputValue +=
                                        "id is : " + out.getId()
                                                + " tS is : " + out.getTimeStart()
                                                + " tT is : " + out.getTimeStop();
                                break;
                            case DatabaseValue.FSMG_QUERY:
                                outputValue +=
                                        "id is : " + out.getId()
                                                + " lat is : " + out.getLatitude()
                                                + " lon is : " + out.getLongitude()
                                                + " name is : " + out.getName()
                                                + " pR is : " + out.getPingRadius();
                                break;
                            default:
                                break;
                        }
                    }


                    for(DatabaseValue out : dbValueFSMG) {
                        switch (out.getType()) {
                            case DatabaseValue.FS_QUERY:
                                outputValue +=
                                        "Id : " + out.getId() +
                                                " lat is : " + out.getLatitude() +
                                                " lon is : " + out.getLongitude() +
                                                " name is : " + out.getName() +
                                                " cG is : " + out.getCardGroup() +
                                                " mG is : " + out.getMallGroup() +
                                                " lD is : " + out.getLongDescription() +
                                                " sD is : " + out.getShortDescription() +
                                                " iE is : " + out.hasTimeLimit() +
                                                " pR is : " + out.getPingRadius();
                                break;
                            case DatabaseValue.FSTS_QUERY:
                                outputValue +=
                                        "id is : " + out.getId()
                                                + " tS is : " + out.getTimeStart()
                                                + " tT is : " + out.getTimeStop();
                                break;
                            case DatabaseValue.FSMG_QUERY:
                                outputValue +=
                                        "id is : " + out.getId()
                                                + " lat is : " + out.getLatitude()
                                                + " lon is : " + out.getLongitude()
                                                + " name is : " + out.getName()
                                                + " pR is : " + out.getPingRadius();
                                break;
                            default:
                                break;
                        }
                    }
                    /*

                    //DatabaseValue value = dbValue[0];
                    outputValue = "Output from value/s is/are following : " + "\n";

                    for(DatabaseValue out : value) {
                        switch (out.getType()) {
                            case DatabaseValue.FS_QUERY:
                                outputValue +=
                                        "Id : " + out.getId() + "\n" +
                                                "lat is : " + out.getLatitude() + "\n" +
                                                "lon is : " + out.getLongitude() + "\n" +
                                                "name is : " + out.getName() + "\n" +
                                                "cG is : " + out.getCardGroup() + "\n" +
                                                "mG is : " + out.getMallGroup() + "\n" +
                                                "lD is : " + out.getLongDescription() + "\n" +
                                                "sD is : " + out.getShortDescription() + "\n" +
                                                "iE is : " + out.hasTimeLimit() + "\n" +
                                                "pR is : " + out.getPingRadius() + "\n";
                                break;
                            case DatabaseValue.FSTS_QUERY:
                                outputValue +=
                                        "id is : " + out.getId() + "\n"
                                                + "tS is : " + out.getTimeStart() + "\n"
                                                + "tT is : " + out.getTimeStop() + "\n";
                                break;
                            case DatabaseValue.FSMG_QUERY:
                                outputValue +=
                                        "id is : " + out.getId() + "\n"
                                                + "lat is : " + out.getLatitude() + "\n"
                                                + "lon is : " + out.getLongitude() + "\n"
                                                + "name is : " + out.getName() + "\n"
                                                + "pR is : " + out.getPingRadius() + "\n";
                                break;
                            default:
                                break;
                        }
                    }

                    */

                    /*
                    Main table value test
                    outputValue +=
                                    "Id : " + value.getId() + "\n" +
                                    "lat is : " + value.getLatitude() + "\n" +
                                    "lon is : " + value.getLongitude() + "\n" +
                                    "name is : " + value.getName() + "\n" +
                                    "cG is : " + value.getCardGroup() + "\n" +
                                    "mG is : " + value.getMallGroup() + "\n" +
                                    "lD is : " + value.getLongDescription() + "\n" +
                                    "sD is : " + value.getShortDescription() + "\n" +
                                    "iE is : " + value.hasTimeLimit() + "\n" +
                                    "pR is : " + value.getPingRadius();
                    */
                    /*
                    Secondary table value test
                    outputValue +=
                            "id is : " + value.getId() + "\n"
                            + "tS is : " + value.getTimeStart() + "\n"
                            + "tT is : " + value.getTimeStop();
                    */
                    /*
                    Third table value test
                    outputValue +=
                            "id is : " + value.getId() + "\n"
                            + "lat is : " + value.getLatitude() + "\n"
                            + "lon is : " + value.getLongitude() + "\n"
                            + "name is : " + value.getName() + "\n"
                            + "pR is : " + value.getPingRadius();
                    */

                    //outputValue = "thread returns " + checks;
                }
                catch (Exception e) {
                    outputValue = "Something went wrong";
                }
                //textView.setText("dbValue length is : " + dbValue.length + "\n" + outputValue);
                textView.setText(outputValue);
            }
        });

        Button iterateButton = (Button) findViewById(R.id.iterateButton);
        iterateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("I am a debug button");
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_testing, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
