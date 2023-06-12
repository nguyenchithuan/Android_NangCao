package edu.poly.assignmentnangcao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import edu.poly.assignmentnangcao.dbhelper.XMLDOMParser;

public class TinTucActivity extends AppCompatActivity {
    private ListView lvTinTuc;
    private ArrayList<String> lsTitle;
    private ArrayList<String> lsLink;
    private ArrayAdapter<String> adapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tin_tuc);

        lvTinTuc = findViewById(R.id.lvTinTuc);
        progressBar = findViewById(R.id.id_progressBar);
        progressBar.setVisibility(View.VISIBLE);

        lsTitle = new ArrayList<>();
        lsLink = new ArrayList<>();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lsTitle);
        lvTinTuc.setAdapter(adapter);
        lvTinTuc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String link = lsLink.get(position); // lấy link
                Intent intent = new Intent(getApplicationContext(), WebviewActivity.class);
                intent.putExtra("DUONG_LINK", link);
                startActivity(intent);
            }
        });

        new ReadRss().execute("https://ngoisao.vnexpress.net/rss/the-thao.rss");
    }

    public class ReadRss extends AsyncTask<String, Object, String> {

        @Override
        protected String doInBackground(String... strings) {
            try {
                StringBuilder stringBuilder = new StringBuilder();
                URL url = new URL(strings[0]);
                InputStreamReader reader = new InputStreamReader(url.openConnection().getInputStream());
                String line = "";
                BufferedReader bufferedReader = new BufferedReader(reader);
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

            XMLDOMParser xmlPar = new XMLDOMParser();
            try {
                Document document = xmlPar.getDocument(s);
                NodeList nodeList = document.getElementsByTagName("item");
                String link = "";
                String title = "";
                for (int i = 0; i < nodeList.getLength(); i++) {
                    Element element = (Element) nodeList.item(i);
                    title = xmlPar.getValue(element, "title");
                    lsTitle.add(title);
                    link = xmlPar.getValue(element, "link");
                    lsLink.add(link);
                }

                adapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
            } catch (Exception e) {

            }
        }
    }
}