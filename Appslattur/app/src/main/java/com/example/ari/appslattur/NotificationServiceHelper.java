package com.example.ari.appslattur;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Arnar Jónsson on 22.1.2015.
 */
public class NotificationServiceHelper {

    public NotificationServiceHelper(Context context, String title, int delay) {

        Intent nIntent = new Intent(context, NotificationServiceHandler.class);
        nIntent.putExtra("titleMessage", "Random title");
        nIntent.putExtra("delay", 30000);
        context.startService(nIntent);

    }
}
