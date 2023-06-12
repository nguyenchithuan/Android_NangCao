package com.example.myapplication.demo7n;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.myapplication.R;

public class Demo71nMain2Activity extends AppCompatActivity {
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo71n_main2);
        imageView  =findViewById(R.id.demo71nImg);
        Animation animation = AnimationUtils.loadAnimation(this,
                R.anim.demo7n4);
        imageView.startAnimation(animation);
    }
}
