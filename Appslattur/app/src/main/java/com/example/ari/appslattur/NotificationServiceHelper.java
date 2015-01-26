package com.example.ari.appslattur;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Arnar Jónsson on 22.1.2015.
 */
public class NotificationServiceHelper {

    public NotificationServiceHelper(Context context, String tickerTitle, String title, String text, int delay, int lifeSpan) {

        Intent nIntent = new Intent(context, NotificationServiceHandler.class);
        nIntent.putExtra("parcelDict", new ParcelDict(tickerTitle, title, text, delay, lifeSpan));
        context.startService(nIntent);

    }
}
