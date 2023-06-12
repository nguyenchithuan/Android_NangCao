package edu.poly.lab7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Bai1Activity extends AppCompatActivity {
    private ImageView imgBai1;
    private Button btnRotate, btnMove, btnRoom, btnAlpha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bai1_main);

        imgBai1 = findViewById(R.id.img_bai1);
        btnRotate = findViewById(R.id.btn_rotate);
        btnMove = findViewById(R.id.btn_move);
        btnRoom = findViewById(R.id.btn_room);
        btnAlpha = findViewById(R.id.btn_alpha);


        btnRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setStartAnimation(Bai1Activity.this, imgBai1, R.anim.rotate_bai1);
            }
        });

        btnMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setStartAnimation(Bai1Activity.this, imgBai1, R.anim.translate_bai1);
            }
        });

        btnRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setStartAnimation(Bai1Activity.this, imgBai1, R.anim.room_bai1);
            }
        });
    }

    public void setStartAnimation(Context context, ImageView img, int anim) {
        Animation animation = AnimationUtils.loadAnimation(context, anim);
        img.startAnimation(animation);
    }

}