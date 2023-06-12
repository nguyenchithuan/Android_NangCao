package edu.poly.notification_channel_id;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private Button btnSend, btnsend_2;
    private TextView tvNotificaiton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSend = findViewById(R.id.btn_send_notification);
        btnsend_2 = findViewById(R.id.btn_send_notification_2);
        tvNotificaiton = findViewById(R.id.tv_pust_notification);

        tvNotificaiton.setTextColor(Color.RED);
        tvNotificaiton.setTypeface(null, Typeface.BOLD_ITALIC);
        tvNotificaiton.setTextSize(30f);
        tvNotificaiton.setEnabled(true);
        tvNotificaiton.setVisibility(View.VISIBLE);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotification();
            }
        });

        btnsend_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotification_2();
            }
        });
    }

    private void sendNotification_2() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Uri url = Uri.parse("https://hungnttg.github.io/aksmm.mp3");

        Notification notification = new NotificationCompat.Builder(this, MyApplication.CHANNEL_ID_2)
                .setContentTitle("Title pust notification channel 2")
                .setContentText("Message pust notification chanel 2")
                .setSmallIcon(R.drawable.ic_baseline_circle_notifications_24)
                .setLargeIcon(bitmap)
                .setSound(url)
                .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(2, notification);
    }

    private void sendNotification() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.iphone);

        Notification notification = new NotificationCompat.Builder(this, MyApplication.CHANNEL_ID)
                .setContentTitle("Title pust notification channel 1")
                .setContentText("Message pust notification channel 1 Message pust notification channel 1 Message pust notification channel 1")
                .setStyle(new NotificationCompat.BigTextStyle().bigText("Message pust notification channel 1 Message pust notification channel 1 Message pust notification channel 1"))
                .setSmallIcon(R.drawable.ic_baseline_circle_notifications_24)
                .setLargeIcon(bitmap)
                .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);
    }
}