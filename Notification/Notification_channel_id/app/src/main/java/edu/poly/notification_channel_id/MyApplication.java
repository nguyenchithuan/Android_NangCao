package edu.poly.notification_channel_id;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import java.net.URL;

public class MyApplication extends Application {

    public static final String CHANNEL_ID = "CHANNEL_1";
    public static final String CHANNEL_ID_2 = "CHANNEL_2";

    @Override
    public void onCreate() {
        super.onCreate();

        createNotificationChannel();
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Uri url = Uri.parse("https://hungnttg.github.io/aksmm.mp3");
            Uri sound = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.iphone);
            AudioAttributes attributes = new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_NOTIFICATION).build();

            // create channel 1
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Channel 1", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setSound(url, attributes);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);


            // create channel 2
            NotificationChannel channel_2 = new NotificationChannel(CHANNEL_ID_2, "Channel 2", NotificationManager.IMPORTANCE_HIGH);
            channel_2.setSound(sound, attributes);
            NotificationManager notificationManager_2 = getSystemService(NotificationManager.class);
            notificationManager_2.createNotificationChannel(channel_2);
        }
    }
}
