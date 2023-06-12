package edu.poly.hocanimation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class OclockActivity extends AppCompatActivity {
    private ImageView imgHour, imgMinute, imgSecond;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oclock);

        imgHour = findViewById(R.id.img_hour);
        imgMinute = findViewById(R.id.img_minute);
        imgSecond = findViewById(R.id.img_second);

        startAnim(this, imgHour, R.anim.hour_anim);
        startAnim(this, imgMinute, R.anim.minute_anim);
        startAnim(this, imgSecond, R.anim.second_anim);
    }

    private void startAnim(Context context, ImageView img, int anim) {
        Animation animation = AnimationUtils.loadAnimation(context, anim);
        img.startAnimation(animation);
    }

}