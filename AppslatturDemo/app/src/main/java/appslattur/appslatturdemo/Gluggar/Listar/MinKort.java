package appslattur.appslatturdemo.Gluggar.Listar;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import appslattur.appslatturdemo.DatabaseHelper.DataBaseHelper;
import appslattur.appslatturdemo.LocationChainStructure.LocationChain;
import appslattur.appslatturdemo.R;

public class MinKort extends Activity {

    public static DataBaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_min_kort);
        db = new DataBaseHelper(this);
        getLocations();
    }

    public void getLocations(){
        ArrayList<LocationChain> locations;
        locations = db.getLocationList();
        for(LocationChain l : locations){
            newRow(l);
        }
    }
    public void newRow(LocationChain locationChain){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(R.id.container, LocationEntry.newInstance(locationChain));
        transaction.commit();
    }
    public static class LocationEntry extends Fragment {
        LocationDetails details;
        LocationChain chain;
        Location loc;
        public LocationEntry(){

        }
        public static LocationEntry newInstance(LocationChain locationChain){
            LocationEntry locationEntry = new LocationEntry();
            //locationEntry.details.setDetails(location);
            locationEntry.chain= locationChain;
            return locationEntry;
        }

        public void setupDetails(){

        }

        private class LocationDetails{
            private TextView textView;
            private LocationChain mychain;
            private LinearLayout containerlayout, layoutforlinks;
            private boolean big= false;
            private LocationDetails(LocationChain chain, TextView textView){
                this.mychain = chain;
                this.textView = textView;

                this.textView.setText(mychain.getName());






            }


        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState){
            View rootView = inflater.inflate(R.layout.locationentrylayout,container,false);

            TextView textview = (TextView)rootView.findViewById(R.id.locationNameTextView);

            details = new LocationDetails(chain, textview);

            return rootView;
        }
    }






}
