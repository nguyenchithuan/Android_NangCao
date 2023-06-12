package com.example.myapplication.demo7n;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.myapplication.R;

public class Demo75nMain2Activity extends AppCompatActivity {
    ImageView imgGio, imgPhut, imgGiay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo75n_main2);
        imgGio = findViewById(R.id.demo75nGio);
        imgPhut  =findViewById(R.id.demo75nPhut);
        imgGiay = findViewById(R.id.demo75nGiay);
        startAni();
    }

    private void startAni() {
        Animation aniGio = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.demo7n5_gio);
        imgGio.startAnimation(aniGio);
        //-----
        Animation aniPhut = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.demo75n_phut);
        imgPhut.startAnimation(aniPhut);
        //--
        Animation aniGiay = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.demo75n_giay);
        imgGiay.startAnimation(aniGiay);
    }
}
