package edu.poly.cameraandmedia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MediaActivity extends AppCompatActivity {
    private Button btnStart, btnStop;
    private ListView lvMedia;
    private List<String> lsName = new ArrayList<>(); // danh sách file nhạc
    private List<Uri> lsUri = new ArrayList<>(); // đường dẫn file nhạc
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);
        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);
        lvMedia = findViewById(R.id.lv_media);


        getListMp3();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lsName);
        lvMedia.setAdapter(arrayAdapter);
        lvMedia.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    mediaPlayer.reset();
                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(),
                            e.getMessage(),Toast.LENGTH_LONG).show();
                }
                String name = lsName.get(i);//lay ve file nhac can phat
                Uri uri2 = getRawUri(name);//lay ve duong dan uri
                mediaPlayer = MediaPlayer.create(getApplicationContext(),uri2);
                mediaPlayer.start();
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    mediaPlayer.stop();
                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(),
                            e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });
        //mo file nhac qua mang
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://hungnttg.github.io/aksmm.mp3";
                try {
                    mediaPlayer = MediaPlayer.create(getApplicationContext(),
                            Uri.parse(url));
                    mediaPlayer.start();
                    mediaPlayer.prepare();
                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(),
                            e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void getListMp3() {
        Field[] fields = R.raw.class.getFields(); // lấy tất cả các trường trong raw
        for (int i = 0; i < fields.length; i++) {
            lsName.add(fields[i].getName()); // đưa tên file vào list
            Uri uri = getRawUri(fields[i].getName()); // lấy uri
            lsUri.add(uri);
        }
    }

    // lấy về đường dẫn uri
    private Uri getRawUri(String name) {
        return Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                File.pathSeparator + File.separator + File.separator +
                getPackageName() + "/raw/"+name);
        // separator là /
    }
}