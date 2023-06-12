package com.example.myapplication.demo7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.myapplication.R;

public class Demo75Main2Activity extends AppCompatActivity {
    ImageView imgGio,imgPhut,imgGiay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo75_main2);
        imgGio = findViewById(R.id.demo75Gio);
        imgPhut = findViewById(R.id.demo75Phut);
        imgGiay = findViewById(R.id.demo75Giay);
        startAnim();
    }

    private void startAnim() {
        Animation animGio = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.demo75_anim_gio);
        imgGio.startAnimation(animGio);
        //--
        Animation animPhut = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.demo75_anim_phut);
        imgPhut.startAnimation(animPhut);
        //--
        Animation animGiay = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.demo75_anim_giay);
        imgGiay.startAnimation(animGiay);
    }

}
