package com.wings.utils;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class XMLparser {

    public static void main(String args[])  {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            // Create a document builder
            DocumentBuilder builder = factory.newDocumentBuilder();
            // Parse the XML file
            Document document = builder.parse(new File("target/surefire-reports/testng-results.xml"));
            document.getDocumentElement().normalize(); // Normalize the document structure
            NodeList testList = document.getElementsByTagName("test");
            for (int i = 0; i < testList.getLength(); i++) {
                Node test = testList.item(i);
                if (test.getNodeType() == Node.ELEMENT_NODE) {
                    Element testElement = (Element) test;
                    System.out.println("Test Name :->" + testElement.getAttribute("name"));
                    System.out.println("Start-Time :->" + testElement.getAttribute("started-at"));
                    System.out.println("Finished-Time :->" + testElement.getAttribute("finished-at"));
                    System.out.println("Duration :->" + testElement.getAttribute("duration-ms"));

                    NodeList classTags = testElement.getElementsByTagName("class");
                    for (int j = 0; j < classTags.getLength(); j++) {
                        Node classNode = classTags.item(j);
                        if (classNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element classElement = (Element) classNode;
                            String className = classElement.getAttribute("name");
                            System.out.println("Class Name :->" + className);
                        }
                    }

                    NodeList testMethodTags = testElement.getElementsByTagName("test-method");
                    for (int k = 0; k < testMethodTags.getLength(); k++) {
                        Node testMethodNode = testMethodTags.item(k);
                        if (testMethodNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element testMethodElement = (Element) testMethodNode;
                            System.out.println("Test Method Name :->" + testMethodElement.getAttribute("name"));
//                            System.out.println("Signature :->" + testMethodElement.getAttribute("signature"));
                            System.out.println("Start-Time :->" + testMethodElement.getAttribute("started-at"));
                            System.out.println("Finished-Time :->" + testMethodElement.getAttribute("finished-at"));
                            System.out.println("Duration :->" + testMethodElement.getAttribute("duration-ms"));
                            System.out.println("Status :->" + testMethodElement.getAttribute("status"));
                            System.out.println("commit to Bitbucket");
                        }
                    }
                }
            }

        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }

    }
}
