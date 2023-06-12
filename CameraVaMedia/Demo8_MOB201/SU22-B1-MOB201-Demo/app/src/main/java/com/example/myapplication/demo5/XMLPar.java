package com.example.myapplication.demo5;

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

public class XMLPar {
    //1. Hàm lấy về tài liệu XML
    public Document getDocument(String xml) throws IOException, SAXException {
        Document document = null;
        //tạo mới 1 tài liệu trắng
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        InputSource inputSource = new InputSource();//đối tượng chứa input
        //tạo luồng đọc dữ liệu
        inputSource.setCharacterStream(new StringReader(xml));
        inputSource.setEncoding("UTF-8");
        //chuyển đổi luồng đọc thành tài liệu XML
        document = builder.parse(inputSource);
        return document;
    }
    //2. Bóc từng thành phần của tài liệu
    //Lấy về title
    public String getValue(Element node, String name)
    {
        String kq = "";
        //lấy về tất cả các nút có cùng thẻ
        NodeList nodeList = node.getElementsByTagName(name);
        //lấy về text của phần tử đầu tiên
        kq = getTextOfNode(nodeList.item(0));
        return kq;
    }//crawler
    //lấy về text của 1 phần tử
    private String getTextOfNode(Node item) {
        Node con;
        if(item!=null)
        {
            if(item.hasChildNodes())//nếu item có chứa nút con
            {
                //đưa vào vòng lặp để lấy ra text của từng thành phần
                for(con=item.getFirstChild();con!=null;con=con.getNextSibling())
                {
                    if(con.getNodeType()==Node.TEXT_NODE)
                    {
                        return con.getNodeValue();
                    }
                }
            }
        }
        return "";
    }
}
