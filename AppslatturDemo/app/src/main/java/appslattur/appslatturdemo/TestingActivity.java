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

                DatabaseEntry dbEntry = new DatabaseEntry(
                        idNo++,
                        "clockText",
                        "clockText"
                );
                try {
                    insertId = new DatabaseEntryTask(getApplicationContext()).
                            execute(dbEntry).get();
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
            int idTest = 3;
            @Override
            public void onClick(View v) {
                String outputValue = "";
                DatabaseValue[] dbValue = new DatabaseValue[1];
                try {
                    dbValue = new DatabaseValueTask(getApplicationContext(),"StudentCard").
                            execute(idTest++).get();

                    DatabaseValue value = dbValue[0];
                    outputValue = "Output from value/s is/are following : " + "\n";
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

                    boolean checks = false;
                    if(dbValue != null) {
                        checks = true;
                    }
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
