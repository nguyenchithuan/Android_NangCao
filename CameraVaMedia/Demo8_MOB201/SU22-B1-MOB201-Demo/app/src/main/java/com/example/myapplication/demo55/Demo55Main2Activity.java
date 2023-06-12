package com.example.myapplication.demo55;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.myapplication.R;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Demo55Main2Activity extends AppCompatActivity {
    ListView listView;
    ArrayAdapter<String> adapter = null;
    List<String> lsLink = new ArrayList<>();
    List<String> lsTitle = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo55_main2);
        listView = findViewById(R.id.demo55Lv);
        new Lop().execute("https://ngoisao.vnexpress.net/rss/the-thao.rss");
    }
    //Tạo lớp đọc dữ liệu từ server
    public class Lop extends AsyncTask<String,Void,String>{
        //đọc dữ liệu từ server
        @Override
        protected String doInBackground(String... strings) {
            StringBuilder stringBuilder = new StringBuilder();//bộ chứa dữ liệu
            try {
                URL url = new URL(strings[0]);//đường dẫn
                //mở kết nối với server và tạo luồng đọc
                InputStreamReader reader = new InputStreamReader(url.openConnection().getInputStream());
                //tạo bộ đệm
                BufferedReader bufferedReader = new BufferedReader(reader);
                //đọc từng dòng
                String line="";
                while ((line=bufferedReader.readLine())!=null)//nếu dòng khác null
                {
                    stringBuilder.append(line);//đưa dòng vào bộ chứa dữ liệu
                }
                return stringBuilder.toString();//trả về kết quả
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        //trả về kết quả cho client
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            XMLPa xmlPa = new XMLPa();
            try {
                Document document = xmlPa.getDocument(s);//client lấy về toàn bộ dữ liệu thô
                //bóc tách dữ liệu
                NodeList nodeList = document.getElementsByTagName("item");
                //lấy về link và title
                String link = "";
                String title="";
                //lấy về link và title của tất cả các thành phần
                for(int i=0;i<nodeList.getLength();i++)
                {
                    Element element = (Element) nodeList.item(i);
                    link = xmlPa.getValue(element,"link");//lấy về link
                    title = xmlPa.getValue(element,"title");//lấy về title
                    //đưa vào list
                    lsLink.add(link);
                    lsTitle.add(title);
                }
                //gọi adapter
                adapter = new ArrayAdapter<>(getApplicationContext(),
                        android.R.layout.simple_list_item_1,
                        lsTitle);
                listView.setAdapter(adapter);
                //xử lý sự kiện
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(Demo55Main2Activity.this,
                                Demo55DetailMain2Activity.class);
                        String link = lsLink.get(i);
                        intent.putExtra("link",link);
                        startActivity(intent);
                    }
                });

            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
