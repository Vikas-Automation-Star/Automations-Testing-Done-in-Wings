package com.wings.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.net.InetAddress;
import java.nio.file.*;
import java.util.regex.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

//public class XMLparser {
//
//    public static String testDraft(String details,String status, String name,String duration){
//
//        String testDetailsDraft="  <tr>\n" +
//                "                    <td align=\"left\"valign=\"middle\"\n" +
//                "                        style=\"border: 1px solid #b6b6b6; font: normal 13px 'Segoe UI', Arial, Helvetica, sans-serif;\">Test\n" +
//                "                        Case ID</td>\n" +
//                "                    <td align=\"left\"valign=\"middle\"\n" +
//                "                        style=\"border: 1px solid #b6b6b6; font: normal 13px 'Segoe UI', Arial, Helvetica, sans-serif;\">Test\n" +
//                "                        Script Name</td>\n" +
//                "                    <td align=\"left\"valign=\"middle\"\n" +
//                "                        style=\"border: 1px solid #b6b6b6; font: normal 13px 'Segoe UI', Arial, Helvetica, sans-serif;\">Description</td>\n" +
//                "                    <td align=\"left\"valign=\"middle\"\n" +
//                "                        style=\"border: 1px solid #b6b6b6; font: normal 13px 'Segoe UI', Arial, Helvetica, sans-serif;\">#status#</td>\n" +
//                "                    <td align=\"left\"valign=\"middle\"\n" +
//                "                        style=\"border: 1px solid #b6b6b6; font: normal 13px 'Segoe UI', Arial, Helvetica, sans-serif;\">Test\n" +
//                "                        Duration</td>\n" +
//                "                </tr>";
//
//
//    }
//        public static void main(String[] args) {
//            try {
//                // Initialize XML parser
//                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//                DocumentBuilder builder = factory.newDocumentBuilder();
//                Document document = builder.parse(new File("target/surefire-reports/testng-results.xml"));
//                document.getDocumentElement().normalize();
//
//                NodeList suiteList = document.getElementsByTagName("suite");
//                String suiteName = "";
//                String executionMachine = InetAddress.getLocalHost().getHostName();
//                String executionStartTime = "";
//                String executionEndTime = "";
//                long executionTimeMillis = 0;
//                String os=System.getProperty("os.name");
//                if (suiteList.getLength() > 0) {
//                    Element suiteElement = (Element) suiteList.item(0);
//                    suiteName = suiteElement.getAttribute("name");
//                    executionStartTime = suiteElement.getAttribute("started-at");
//                    executionEndTime = suiteElement.getAttribute("finished-at");
//
//                    // Calculate total execution time in milliseconds
//                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
//                    Date start = sdf.parse(executionStartTime);
//                    Date end = sdf.parse(executionEndTime);
//                    executionTimeMillis = end.getTime() - start.getTime();
//                }
//
//                // Retrieve test pass/fail information
//                int passedTests = 0;
//                int failedTests = 0;
//                int skippedTests = 0;
//
//                String testDetails="";
//
//                NodeList testList = document.getElementsByTagName("test");
//                for (int i = 0; i < testList.getLength(); i++) {
//                    Element testElement = (Element) testList.item(i);
//                    NodeList testMethodTags = testElement.getElementsByTagName("test-method");
//                    for (int k = 0; k < testMethodTags.getLength(); k++) {
//                        Element testMethodElement = (Element) testMethodTags.item(k);
//                        String status = testMethodElement.getAttribute("status"),name=testMethodElement.getAttribute("name");
//                        double duration= Double.parseDouble(testMethodElement.getAttribute("duration-ms"))/1000;
//                        switch (status) {
//                            case "PASS":
//                                passedTests++;
//                                testDetails+=testDetailsDraft;
//                                testDraft(testDetails,status,name, String.valueOf(duration));
//                                break;
//                            case "FAIL":
//                                failedTests++;
//                                testDetails+=testDetailsDraft;
//                                testDraft(testDetails,status,name, String.valueOf(duration));
//                                break;
//                            case "SKIP":
//                                skippedTests++;
//                                testDetails+=testDetailsDraft;
//                                testDraft(testDetails,status,name, String.valueOf(duration));
//                                break;
//                        }
//                    }
//                }
//                // Calculate pass rate
//                int totalTests = passedTests + failedTests + skippedTests;
//                double passRate = (totalTests > 0) ? (passedTests / (double) totalTests) * 100 : 0;
//
//                // Format execution time
//                String executionTimeFormatted = String.format("%02d:%02d:%02d", (executionTimeMillis / (1000 * 60 * 60)) % 24, (executionTimeMillis / (1000 * 60)) % 60, (executionTimeMillis / 1000) % 60);
//
//                // FROM HTML file
//                String htmlTemplate = new String(Files.readAllBytes(Paths.get("mailTemplates/executiontemplate.html")), "UTF-8");
//
//                // Replace placeholders in HTML
//                htmlTemplate = htmlTemplate.replaceAll(Pattern.quote("#ExecutionMachine#"), executionMachine);
//                htmlTemplate = htmlTemplate.replaceAll(Pattern.quote("#SuiteName#"), suiteName);
//                htmlTemplate = htmlTemplate.replaceAll(Pattern.quote("#TestPassRate#"), String.format("%.2f%%", passRate));
//                htmlTemplate = htmlTemplate.replaceAll(Pattern.quote("#PassedTests#"), Integer.toString(passedTests));
//                htmlTemplate = htmlTemplate.replaceAll(Pattern.quote("#FailedTests#"), Integer.toString(failedTests));
//                htmlTemplate = htmlTemplate.replaceAll(Pattern.quote("#ExecutionStartTime#"), executionStartTime);
//                htmlTemplate = htmlTemplate.replaceAll(Pattern.quote("#TotalTests#"), String.valueOf(totalTests));
//                htmlTemplate = htmlTemplate.replaceAll(Pattern.quote("#ExecutionEndTime#"), executionEndTime);
//                htmlTemplate = htmlTemplate.replaceAll(Pattern.quote("#ExecutionTime#"), executionTimeFormatted);
//                htmlTemplate = htmlTemplate.replaceAll(Pattern.quote("#OS#"), os);
//
//                htmlTemplate = htmlTemplate.replaceAll(Pattern.quote("#row#"), executionTimeFormatted);
//
//                // TO file
//                Files.write(Paths.get("mailTemplates/to.html"), htmlTemplate.getBytes("UTF-8"));
//                System.out.println("Report generated successfully!");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }

