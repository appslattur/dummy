package com.example.ari.appslattur;

/**
 * Created by Arnar Jónsson on 26.1.2015.
 */
public class NotificationHelper {

    private NotificationHandler nHandler;

    public NotificationHelper(NotificationData data) {

        (new Thread(new NotificationHandler(data))).start();
    }
}
