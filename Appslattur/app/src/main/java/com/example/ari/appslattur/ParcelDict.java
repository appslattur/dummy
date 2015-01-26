package com.example.ari.appslattur;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Arnar Jónsson on 23.1.2015.
 */
public class ParcelDict implements Parcelable {

    private String tickerTitle;
    private String title;
    private String text;
    private String delay;
    private String lifeSpan;

    public ParcelDict(String tickerTitle, String title, String text, int delay, int lifeSpan) {

        this.tickerTitle = tickerTitle;
        this.title = title;
        this.text = text;
        this.delay = Integer.toString(delay);
        this.lifeSpan = Integer.toString(lifeSpan);
    }

    // Get Methods, set not needed
    public String getTickerTitle() {
        return this.tickerTitle;
    }

    public String getTitle() {
        return this.title;
    }

    public String getText() {
        return this.text;
    }

    public int getDelay() {
        try {
            return Integer.parseInt(this.delay);
        }
        catch (NumberFormatException e) {
            //Do Nothing
        }
        return 0;
    }

    public int getLifeSpan() {
        try {
            return Integer.parseInt(this.lifeSpan);
        }
        catch (NumberFormatException e) {
            //Do Nothing
        }
        return 0;
    }

    public ParcelDict(Parcel in) {
        String[] data = new String[5];

        in.readStringArray(data);
        this.tickerTitle = data[0];
        this.title = data[1];
        this.text = data[2];
        this.delay = data[3];
        this.lifeSpan = data[4];
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.tickerTitle);
        dest.writeString(this.title);
        dest.writeString(this.text);
        dest.writeString(this.delay);
        dest.writeString(this.lifeSpan);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        public ParcelDict createFromParcel(Parcel in) {
            return new ParcelDict(in);
        }
        public ParcelDict[] newArray(int size) {
            return new ParcelDict[size];
        }
    };
}

