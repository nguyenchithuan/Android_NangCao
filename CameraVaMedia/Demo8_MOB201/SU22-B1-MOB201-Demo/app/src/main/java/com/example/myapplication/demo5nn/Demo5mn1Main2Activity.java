package com.example.myapplication.demo5nn;

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

public class Demo5mn1Main2Activity extends AppCompatActivity
implements DieuTietInterface //B4. Cài đặt interface
{
    ListView listView;
    List<String> lsLink = new ArrayList<>();
    List<String> lsTitle = new ArrayList<>();
    ArrayAdapter<String> adapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo5mn1_main2);
        listView  =findViewById(R.id.demo5nn1Lv);
        //B6 - truyền người điều tiết xúc cát cho server
        new KNVSV1(this).execute("https://ngoisao.vnexpress.net/rss/the-thao.rss");

    }

    @Override
    public boolean daXucCatXongChua(boolean xc) {
        //B5 - Cài đặt phương thức (ô tô di chuyển sau khi xúc cát xong)
        if(xc==true)
        {
            adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,
                    lsTitle);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(Demo5mn1Main2Activity.this,
                            Demo5nn1DetailMain2Activity.class);
                    String link = lsLink.get(i);
                    intent.putExtra("link",link);
                    startActivity(intent);
                }
            });
        }
        return false;
    }

    //Tạo lớp kết nối với server
    public class KNVSV1 extends AsyncTask<String,Void,String>{
        //b2. Khai báo người điều hành xúc cát
        DieuTietInterface dieuTietInterface = null;
        public KNVSV1(DieuTietInterface ndt)
        {
            this.dieuTietInterface = ndt;
        }
        //hàm đọc dữ liệu từ server (mỹ)
        @Override
        protected String doInBackground(String... strings) {
            //Khai báo đối tượng chứa dữ liệu
            StringBuilder stringBuilder = new StringBuilder();
            try {
                URL url = new URL(strings[0]);//lấy đường dẫn đọc dữ liệu
                //kết nối với server và tạo luồng đọc
                InputStreamReader
                        reader = new InputStreamReader(url.openConnection().getInputStream());
                //tạo bộ đệm
                BufferedReader bufferedReader = new BufferedReader(reader);
                //đọc từng dòng
                String line="";
                while ((line = bufferedReader.readLine())!=null)//nếu dòng khác null
                {
                    stringBuilder.append(line);//đưa dòng đọc được vào bộ chứa dữ liệu
                }
                return stringBuilder.toString();//trả về kết quả
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
        //hàm trả dữ liệu về client (việt nam)
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            XMLPar2 xmlPar2 = new XMLPar2();
            try {
                Document document = xmlPar2.getDocument(s);//lấy về dữ liệu thô
                //lấy về các item
                NodeList nodeList = document.getElementsByTagName("item");
                //bóc các item
                String link = "";
                String title = "";
                for(int i=0;i<nodeList.getLength();i++)
                {
                    //lấy về item thứ i
                    Element element = (Element)nodeList.item(i);
                    //lấy link + title
                    link = xmlPar2.getValue(element,"link");
                    title = xmlPar2.getValue(element,"title");
                    //đưa vào list
                    lsLink.add(link);
                    lsTitle.add(title);
                }
                //B3 - khi Xúc cát xong => người điều tiết báo đã xong
                dieuTietInterface.daXucCatXongChua(true);

            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
