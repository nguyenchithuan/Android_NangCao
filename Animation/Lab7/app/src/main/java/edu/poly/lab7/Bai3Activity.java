package edu.poly.lab7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Bai3Activity extends AppCompatActivity {
    private ImageView imgHour, imgMinute, imgSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai3);

        imgHour = findViewById(R.id.img_hour);
        imgMinute = findViewById(R.id.img_minute);
        imgSecond = findViewById(R.id.img_second);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.hour);
        imgHour.startAnimation(animation);

        Animation animation1 = AnimationUtils.loadAnimation(this, R.anim.minute);
        imgMinute.startAnimation(animation1);

        Animation animation2 = AnimationUtils.loadAnimation(this, R.anim.second);
        imgSecond.startAnimation(animation2);
    }
}