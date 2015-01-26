package com.example.ari.appslattur;

import android.content.Context;

/**
 * Created by Arnar Jónsson on 26.1.2015.
 */
public class NotificationData {

    private Context context;
    private String tickerTitle;
    private String title;
    private String text;
    private boolean vibration;
    private boolean willRecycle;

    public NotificationData(Context context,
                            String tickerTitle,
                            String title,
                            String text,
                            boolean vibration,
                            boolean willRecycle) {
        this.context = context;
        this.tickerTitle = tickerTitle;
        this.title = title;
        this.text = text;
        this.vibration = vibration;
        this.willRecycle = willRecycle;
    }

    public Context getContext() {
        return this.context;
    }

    public String getTickerTitle() {
        return this.tickerTitle;
    }

    public String getTitle() {
        return this.title;
    }

    public String getText() {
        return this.text;
    }

    public boolean willVibrate() {
        return this.vibration;
    }

    public boolean isEternal() {
        return this.willRecycle;
    }
}
