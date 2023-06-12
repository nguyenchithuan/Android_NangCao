package edu.poly.labxmlparser;

import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MyXMLParser {

    // Hàm lấy tài liệu xml
    public Document getDocument(String xml) throws IOException, SAXException {
        Document document = null;

        // Tạo tài liệu trắng
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;

        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        // tạo luông đọc dữ liêu
        InputSource inputSource = new InputSource();

        inputSource.setCharacterStream(new StringReader(xml));
        inputSource.setEncoding("UFT-8");

        // chuyển đổi luồng đọc dữ liệu thành file xml
        document = builder.parse(inputSource);


        return document;

    }

    public String getValue(Element node, String name) {
        String kq = "";

        // lấy ra tất cả các thẻ
        NodeList nodeList = node.getElementsByTagName(name);
        // Lấy ra thẻ ở vị trí đầu tiên
        kq = getTextOfNode(nodeList.item(0));
        return kq;
    }

    private String getTextOfNode(Node item) {
        Node node;

        if(item != null) {
            if(item.hasChildNodes()) {
                for (node = item.getFirstChild(); node != null; node = item.getNextSibling()) {
                    if(node.getNodeType() == Node.TEXT_NODE) {
                        return node.getNodeValue();
                    }
                }
            }
        }

        return "";
    }

}
