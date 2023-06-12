package edu.poly.hocanimation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    // Bải 7: Animation
    // - Ưu điểm: đẹp
    // - Nhược điểm: tốn bộ nhớ

    private ImageView imgBai1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgBai1 = findViewById(R.id.img_bai1);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.translate);
        imgBai1.startAnimation(animation);
    }
}