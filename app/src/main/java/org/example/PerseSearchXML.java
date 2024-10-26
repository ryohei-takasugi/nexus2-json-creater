package org.example;

import java.util.function.Consumer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class PerseSearchXML {

    private final String xmlContent;

    public PerseSearchXML(String xmlContent) {
        this.xmlContent = xmlContent;
    }

    public void getBaseValue(Consumer<Content> callback) throws Exception {
        NodeList artifacts = getArtifact();

        for (int i = 0; i < artifacts.getLength(); i++) {
            Node artifactNode = artifacts.item(i);

            if (artifactNode.getNodeType() == Node.ELEMENT_NODE) {
                Element artifactElement = (Element) artifactNode;

                // Extract groupId, artifactId, and version
                String groupId = getTagValue("groupId", artifactElement);
                String artifactId = getTagValue("artifactId", artifactElement);
                String version = getTagValue("version", artifactElement);

                Content content = new Content(groupId, artifactId, version);
                // Check for extension "module"
                NodeList artifactLinks = artifactElement.getElementsByTagName("artifactLink");
                for (int j = 0; j < artifactLinks.getLength(); j++) {
                    Element artifactLink = (Element) artifactLinks.item(j);
                    String extension = getTagValue("extension", artifactLink);

                    content.setExt(extension);
                    callback.accept(content);
                }
            }
        }
    }

    private String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag);
        if (nodeList != null && nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        }
        return null;
    }

    private NodeList getArtifact() throws Exception {
        Document document = parseDocument();
        return document.getElementsByTagName("artifact");
    }

    private Document parseDocument() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(new java.io.ByteArrayInputStream(xmlContent.getBytes()));
    }
}