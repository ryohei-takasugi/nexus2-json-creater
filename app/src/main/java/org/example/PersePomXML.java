package org.example;

import java.io.ByteArrayInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class PersePomXML {
    private final String xmlContent;

    public PersePomXML(String xmlContent) {
        this.xmlContent = xmlContent;
    }

    public String getKind() throws Exception {
        Document pomDocument = parseDocument();
        NodeList propertiesList = pomDocument.getElementsByTagName("properties");

        if (propertiesList.getLength() > 0) {
            Element properties = (Element) propertiesList.item(0);

            return getTagValue("kind", properties);
        }
        return null;
    }

    public String getQName() throws Exception {
        Document pomDocument = parseDocument();
        NodeList propertiesList = pomDocument.getElementsByTagName("properties");

        if (propertiesList.getLength() > 0) {
            Element properties = (Element) propertiesList.item(0);

            return getTagValue("qname", properties);
        }
        return null;
    }

    private String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag);
        if (nodeList != null && nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        }
        return null;
    }

    private Document parseDocument() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(new ByteArrayInputStream(xmlContent.getBytes()));
    }
}
