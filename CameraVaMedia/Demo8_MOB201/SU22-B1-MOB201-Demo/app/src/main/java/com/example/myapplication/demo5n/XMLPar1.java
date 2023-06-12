package com.example.myapplication.demo5n;

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

public class XMLPar1 {
    //1. Lấy về 1 tài liệu
    public Document getDocument(String xml) throws IOException, SAXException {
        Document document = null;
        //Tạo 1 tài liệu trắng
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        InputSource inputSource = new InputSource();//sử dụng luồng input
        inputSource.setCharacterStream(new StringReader(xml));//đọc từ link
        inputSource.setEncoding("UTF-8");
        document = builder.parse(inputSource);//đưa dữ liêu vào document
        return document;
    }
    //2. Lấy về giá trị của từng nút của tài liệu
    public String getValue(Element node,String name)
    {
        String kq = "";
        //lấy về danh sách các nut
        NodeList nodeList = node.getElementsByTagName(name);
        kq = getTextOfNode(nodeList.item(0));//lấy về text của item đầu tiên
        return kq;
    }

    private String getTextOfNode(Node item) {
        Node con;
        if(item!=null)
        {
            if(item.hasChildNodes())
            {
                //quet qua cac nut con
                for(con = item.getFirstChild();con!=null;con = con.getNextSibling())
                {
                    if(con.getNodeType()==Node.TEXT_NODE)
                    {
                        return con.getNodeValue();//lấy về giá trị kiểu text
                    }
                }
            }

        }
        return "";
    }
}
