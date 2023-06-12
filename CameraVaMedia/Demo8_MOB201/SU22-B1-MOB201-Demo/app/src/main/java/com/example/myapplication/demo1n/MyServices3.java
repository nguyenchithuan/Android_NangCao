package com.example.myapplication.demo1n;

import android.app.IntentService;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyServices3 extends IntentService {
    public MyServices3()
    {
        super("MyServices3");
    }
    int count=0;
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        //nhan ky tu
        char c1 = intent.getCharExtra("char",'0');
        //nhan chuoi
        String chuoi = intent.getStringExtra("chuoikiemtra");
        count = demKyTu(chuoi,c1);
    }
    @Override
    public void onDestroy() {
        Toast.makeText(this,"Tong so ky ty la: "+count, Toast.LENGTH_LONG).show();
        Toast.makeText(this,"Huy Service", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }
    private int demKyTu(String str, char c)
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
