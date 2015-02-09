package com.example.ari.appslattur;

import android.content.Context;
import android.content.Intent;

/**
 * Created by Arnar Jónsson on 22.1.2015.
 */
public class NotificationHelper {

    NotificationHandler nHandler;

    public NotificationHelper(Context context, String title) {

        nHandler = new NotificationHandler(context,
                "Random Ticker Title",
                title,
                "Random Text",
                R.drawable.ic_launcher,
                null,
                null,
                true,
                null,
                true);

        nHandler.sendNotification();

    }
}
