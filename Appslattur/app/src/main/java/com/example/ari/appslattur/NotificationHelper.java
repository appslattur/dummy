package com.example.ari.appslattur;

import android.content.Context;
import android.content.Intent;

/**
 * Created by Arnar Jónsson on 22.1.2015.
 */
public class NotificationHelper {

    NotificationHandler nHandler;

    public NotificationHelper(Context context, String tickerTitle, String title, String text, int lifeSpan) {

        nHandler = new NotificationHandler(context,
                tickerTitle,
                title,
                text,
                lifeSpan,
                R.drawable.ic_launcher,
                null,
                null,
                true,
                null,
                true);

        nHandler.sendNotification();

    }
}