public class XMLparser {

    public static String testDraft(String testCaseId, String scriptName, String description, String status, String duration) {
        // Generates an HTML row for each test case with its ID, script name, description, status, and duration
        return "<tr>\n" +
                "    <td align=\"left\" valign=\"middle\"\n" +
                "        style=\"border: 1px solid #b6b6b6; font: normal 13px 'Segoe UI', Arial, Helvetica, sans-serif;\">" + testCaseId + "</td>\n" +
                "    <td align=\"left\" valign=\"middle\"\n" +
                "        style=\"border: 1px solid #b6b6b6; font: normal 13px 'Segoe UI', Arial, Helvetica, sans-serif;\">" + scriptName + "</td>\n" +
                "    <td align=\"left\" valign=\"middle\"\n" +
                "        style=\"border: 1px solid #b6b6b6; font: normal 13px 'Segoe UI', Arial, Helvetica, sans-serif;\">" + description + "</td>\n" +
                "    <td align=\"left\" valign=\"middle\"\n" +
                "        style=\"border: 1px solid #b6b6b6; font: normal 13px 'Segoe UI', Arial, Helvetica, sans-serif;\">" + status + "</td>\n" +
                "    <td align=\"left\" valign=\"middle\"\n" +
                "        style=\"border: 1px solid #b6b6b6; font: normal 13px 'Segoe UI', Arial, Helvetica, sans-serif;\">" + duration + "</td>\n" +
                "</tr>\n";
    }

