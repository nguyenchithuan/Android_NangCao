package com.example.myapplication.demo5m;

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

public class Demo5mMain2Activity extends AppCompatActivity {
    ListView listView;
    List<String> lsLink = new ArrayList<>();
    List<String> lsTitle = new ArrayList<>();
    ArrayAdapter<String> adapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo5m_main2);
        listView = findViewById(R.id.demo5mListview);
        new LopKetNoiVoiServer()
                .execute("https://ngoisao.vnexpress.net/rss/the-thao.rss");
    }

    public class LopKetNoiVoiServer extends AsyncTask<String,Void,String>{
        //doc du lieu tu server
        @Override
        protected String doInBackground(String... strings) {
            StringBuilder builder = new StringBuilder();
            try {
                URL url = new URL(strings[0]);//duong dan doc du lieu
                InputStreamReader inputStreamReader
                        =new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                //đọc theo dòng
                String line = "";
                while ((line=bufferedReader.readLine())!=null)
                {
                    builder.append(line);
                }
                return builder.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
             return builder.toString();
        }
        //tra ket qua ve nguoi dung
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            PhanTichXML ptxml = new PhanTichXML();
            try {
                Document document = ptxml.getDocument(s);//trả về tài liệu thô
                //Lấy về danh sách các nút
                NodeList nodeList = document.getElementsByTagName("item");
                //
                String link="";
                String title="";
                for(int i = 0;i<nodeList.getLength();i++)
                {
                    //lấy về phần tử thứ i
                    Element element = (Element)nodeList.item(i);
                    //lấy về title và link
                    title = ptxml.getValue(element,"title");
                    link = ptxml.getValue(element,"link");
                    //đưa vào list
                    lsLink.add(link);
                    lsTitle.add(title);
                }
                adapter = new ArrayAdapter<>(getApplicationContext(),
                        android.R.layout.simple_list_item_1,lsTitle);
                adapter.notifyDataSetChanged();
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(Demo5mMain2Activity.this,
                                Demo5mDetailMain2Activity.class);
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
