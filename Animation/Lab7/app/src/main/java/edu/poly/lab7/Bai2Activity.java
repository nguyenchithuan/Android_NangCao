package edu.poly.lab7;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class Bai2Activity extends AppCompatActivity {
    private ImageView imgBai2;
    private Button btnAll, btnDoremon, btnNobita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai2);

        imgBai2 = findViewById(R.id.img_bai2);
        btnAll = findViewById(R.id.btn_all);
        btnDoremon = findViewById(R.id.btn_doraemon);
        btnNobita = findViewById(R.id.btn_nobita);

        btnAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgBai2.setImageResource(R.drawable.all);
                Animation animation = AnimationUtils.loadAnimation(Bai2Activity.this, R.anim.translate_bai2);
                imgBai2.startAnimation(animation);
            }
        });

        btnDoremon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgBai2.setImageResource(R.drawable.doraemon);
                Animation animation = AnimationUtils.loadAnimation(Bai2Activity.this, R.anim.translate_bai2);
                imgBai2.startAnimation(animation);
            }
        });

        btnNobita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgBai2.setImageResource(R.drawable.nobita);
                Animation animation = AnimationUtils.loadAnimation(Bai2Activity.this, R.anim.translate_bai2);
                imgBai2.startAnimation(animation);
            }
        });

    }
}