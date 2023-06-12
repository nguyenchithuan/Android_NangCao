package edu.poly.labxmlparser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

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

import javax.xml.parsers.DocumentBuilder;

public class MainActivity extends AppCompatActivity {
    private EditText edData;
    private Button btnDownLoad;
    private ListView lvDanhSach;
    private ArrayList<String> lsTitle = new ArrayList<>();
    private ArrayList<String> lsLink = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edData = findViewById(R.id.ed_nhapDuLieu);
        btnDownLoad = findViewById(R.id.btn_download);
        lvDanhSach = findViewById(R.id.lvData);

        DBDL dbdl = new DBDL();
        edData.setText("https://ngoisao.vnexpress.net/rss/the-thao.rss");

        btnDownLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String resourse = edData.getText().toString().trim();
                dbdl.execute(resourse);
            }
        });
    }


    public class DBDL extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder stringBuilder = new StringBuilder();
            try {
                URL url = new URL(strings[0]); // đường link dữ liệu
                // tạo luồng đọc
                InputStreamReader reader = new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader = new BufferedReader(reader);
                String line = "";

                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }

                return stringBuilder.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            MyXMLParser myXMLParser = new MyXMLParser();

            try {
                Document document = myXMLParser.getDocument(s);// lấy về tài liệm the đường dẫn s

                NodeList nodeList = document.getElementsByTagName("item");

                String title = "";
                String link = "";


                for (int i = 0; i < nodeList.getLength(); i++) {
                    Element element = (Element) nodeList.item(i);
                    title = myXMLParser.getValue(element, "title");
                    lsTitle.add(title);
                    link = myXMLParser.getValue(element, "link");
                    lsLink.add(link);
                }

                adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, lsTitle);

                lvDanhSach.setAdapter(adapter);

                lvDanhSach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        intent = new Intent(getApplicationContext(), WebActivity.class);
                        intent.putExtra("link", lsLink.get(position));
                        startActivity(intent);
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            }
        }
    }
}