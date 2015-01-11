package com.example.ari.appslattur;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Ari on 9.1.2015.
 */
public class ConnectivityHelper {
    ConnectivityManager myConnectivityManager;
    public ConnectivityHelper(Context ctx){
        myConnectivityManager = (ConnectivityManager)ctx.getSystemService(ctx.CONNECTIVITY_SERVICE);
    }

    public String getAvailibleConnections(){
        String combinedString="";
        combinedString += "WIFI: "+ getWifiAvailibility();
        combinedString += " || 3G: "+ getMobileAvailibility();
        return combinedString;
    }

    private boolean getWifiAvailibility(){
        NetworkInfo wa = myConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if(wa.isConnected())return true;
        return false;
    }

    private boolean getMobileAvailibility(){
        NetworkInfo ma = myConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if(ma.isConnected())return true;
        return false;
    }
}
