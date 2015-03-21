package appslattur.appslatturdemo.NotificationHandler;

import android.net.Uri;

/**
 * Created by Arnar Jónsson on 9.2.2015.
 * Refactored 21.3.2015
 */
public class NotificationData {

    private int id;
    private String tickerTitle;
    private String title;
    private String text;
    private int icon;
    private String sound;
    private int vibrationLength;


    public NotificationData() {

        // Empty Constructor

    }

    /////
    // GET/SET Methods
    /////
    public int getId() {
        return this.id;
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

    public int getIcon() {
        return this.icon;
    }

    public Uri getSound() {
        return Uri.parse(this.sound);
    }

    public int getVibrationLength() {
        return Integer.parseInt(this.vibrationLength);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTickerTitle(String tickerTitle) {
        this.tickerTitle = tickerTitle;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public void setSound(Uri sound) {
        this.sound = sound.toString();
    }

    public void setVibrationLength(int vibrationLength) {
        this.vibrationLength = vibrationLength;
    }

    /////
    // Parcelable thingies
    /////
    //TODO : Shiny things
}
