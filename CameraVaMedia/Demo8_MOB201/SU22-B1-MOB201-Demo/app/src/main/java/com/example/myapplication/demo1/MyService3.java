package com.example.myapplication.demo1;

import android.app.IntentService;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.myapplication.MainActivity;

public class MyService3 extends IntentService {
    public MyService3() {
        super("MyService3");
    }
    int count=0;
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        char c1 = intent.getCharExtra("char",'0');//ky tu muon dem
        String chuoiCanDem = intent.getStringExtra("chk");//chuoi can dem
        count = demKyTu(chuoiCanDem,c1);

        Intent myIntent = new Intent(this, MainActivity.class);
        myIntent.putExtra("kq",count);
        this.startActivity(myIntent);
    }
    @Override
    public void onDestroy() {
        //Toast.makeText(this,"Tong so ky tu la: "+count,Toast.LENGTH_LONG).show();
        //Toast.makeText(this,"Huy service",Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }
    private int demKyTu(String str,char c)
    {
        int dem=0;
        for(int i=0;i<str.length();i++)
        {
            if(str.charAt(i)==c)
            {
                dem++;
            }
        }
        return dem;
    }
}
