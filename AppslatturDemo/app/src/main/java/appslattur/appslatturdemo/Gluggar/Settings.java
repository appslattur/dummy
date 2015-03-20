package appslattur.appslatturdemo.Gluggar;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import appslattur.appslatturdemo.DatabaseHelper.DataBaseHelper;

import appslattur.appslatturdemo.R;

public class Settings extends ActionBarActivity {
    DataBaseHelper mdb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mdb = new DataBaseHelper(this);

        findViewById(R.id.saveButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Settings.this, SaveThisLocation.class );
                Settings.this.startActivity(intent);
            }
        });


        findViewById(R.id.clearTable).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mdb.clearTable();
                mdb.populateTable();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
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
