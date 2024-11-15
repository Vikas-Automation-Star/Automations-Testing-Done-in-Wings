package com.wings.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.net.InetAddress;
import java.nio.file.*;
import java.util.Map;
import java.util.Properties;
import java.util.regex.*;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


public class XMLparser {

    static Path toFile= Paths.get("mailTemplates/to.html");

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
            String os = System.getProperty("os.name");

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
                String name = testElement.getAttribute("name");
                double duration = Double.parseDouble(testElement.getAttribute("duration-ms")) / 1000;

                NodeList testMethodTags = testElement.getElementsByTagName("test-method");
                int middleIndex = testMethodTags.getLength() / 2;
                if (testMethodTags.getLength() > 0) {
//                for (int k = 0; k < testMethodTags.getLength(); k++) { //instead of fetching all the childnodes, we're just fetching @Test here.
                    Element testMethodElement = (Element) testMethodTags.item(middleIndex);
                    String status = testMethodElement.getAttribute("status");

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
                    String testID = "";
                    testDetails.append(testDraft(name, name, description, status, String.format("%.2f s", duration)));
                }
            }

            // Calculate total tests and pass rate
            totalTests = passedTests + failedTests + skippedTests;
            passRate = (totalTests > 0) ? (passedTests / (double) totalTests) * 100 : 0;

            String jsonContent = new String(Files.readAllBytes(Paths.get("./src/main/resources/XMLdata.json")));
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> jsonMap = objectMapper.readValue(jsonContent, Map.class);

            // Format execution time
            String executionTimeFormatted = String.format("%02d:%02d:%02d", (executionTimeMillis / (1000 * 60 * 60)) % 24, (executionTimeMillis / (1000 * 60)) % 60, (executionTimeMillis / 1000) % 60);

            // Load HTML template(like base template)
            String htmlTemplate = new String(Files.readAllBytes(Paths.get("mailTemplates/executiontemplate.html")), "UTF-8");

            // Replace placeholders in HTML template
            htmlTemplate = htmlTemplate.replaceAll(Pattern.quote("#ExecutionMachine#"), executionMachine);
            htmlTemplate = htmlTemplate.replaceAll(Pattern.quote("#SuiteName#"), suiteName);
            htmlTemplate = htmlTemplate.replaceAll(Pattern.quote("#TestPassRate#"), String.format("%.2f%%", passRate));
            htmlTemplate = htmlTemplate.replaceAll(Pattern.quote("#PassedTests#"), Integer.toString(passedTests));
            htmlTemplate = htmlTemplate.replaceAll(Pattern.quote("#FailedTests#"), Integer.toString(failedTests));
            htmlTemplate = htmlTemplate.replaceAll(Pattern.quote("#SkippedTests#"), Integer.toString(skippedTests));
            htmlTemplate = htmlTemplate.replaceAll(Pattern.quote("#ExecutionStartTime#"), executionStartTime);
            htmlTemplate = htmlTemplate.replaceAll(Pattern.quote("#ExecutionEndTime#"), executionEndTime);
            htmlTemplate = htmlTemplate.replaceAll(Pattern.quote("#ExecutionTime#"), executionTimeFormatted);
            htmlTemplate = htmlTemplate.replaceAll(Pattern.quote("#TotalTests#"), String.valueOf(totalTests));
            htmlTemplate = htmlTemplate.replaceAll(Pattern.quote("#OS#"), os);
            htmlTemplate = htmlTemplate.replaceAll(Pattern.quote("#URL#"), jsonMap.get("app").toString());
            htmlTemplate = htmlTemplate.replaceAll(Pattern.quote("#CompanyDetails#"),jsonMap.get("companyName").toString());
            htmlTemplate = htmlTemplate.replaceAll(Pattern.quote("#row#"), testDetails.toString());

            // Write output to HTML file
            Files.write(toFile, htmlTemplate.getBytes("UTF-8"));

            final String fromEmail = "QA@wingsinfo.net"; //requires valid gmail id
            final String password = "TestAutomation@19"; // correct password for gmail id
            final String toEmail = "madhuri.matta@wingsinfo.net";//,manoj.c@wingsinfo.net,venkatarathaiah.m@wingsinfo.net ";//, vikas.empuluri@wingsinfo.net, manoj.c@wingsinfo.net"; // can be any email id

            System.out.println("TLSEmail Start");
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.office365.com"); //SMTP Host
            props.put("mail.smtp.port", "587"); //TLS Port
            props.put("mail.smtp.auth", "true"); //enable authentication
            props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

            //create Authenticator object to pass in Session.getInstance argument
            Authenticator auth = new Authenticator() {
                //override the getPasswordAuthentication method
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, password);
                }
            };
            Session session = Session.getInstance(props, auth);
            EmailUtil.sendEmail(session, toEmail, String.valueOf(toFile));

            System.out.println("Report generated successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



