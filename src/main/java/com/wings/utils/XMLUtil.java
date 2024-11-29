package com.wings.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.appium.java_client.windows.WindowsDriver;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

public class XMLUtil {
    Document document;
    long executionTimeMillis = 0;
    String executionMachine = "";
    String executionStartTime = "";
    String suiteName1="";
    String executionEndTime = "";
    String os = System.getProperty("os.name");
    static final Path toFile = Paths.get("mailTemplates/to.html");
    int passedTests = 0;
    int failedTests = 0;
    int skippedTests = 0;
    int totalTests = 0;
    double passRate = 0;
    String name="";
    String description="";
    String testID="";
    String dataFile="src/main/resources/XMLdata.json";
    final List<Map<String, String>> testDetailsList = new ArrayList<>();
    WindowsDriver driver;
    Common common=new Common(driver);

    StringBuilder testDetails = new StringBuilder();

    public void readXMLFile(String pathOfFile) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        document = builder.parse(new File(pathOfFile));
        document.getDocumentElement().normalize();
    }

    public String testRowAppend(String testID,String name,String status, String description,double duration){
        return "<tr style='text-align: center; vertical-align: middle;'> <td align=\"center\" valign=\"middle\" style=\"border:1px solid #b6b6b6; font:normal 13px 'Segoe UI', Arial, Helvetica, sans-serif; \"> <a href=\"https://wingsinfo.atlassian.net/browse/"+testID+"\">"+testID+"</a></td>" +
                "<td align=\"left\" valign=\"middle\" style=\"border:1px solid #b6b6b6; font:normal 13px 'Segoe UI', Arial, Helvetica, sans-serif; \">"+name+"</td>" +
                "<td  align=\"left\" valign=\"middle\" style=\"border:1px solid #b6b6b6; font:normal 13px 'Segoe UI', Arial, Helvetica, sans-serif; \">"+status+"</td>" +
                "<td align=\"center\" valign=\"middle\" style=\"border:1px solid #b6b6b6; font:normal 13px 'Segoe UI', Arial, Helvetica, sans-serif; \">"+description+"</td>" +
                "<td align=\"left\" valign=\"middle\" style=\"border:1px solid #b6b6b6; font:normal 13px 'Segoe UI', Arial, Helvetica, sans-serif; \">"+duration+"</td></tr>";

    }

    public void readTestNG(String TestNGFile) throws ParserConfigurationException, IOException, SAXException {
        readXMLFile(TestNGFile);
        NodeList childList=document.getElementsByTagName("test");
        for (int i = 0; i < childList.getLength(); i++) {
            Element testElement= (Element) childList.item(i);
            name=testElement.getAttribute("name");
            description=testElement.getAttribute("description");
            testID=testElement.getAttribute("testID");

            Map<String, String> testData = new HashMap<>();
            testData.put("name", name);
            testData.put("description", description);
            testData.put("testID", testID);
            testDetailsList.add(testData);
        }

    }

    public void readTestNGResults(String testNGResultsFIle) throws ParseException, IOException, ParserConfigurationException, SAXException, org.json.simple.parser.ParseException {
        // Parse testng-results.xml for results (this will now happen after parsing sampleSuite.xml)
        readXMLFile(testNGResultsFIle);
        document.getDocumentElement().normalize();
        NodeList suiteList = document.getElementsByTagName("suite");
        executionMachine = InetAddress.getLocalHost().getHostName();

        if (suiteList.getLength() > 0) {
            Element suiteElement = (Element) suiteList.item(0);
            suiteName1 = suiteElement.getAttribute("name");
            executionStartTime = suiteElement.getAttribute("started-at");
            executionEndTime = suiteElement.getAttribute("finished-at");

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date start = sdf.parse(executionStartTime);
            Date end = sdf.parse(executionEndTime);
            executionTimeMillis = end.getTime() - start.getTime();
        }

        NodeList testList = document.getElementsByTagName("test");
        for (int i = 0; i < testList.getLength(); i++) {
            Element testElement = (Element) testList.item(i);
            String name = testElement.getAttribute("name");
            double duration = Double.parseDouble(testElement.getAttribute("duration-ms")) / 1000;

            NodeList testMethodTags = testElement.getElementsByTagName("test-method");
            int middleIndex = testMethodTags.getLength() / 2;
            if (testMethodTags.getLength() > 0) {
                Element testMethodElement = (Element) testMethodTags.item(middleIndex);
                String status = testMethodElement.getAttribute("status");
                Map<String, String> testDetailsMap = testDetailsList.get(i);
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
                if (common.getProperty("failedtcreport").equals("true") && status.equals("FAIL")){
                    testDetails.append(testRowAppend(testDetailsMap.get("testID"), testDetailsMap.get("name"), testDetailsMap.get("description"), status,duration));
                }
                else if(common.getProperty("failedtcreport").equals("false")){
                    testDetails.append(testRowAppend(testDetailsMap.get("testID"), testDetailsMap.get("name"), testDetailsMap.get("description"), status, duration));
                }
            }
        }

        totalTests = passedTests + failedTests + skippedTests;
        passRate = (totalTests > 0) ? (passedTests / (double) totalTests) * 100 : 0;
                // Read data from JSON file
//        String jsonContent = new String(Files.readAllBytes(Paths.get(JSONFile)));
//        ObjectMapper objectMapper = new ObjectMapper();
//        Map<String, Object> jsonMap = objectMapper.readValue(jsonContent, Map.class);

        // Format execution time
        String executionTimeFormatted = String.format("%02d:%02d:%02d",
                (executionTimeMillis / (1000 * 60 * 60)) % 24,
                (executionTimeMillis / (1000 * 60)) % 60,
                (executionTimeMillis / 1000) % 60);

        // Read and update HTML template
        String htmlTemplate = new String(Files.readAllBytes(Paths.get("mailTemplates/executiontemplate.html")), "UTF-8");
        htmlTemplate = htmlTemplate.replaceAll(Pattern.quote("#ExecutionMachine#"), executionMachine);
        htmlTemplate = htmlTemplate.replaceAll(Pattern.quote("#SuiteName#"), suiteName1);
        htmlTemplate = htmlTemplate.replaceAll(Pattern.quote("#TestPassRate#"), String.format("%.2f%%", passRate));
        htmlTemplate = htmlTemplate.replaceAll(Pattern.quote("#PassedTests#"), Integer.toString(passedTests));
        htmlTemplate = htmlTemplate.replaceAll(Pattern.quote("#FailedTests#"), Integer.toString(failedTests));
        htmlTemplate = htmlTemplate.replaceAll(Pattern.quote("#SkippedTests#"), Integer.toString(skippedTests));
        htmlTemplate = htmlTemplate.replaceAll(Pattern.quote("#ExecutionStartTime#"), executionStartTime);
        htmlTemplate = htmlTemplate.replaceAll(Pattern.quote("#ExecutionEndTime#"), executionEndTime);
        htmlTemplate = htmlTemplate.replaceAll(Pattern.quote("#ExecutionTime#"), executionTimeFormatted);
        htmlTemplate = htmlTemplate.replaceAll(Pattern.quote("#TotalTests#"), String.valueOf(totalTests));
        htmlTemplate = htmlTemplate.replaceAll(Pattern.quote("#OS#"), os);
        htmlTemplate = htmlTemplate.replaceAll(Pattern.quote("#URL#"), common.getData(dataFile,"app"));
        htmlTemplate = htmlTemplate.replaceAll(Pattern.quote("#CompanyDetails#"), common.getData(dataFile,"companyName"));
        htmlTemplate = htmlTemplate.replaceAll(Pattern.quote("#row#"), testDetails.toString());
        Files.write(toFile, htmlTemplate.getBytes("UTF-8"));
        // Send email with report attached
        final String fromEmail = "productupdates@wingsinfo.net";
        final String password = "Zuy97283";
        final String toEmail = "vikas.empuluri@wingsinfo.net,manoj.c@wingsinfo.net";//manoj.c@wingsinfo.net,venkatarathaiah.m@wingsinfo.net";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.office365.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };
        Session session = Session.getInstance(props, auth);
        EmailUtil.sendEmail(session, toEmail, String.valueOf(toFile));
        System.out.println("Report generated and email sent successfully!");
    }

    public static void main(String[] args) {
        XMLUtil xmlUtil = new XMLUtil();
        try {
            xmlUtil.readTestNG("TestNG/MenuItems/TaxesSuite.xml");// Parse TestNg file
            xmlUtil.readTestNGResults("./target/surefire-reports/testng-results.xml"); //parse testng-results.xml file and Process test results and send email
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
