package com.example.myapplication.demo5m;

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

public class PhanTichXML {
    //1. Lay ve toan bo tai lieu
    public Document getDocument(String str) throws IOException, SAXException {
        Document document = null;
        //tạo 1 tài liệu trắng
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
             builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        InputSource inputSource = new InputSource();//bộ đọc dữ liệu đầu vào
        inputSource.setCharacterStream(new StringReader(str));//đưa dữ liệu vào luồng đọc
        inputSource.setEncoding("UTF-8");
        document = builder.parse(inputSource);//đưa dữ liệu đầu vào -> document
        return document;
    }
    //2. Lấy về giá trị của các nút
    public String getValue(Element node,String name)
    {
        String kq = "";
        //lấy về tất cả các item
        NodeList nodeList  = node.getElementsByTagName(name);
        kq = getTextOfNode(nodeList.item(0));
        return kq;
    }

    private String getTextOfNode(Node item) {
        Node con;
        if(item!=null)//kiem tra khac null
        {
            if(item.hasChildNodes())//neu co nut con
            {
                //for cac nut con
                for(con = item.getFirstChild();con!=null;con = con.getNextSibling())
                {
                    if(con.getNodeType() == Node.TEXT_NODE)//neu la kieu text
                    {
                        return con.getNodeValue();//tra ve ket qua la text
                    }
                }
            }
        }
        return "";
    }
}
