package com.example.myapplication.demo5nn;

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

public class XMLPar2 {
    //1. Lấy tài liệu
    public Document getDocument(String xml) throws IOException, SAXException {
        Document document = null;
        //Tạo 1 tài liệu trắng
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();//tạo builder để chuyển dữ liệu vào tài liệu trắng
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        InputSource inputSource = new InputSource();
        inputSource.setCharacterStream(new StringReader(xml));//tạo luồng đọc
        inputSource.setEncoding("UTF-8");//định dạng
        document = builder.parse(inputSource);//điền dữ liệu đọc được vào document
        return document;
    }
    //2. lấy giá trị của nút
    public String getValue(Element node,String name)
    {
        String kq="";
        //lấy về danh sách các nút có cùng tên
        NodeList nodeList = node.getElementsByTagName(name);
        kq = getTextofNode(nodeList.item(0));
        return kq;
    }
//crawler
    private String getTextofNode(Node item) {
        Node con;
        if(item!=null)
        {
            if(item.hasChildNodes())//nếu có nut con
            {
                //duyệt qua các thằng con
                for(con = item.getFirstChild(); con!=null; con = item.getNextSibling())
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