    public static void main(String[] args) {
        try {
            // Initialize XML parser
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File("target/surefire-reports/testng-results.xml"));
            document.getDocumentElement().normalize();

            NodeList suiteList = document.getElementsByTagName("suite");
            String suiteName = "";
            String executionMachine = InetAddress.getLocalHost().getHostName();
            String executionStartTime = "";
            String executionEndTime = "";
            long executionTimeMillis = 0;
            String os = System.getProperty("os.name"),companyName="WingsAutomtionTesting1",app="singleUserApp=C:\\Program Files (x86)\\Wings Infonet\\Wings Accounting 24DNP\\Wings.exe";

            if (suiteList.getLength() > 0) {
                Element suiteElement = (Element) suiteList.item(0);
                suiteName = suiteElement.getAttribute("name");
                executionStartTime = suiteElement.getAttribute("started-at");
                executionEndTime = suiteElement.getAttribute("finished-at");

                // Calculate total execution time in milliseconds
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                Date start = sdf.parse(executionStartTime);
                Date end = sdf.parse(executionEndTime);
                executionTimeMillis = end.getTime() - start.getTime();
            }

            // Retrieve test pass/fail information
            int passedTests = 0;
            int failedTests = 0;
            int skippedTests = 0;
            int totalTests = 0;
            double passRate = 0;

            StringBuilder testDetails = new StringBuilder();  // Use StringBuilder to accumulate test rows

            NodeList testList = document.getElementsByTagName("test");
            for (int i = 0; i < testList.getLength(); i++) {
                Element testElement = (Element) testList.item(i);
                NodeList testMethodTags = testElement.getElementsByTagName("test-method");
                int middleIndex = testMethodTags.getLength() / 2;
                if (testMethodTags.getLength() > 0) {
//                for (int k = 0; k < testMethodTags.getLength(); k++) {
                    Element testMethodElement = (Element) testMethodTags.item(middleIndex);
                    String status = testMethodElement.getAttribute("status");
                    String name = testMethodElement.getAttribute("name");
                    double duration = Double.parseDouble(testMethodElement.getAttribute("duration-ms")) / 1000;

                    // Update counters based on status
                    switch (status) {
                        case "PASS":
                            passedTests++;
                            break;
                        case "FAIL":
                            failedTests++;
                            break;
                        case "SKIP":
                            skippedTests++;
                            break;
                    }

                    // Generate and accumulate test rows
                    String description = "Description for " + name;  // Placeholder description
                    testDetails.append(testDraft(name, name, description, status, String.format("%.2f s", duration)));
                }
            }

            // Calculate total tests and pass rate
            totalTests = passedTests + failedTests + skippedTests;
            passRate = (totalTests > 0) ? (passedTests / (double) totalTests) * 100 : 0;

            // Format execution time
            String executionTimeFormatted = String.format("%02d:%02d:%02d",
                    (executionTimeMillis / (1000 * 60 * 60)) % 24,
                    (executionTimeMillis / (1000 * 60)) % 60,
                    (executionTimeMillis / 1000) % 60);

            // Load HTML template
            String htmlTemplate = new String(Files.readAllBytes(Paths.get("mailTemplates/executiontemplate.html")), "UTF-8");

            // Replace placeholders in HTML template
            htmlTemplate = htmlTemplate.replaceAll(Pattern.quote("#ExecutionMachine#"), executionMachine);
            htmlTemplate = htmlTemplate.replaceAll(Pattern.quote("#SuiteName#"), suiteName);
            htmlTemplate = htmlTemplate.replaceAll(Pattern.quote("#TestPassRate#"), String.format("%.2f%%", passRate));
            htmlTemplate = htmlTemplate.replaceAll(Pattern.quote("#PassedTests#"), Integer.toString(passedTests));
            htmlTemplate = htmlTemplate.replaceAll(Pattern.quote("#FailedTests#"), Integer.toString(failedTests));
            htmlTemplate = htmlTemplate.replaceAll(Pattern.quote("#ExecutionStartTime#"), executionStartTime);
            htmlTemplate = htmlTemplate.replaceAll(Pattern.quote("#ExecutionEndTime#"), executionEndTime);
            htmlTemplate = htmlTemplate.replaceAll(Pattern.quote("#ExecutionTime#"), executionTimeFormatted);
            htmlTemplate = htmlTemplate.replaceAll(Pattern.quote("#TotalTests#"), String.valueOf(totalTests));
            htmlTemplate = htmlTemplate.replaceAll(Pattern.quote("#OS#"), os);
            htmlTemplate = htmlTemplate.replaceAll(Pattern.quote("#URL#"), app);

            htmlTemplate = htmlTemplate.replaceAll(Pattern.quote("#CompanyDetails#"),companyName);

            htmlTemplate = htmlTemplate.replaceAll(Pattern.quote("#row#"), testDetails.toString());  // Insert all test rows

            // Write output to HTML file
            Files.write(Paths.get("mailTemplates/to.html"), htmlTemplate.getBytes("UTF-8"));
            System.out.println("Report generated successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

//        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//        try {
//            // Create a document builder
//            DocumentBuilder builder = factory.newDocumentBuilder();
//            // Parse the XML file
//            Document document = builder.parse(new File("target/surefire-reports/testng-results.xml"));
//            document.getDocumentElement().normalize(); // Normalize the document structure
//            NodeList testList = document.getElementsByTagName("test");
//            for (int i = 0; i < testList.getLength(); i++) {
//                Node test = testList.item(i);
//                if (test.getNodeType() == Node.ELEMENT_NODE) {
//                    Element testElement = (Element) test;
//                    System.out.println("Test Name :->" + testElement.getAttribute("name"));
//                    System.out.println("Start-Time :->" + testElement.getAttribute("started-at"));
//                    System.out.println("Finished-Time :->" + testElement.getAttribute("finished-at"));
//                    System.out.println("Duration :->" + testElement.getAttribute("duration-ms"));
//
//                    NodeList classTags = testElement.getElementsByTagName("class");
//                    for (int j = 0; j < classTags.getLength(); j++) {
//                        Node classNode = classTags.item(j);
//                        if (classNode.getNodeType() == Node.ELEMENT_NODE) {
//                            Element classElement = (Element) classNode;
//                            String className = classElement.getAttribute("name");
//                            System.out.println("Class Name :->" + className);
//                        }
//                    }
//
//                    NodeList testMethodTags = testElement.getElementsByTagName("test-method");
//                    for (int k = 0; k < testMethodTags.getLength(); k++) {
//                        Node testMethodNode = testMethodTags.item(k);
//                        if (testMethodNode.getNodeType() == Node.ELEMENT_NODE) {
//                            Element testMethodElement = (Element) testMethodNode;
//                            System.out.println("Test Method Name :->" + testMethodElement.getAttribute("name"));
////                            System.out.println("Signature :->" + testMethodElement.getAttribute("signature"));
//                            System.out.println("Start-Time :->" + testMethodElement.getAttribute("started-at"));
//                            System.out.println("Finished-Time :->" + testMethodElement.getAttribute("finished-at"));
//                            System.out.println("Duration :->" + testMethodElement.getAttribute("duration-ms"));
//                            System.out.println("Status :->" + testMethodElement.getAttribute("status"));
//                        }
//                    }
//                }
//            }
//
//        } catch (ParserConfigurationException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } catch (SAXException e) {
//            throw new RuntimeException(e);
//        }
//
//    }

