package Gluggar;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import DatabaseHelper.DataBaseHelper;
import LocationChainStructure.LocationChain;
import mvp.R;


public class viewlocationsactivity extends Activity {

    //Import needed object to handle this.
    public static DataBaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vielocatonsactivity);
        db = new DataBaseHelper(this);
        getLocations();
    }
    public void newRow(LocationChain locationChain){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(R.id.container, LocationEntry.newInstance(locationChain));
        transaction.commit();
    }


    public void getLocations(){
        ArrayList<LocationChain> locations;
        locations = db.getLocationList();
        for(LocationChain l : locations){
          newRow(l);
        }
    }




    //FRAGMENT BEGINS!
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
            private LocationDetails(LocationChain chain, TextView textView, LinearLayout cl, LinearLayout subLocs){
                this.mychain = chain;
                this.textView = textView;
                this.containerlayout = cl;
                this.layoutforlinks = subLocs;
                this.textView.setText(mychain.getName());
                populateLayoutForLinks();
                this.textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(big == false ){
                           big = true;
                            containerlayout.setVisibility(View.VISIBLE);
                        }else{
                            big = false;
                            containerlayout.setVisibility(View.GONE);
                        }
                    }
                });

            }
            private void populateLayoutForLinks(){
                for(final LocationChain.LocationLink ll : mychain.getLinks()){
                    View mylayout = layoutforlinks;

                    TextView temptextview = new TextView(layoutforlinks.getContext());
                    temptextview.setText(mychain.getName() +"  ID: "+ll.getId());
                    temptextview.setLayoutParams(new ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.FILL_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT

                    ));
                    temptextview.setTextSize(TypedValue.COMPLEX_UNIT_SP,18);
                    temptextview.setVisibility(View.VISIBLE);
                    if(ll.isEnabled()){
                        //.setEnabled(0);
                        temptextview.setBackgroundColor(Color.argb(55, 0,255, 0));

                    }else{
                        //ll.setEnabled(1);
                        temptextview.setBackgroundColor(Color.argb(55, 255,102, 102));

                    }
                    temptextview.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(ll.isEnabled()){
                                ll.setEnabled(0);
                                //pretty green
                                db.setEnabledById(ll.getId(), ll.enabled);
                                view.setBackgroundColor(Color.argb(55, 255, 102, 102));
                            }else{
                                ll.setEnabled(1);
                                //pretty red
                                db.setEnabledById(ll.getId(), ll.enabled);

                                view.setBackgroundColor(Color.argb(55, 0, 255, 0));
                            }
                        }
                    });

                    layoutforlinks.addView(temptextview);
                }
            }
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState){
            View rootView = inflater.inflate(R.layout.locationentrylayout,container,false);

            TextView textview = (TextView)rootView.findViewById(R.id.locationNameTextView);
            LinearLayout ib = (LinearLayout)rootView.findViewById(R.id.myspace);
            LinearLayout iib = (LinearLayout)rootView.findViewById(R.id.allLocations);

            details = new LocationDetails(chain, textview, ib, iib);

            return rootView;
        }
    }



}
