package edu.poly.hocxmlparser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    // Có thể dùng XML hoặc JSON để truyền tải dữ liệu qua internet
    // JSON hay được dùng nhất
    // Hôm nay học XML

    // Mô hình
    // Điện thoại -> internet -> server -> data
    // Trả ngược lại giá trị : data -> server -> internet -> điện thoại

    // Bài 5 - XML Parser


    // 1. XML-html
    // là 1 loại tài liệu được truyền tải qua môi trường internet.


    // 2. Cấu trúc
    // Chanel -> title -> link -> description
    // (html -> body -> div -> a)


    // 3. Kỹ thuật lập trình
    // B1:
    // - Tạo instance chứa XML
    // - Đọc tài liệu (qua InputSource) và dùng định dạng UTF-8
    // B2:
    // - Lấy về giá trị của các nut(node)
    // - Lấy text, name
    // - Các hàm:
    // - getFirseChild, getNextSilink, getNodeType, getNodeValue
    // B3:
    // - Kết nối qua môi trường internet
    // - Dùng thư viện: AsysnTask<Input, Quá trình, output>
    // - input: doInBackground
    // - output: onPostExecute
    // B4:
    // - Cách gọi AsysTasK:
    // new Lop().execute(link);
    // - Khi gọi execute => thực chất nó gọi đến 2 hàm: doInBackGround + onPostExecute


    // Demo1: Tạo lớp đọc dữ liệu XML


    private ListView lvList;
    private List<String> lsTitle;
    private List<String> lsLink;
    private ArrayAdapter<String> adapter;
    private Intent intent;
    public static final String DUONG_LINK = "linkSend";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvList = findViewById(R.id.lvList);
        lsTitle = new ArrayList<>();
        lsLink = new ArrayList<>();

        new DBDL().execute("https://ngoisao.vnexpress.net/rss/the-thao.rss");

    }

    // 2. Tạo lớp đồng bộ dữ liệu (crawler)
    // Input là String, Output là String
    public class DBDL extends AsyncTask<String, Void, String> {
        // Hàm đồng bộ dữ liệu từ server (mỹ)
        // Điện thoại -> internet -> service -> data
        @Override
        protected String doInBackground(String... strings) {
            try {
                StringBuilder stringBuilder = new StringBuilder(); // Bộ chứ dữ liệu
                URL url = new URL(strings[0]); // đường link dữ liệu
                // tạo luồng đọc
                InputStreamReader reader = new InputStreamReader(url.openConnection().getInputStream());
                // Đọc từng dòng dữ liệu
                String line = "";
                // Tạo bộ đện
                BufferedReader bufferedReader = new BufferedReader(reader);
                while ((line = bufferedReader.readLine()) != null) { // kiểm tra điều kiện đọc
                    stringBuilder.append(line); // đưa dòng đọc được vào bộ chứa dữ liệu
                }

                return stringBuilder.toString(); // chuyển kết quả thành chuỗi
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        // Đưa dữ liệu về client (việt nam)
        // Data -> server -> internet -> điện thoại
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            XMLPar xmlPar = new XMLPar();
            try {
                Document document = xmlPar.getDocument(s);// lấy về tài liệm the đường dẫn s
                // lấy về các item
                NodeList nodeList = document.getElementsByTagName("item");
                // Lấy về link và title
                String link = "";
                String title = "";
                for (int i = 0; i < nodeList.getLength(); i++) {
                    Element element = (Element) nodeList.item(i);// lấy về item i
                    title = xmlPar.getValue(element, "title"); // lấy về title của item i
                    lsTitle.add(title); // đưa title vào list
                    link = xmlPar.getValue(element, "link");// lấy về link của item i
                    lsLink.add(link); // đưa link vào list
                }

                adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, lsTitle);
                lvList.setAdapter(adapter);
                lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String link = lsLink.get(position); // lấy link
                        intent = new Intent(getApplicationContext(), WebviewActivity.class);
                        intent.putExtra(DUONG_LINK, link);
                        startActivity(intent);
                    }
                });


            } catch (Exception e) {

            }
        }
    }
}
