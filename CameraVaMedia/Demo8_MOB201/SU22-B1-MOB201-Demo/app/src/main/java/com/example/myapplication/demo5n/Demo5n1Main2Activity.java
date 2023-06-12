package com.example.myapplication.demo5n;

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

public class Demo5n1Main2Activity extends AppCompatActivity
implements Demo5n1LangNghe//b5. implement interface
{
    ListView listView;
    List<String> lsTitle = new ArrayList<>();
    List<String> lsLink = new ArrayList<>();
    ArrayAdapter<String> adapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo5n1_main2);
        listView = findViewById(R.id.demo5n1Lv);
        //B6.truyền interface về activity
        new DDLTSV(this).execute("https://ngoisao.vnexpress.net/rss/the-thao.rss");


    }
    //B7.đưa dữ liệu lên listview sau khi đã lấy được dữ liệu
    @Override
    public boolean layDuLieuThanhCong(boolean tc) {
        if(tc==true)
        {
            adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1,lsTitle);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(Demo5n1Main2Activity.this,
                            Demo5n1DetailMain2Activity.class);
                    String link = lsLink.get(i);
                    intent.putExtra("link",link);
                    startActivity(intent);
                }
            });
        }

        return false;
    }

    //Tạo lớp đọc dữ liệu từ server
    public class DDLTSV extends AsyncTask<String,Void,String>{
        Demo5n1LangNghe demo5n1LangNghe = null;//B2 . Khai báo interface
        public DDLTSV(Demo5n1LangNghe d)//B3. khởi tạo
        {
            this.demo5n1LangNghe = d;
        }
        //Hàm lấy dữ liệu từ server
        @Override
        protected String doInBackground(String... strings) {
            StringBuilder stringBuilder = new StringBuilder();//bộ chứa dữ liệu
            try {
                URL url = new URL(strings[0]);//gán link đọc dữ liệu
                //Tạo luồng đọc
                InputStreamReader
                        reader = new InputStreamReader(url.openConnection().getInputStream());
                //tạo bộ đệm
                BufferedReader bufferedReader = new BufferedReader(reader);
                //đọc từng dòng
                String line="";
                while ((line = bufferedReader.readLine())!=null)//nếu dòng không phải null
                {
                    stringBuilder.append(line);//đưa dòng vào bộ chứa dữ liệu
                }
                return stringBuilder.toString();//chuyển bộ chứa thành chuỗi
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        //trả dữ liệu về client
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            XMLPar1 xmlPar1 = new XMLPar1();
            try {
                Document document = xmlPar1.getDocument(s);//lấy tài liệu thô từ kết quả truyền về
                //lấy về danh sách các item
                NodeList nodeList = document.getElementsByTagName("item");
                //bóc tách từng item
                String link="";
                String title="";
                for(int i=0;i<nodeList.getLength();i++)
                {
                    Element element = (Element)nodeList.item(i);//lấy về item thứ i
                    //lấy về title và link của từng item
                    title = xmlPar1.getValue(element,"title");
                    link = xmlPar1.getValue(element,"link");
                    //đưa vào list
                    lsLink.add(link);
                    lsTitle.add(title);
                }
                //Đọc xong dữ liệu
                demo5n1LangNghe.layDuLieuThanhCong(true);//B4. interface nhận tín hiệu thành công
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
