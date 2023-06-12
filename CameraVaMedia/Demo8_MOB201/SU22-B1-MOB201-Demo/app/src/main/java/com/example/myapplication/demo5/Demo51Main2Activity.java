package com.example.myapplication.demo5;

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

public class Demo51Main2Activity extends AppCompatActivity
implements LangNgheComplete
{
    ListView listView;
    List<String> lsTitle = new ArrayList<>();
    List<String> lsLink = new ArrayList<>();
    ArrayAdapter<String> adapter;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo51_main2);
        listView  =findViewById(R.id.demo51Lv);
        //IV.Tạo đối tượng và truyền biến this là interface
        new DBDL(this).execute("https://ngoisao.vnexpress.net/rss/the-thao.rss");

    }
    //V. override để thực hiện công việc sau khi asynctask kết thúc
    @Override
    public boolean downloadComplete(boolean kq) {
        if(kq)
        {
            adapter = new ArrayAdapter<String>(getApplicationContext(),
                    android.R.layout.simple_list_item_1, lsTitle);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String link = lsLink.get(i);//lấy link
                    intent = new Intent(Demo51Main2Activity.this,
                            Demo51DetailMain2Activity.class);
                    intent.putExtra("linkSent",link);
                    startActivity(intent);
                }
            });
        }
        return false;
    }

    //2. Tạo lớp đồng bộ dữ liệu
    public class DBDL extends AsyncTask<String,Void,String>{
        //II. khai bao interface
            LangNgheComplete langNgheComplete = null;
            ///III. Ham khoi tao co su dung truyen interface
            public DBDL(LangNgheComplete l)
            {
                this.langNgheComplete = l;
            }

        //Hàm đồng bộ dữ liệu từ server (mỹ)
        @Override
        protected String doInBackground(String... strings) {
            StringBuilder stringBuilder = new StringBuilder();//bộ chứa dữ liệu
            try {
                URL url = new URL(strings[0]);//đường link dữ liệu
                //tạo luồng đọc
                InputStreamReader reader
                        =new InputStreamReader(url.openConnection().getInputStream());
                //đọc từng dòng dữ liệu
                String line = "";
                //Taọ bộ đệm
                BufferedReader bufferedReader = new BufferedReader(reader);
                while ((line =bufferedReader.readLine())!=null)//kiểm tra điều kiện đọc
                {
                    stringBuilder.append(line);//đưa dòng đọc được vào bộ chứa dữ liệu
                }
                return stringBuilder.toString();//chuyển kết quả thành chuỗi
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        //Đưa dữ liệu về client (việt nam)

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            XMLPar xmlPar = new XMLPar();
            try {
                Document document = xmlPar.getDocument(s);//lấy về tài liệu theo đường dẫn s
                //lấy về các item
                NodeList nodeList = document.getElementsByTagName("item");
                //lấy về link và title
                String link = "";
                String title ="";
                for(int i=0;i<nodeList.getLength();i++)
                {
                    Element element = (Element)nodeList.item(i);//lấy về item i
                    title = xmlPar.getValue(element,"title");//lấy về title của item i
                    lsTitle.add(title);//Đưa vào list
                    link = xmlPar.getValue(element,"link");//lấy về link của item i
                    lsLink.add(link);//đưa link vào list
                }
                this.langNgheComplete.downloadComplete(true);



            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
