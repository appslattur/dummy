package appslattur.appslatturdemo;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import appslattur.appslatturdemo.AsyncThreads.DBAsyncTask;
import appslattur.appslatturdemo.DataBaseHandler.DatabaseEntry;
import appslattur.appslatturdemo.DataBaseHandler.DatabaseEntryTask;
import appslattur.appslatturdemo.DataBaseHandler.DatabaseValue;
import appslattur.appslatturdemo.DataBaseHandler.DatabaseValueTask;
import appslattur.appslatturdemo.DataStructure.DBEntryFS;
import appslattur.appslatturdemo.DataStructure.DBEntryFSMG;
import appslattur.appslatturdemo.DataStructure.DBEntryFSTS;
import appslattur.appslatturdemo.DataStructure.DBEntryObject;
import appslattur.appslatturdemo.DataStructure.DBEntryRESPONSE;
import appslattur.appslatturdemo.DataStructure.DBFSEntryTask;


public class TestingActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);

        /////
        // Inheritance testing
        /////
        DBFSEntryTask eTask = new DBFSEntryTask();
        eTask.setId(1);

        final TextView textView = (TextView) findViewById(R.id.outputView);



        Button entryButton = (Button) findViewById(R.id.entryButton);
        entryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBEntryRESPONSE[] res;
                DBEntryFS fsEntry = new DBEntryFS(1,
                        0.0,
                        0.0,
                        "ShopName",
                        "cardName",
                        "MallGroup",
                        "longDesc",
                        "ShortDesc",
                        true,
                        5);
                DBEntryFSTS fstsEntry = new DBEntryFSTS(2,
                        0.0,
                        0.0,
                        "ShopName",
                        "cardName",
                        "MallGroup",
                        "longDesc",
                        "ShortDesc",
                        true,
                        5,
                        "00:00:00",
                        "00:00:00");
                DBEntryFSMG fsmgEntry = new DBEntryFSMG(1,
                        0.0,
                        0.0,
                        "ShopName",
                        5);
                DBEntryObject[] input = new DBEntryObject[2];
                input[0] =  fsEntry;
                input[1] = fstsEntry;
                //input[2] = fsmgEntry;
                try {
                    res = (DBEntryRESPONSE[]) new DBAsyncTask(getApplicationContext()).
                            execute(input).get();
                    DBEntryRESPONSE output = res[0];
                    textView.setText("Response message is : " +
                            output.getResponseMessage());
                }
                catch (Exception e) {
                    textView.setText("Failed to resolve task output");
                }
            }
        });

        Button valueButton = (Button) findViewById(R.id.valueButton);


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
