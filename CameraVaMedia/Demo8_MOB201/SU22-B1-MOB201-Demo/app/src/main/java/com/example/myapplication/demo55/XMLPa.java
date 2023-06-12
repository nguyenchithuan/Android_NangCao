package com.example.myapplication.demo55;

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

public class XMLPa {
    //1. Lấy toàn bộ tài liệu
    public Document getDocument(String xml) throws IOException, SAXException {
        Document document = null;
        //tạo 1 tài liệu trắng
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder=null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        InputSource inputSource = new InputSource();//luồng đọc nguồn dữ liệu
        inputSource.setCharacterStream(new StringReader(xml));//đưa luồng đọc vào inputSource
        inputSource.setEncoding("UTF-8");
        document = builder.parse(inputSource);//đưa dữ liệu đọc được vào tài liệu
        return document;
    }
    //2. Lấy giá trị cần thiết
    public String getValue(Element node,String name)
    {
        String kq = "";
        //Lấy về danh sách các nút có cùng tên
        NodeList nodeList = node.getElementsByTagName(name);
        //lấy về text của nút đầu tiên
        kq = getTextOfNode(nodeList.item(0));
        return kq;
    }
//crawler
    private String getTextOfNode(Node item) {
        Node con;
        if(item!=null)//kiểm tra item khác null
        {
            if(item.hasChildNodes())//nếu có nút con
            {
                //duyệt qua các con của item
                for(con = item.getFirstChild();con!=null;con = item.getNextSibling())
                {
                    if(con.getNodeType()==Node.TEXT_NODE)//nếu là kiểu Text
                    {
                        return con.getNodeValue();//thì trả về giá trị
                    }
                }
            }
        }
        return "";
    }
}
