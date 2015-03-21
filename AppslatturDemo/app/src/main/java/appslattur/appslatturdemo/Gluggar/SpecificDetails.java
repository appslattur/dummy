package appslattur.appslatturdemo.Gluggar;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import appslattur.appslatturdemo.DatabaseHelper.DataBaseHelper;
import appslattur.appslatturdemo.R;

public class SpecificDetails extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_details);
        String locationID ="";

        Bundle b=getIntent().getExtras();
        if(b != null){
            locationID = b.getString("locationID");
        }
        populateView(locationID);


    }

    public void populateView(String s){
        DataBaseHelper db = new DataBaseHelper(this);
        int id = Integer.parseInt(s);



        TextView details = (TextView)findViewById(R.id.details);
        TextView detailsname = (TextView)findViewById(R.id.locationname);

        details.setText( db.getLongDescById(id));
        detailsname.setText( db.getNameById(id));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_specific_details, menu);
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
