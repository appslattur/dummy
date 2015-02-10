package NotificationHandler;

import android.net.Uri;

/**
 * Created by Arnar Jónsson on 9.2.2015.
 */
public class NotificationData {

    private String id;
    private String tickerTitle;
    private String title;
    private String text;
    private String icon;
    private String sound;
    private String vibrationLength;


    public NotificationData(int id, String tickerTitle, String title, String text, int icon, Uri sound, int vibrationLength) {

        this.id = Integer.toString(id);
        this.tickerTitle = tickerTitle;
        this.title = title;
        this.text = text;
        this.icon = Integer.toString(icon);
        this.sound = sound.toString();
        this.vibrationLength = Integer.toString(vibrationLength);

    }

    /////
    // Public Methods
    /////
    public int getId() {
        return Integer.parseInt(this.id);
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
        return Integer.parseInt(this.icon);
    }

    public Uri getSound() {
        return Uri.parse(this.sound);
    }

    public int getVibrationLength() {
        return Integer.parseInt(this.vibrationLength);
    }

    /////
    // Parcelable thingies
    /////
    //TODO : Shiny things
}
