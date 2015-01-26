package com.example.ari.appslattur;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Arnar Jónsson on 22.1.2015.
 */
public class AutoStart extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent startingIntent = new Intent(context, DummyService.class);
        context.startService(startingIntent);
        Log.i("AutoStart", "started");
    }
}
