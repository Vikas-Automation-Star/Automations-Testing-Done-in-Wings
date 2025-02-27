package phase_1_TestCases;

import com.wings.pages.AppLogin;
import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class UserRightsSetup extends Transaction {
    WindowsDriver driver, passwordDriver;
    Common common;
    String dataFile;
    AppLogin appLogin;

    public UserRightsSetup(WindowsDriver driver, String file, AppLogin appLogin) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
        this.appLogin = appLogin;
    }

    public void userCreationAssignPassword() throws InterruptedException, IOException, ParseException {
        /* CREATING A MASTER IS NOT NEEDED ALL THE TIME.
        common.clickElement("xpath","//MenuItem[@Name='Configure']");
        common.clickElement("xpath","//MenuItem[@Name='User Rights']");
        common.clickElement("xpath","//MenuItem[@Name='Users']");
        WebElement allUsers = common.findWebElement("xpath", "//TreeItem[@Name='Simple Users']/TreeItem[@Name='All Simple Users']");
        Actions actions = new Actions(driver);
        actions.contextClick(allUsers).perform();
        common.clickElement("name", "New Master");
        Thread.sleep(3500);
        common.inputText("xpath", "//Edit[@Name='New Master *']", common.getData(dataFile, "newUser"));
        //save
        common.clickElement("xpath", "//Pane/Button[@Name='Save']");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Window/Button[@Name='OK']");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane/Button[@Name='Cancel']");
        //close
        common.clickElement("xpath", "//TabItem[@Name='Users']/Button[@Name='Close']");

         */

        common.clickElement("xpath", "//MenuItem[@Name='Configure']");
        common.clickElement("xpath", "//MenuItem[@Name='User Rights']");
        common.clickElement("xpath", "//MenuItem[@Name='Users']");
        common.clickElement("xpath", "//TreeItem[@Name='All Simple Users']");
        //assign password
        WebElement user1 = common.findWebElement("xpath", "//Text[@Name='Sample User_1']");
        Actions actions = new Actions(driver);
        actions.contextClick(user1).perform();
        common.clickElement("xpath", "//MenuItem[@Name='Change Password']");
        //enter password
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("app", "Root");
        passwordDriver = new WindowsDriver<>(new URL("http://127.0.0.1:4723/"), capabilities);
        Thread.sleep(5000);
        List<WebElement> panes = By.tagName("Window").findElements(passwordDriver);
        Thread.sleep(2500);
        if (panes.isEmpty()) {
            System.out.println("No windows found.");
        } else {
            for (WebElement i : panes) {
                String nativeWindow = i.getAttribute("NativeWindowHandle");
                String hexloginid = Integer.toHexString(Integer.parseInt(nativeWindow));
                System.out.println("window id: " + hexloginid);
                String name = i.getAttribute("Name");
                System.out.println("Name:- " + name);
                if (name.equals("Change Password")) {
                    Thread.sleep(2500);
                    WebElement newPassword = passwordDriver.findElementByXPath("//Edit[@Name='New Password']");
                    newPassword.sendKeys("Wings@123");
                    WebElement confirmPassword = passwordDriver.findElementByXPath("//Edit[@Name='Confirm Password']");
                    confirmPassword.sendKeys("Wings@123");
                    passwordDriver.findElementByXPath("//Button[@Name='Ok']").click();
                    Thread.sleep(700);
                    passwordDriver.findElementByXPath("//Button[@Name='OK']").click();
                    passwordDriver.quit();
                    break;
                }
            }
        }
        appLogin.logout();
        //login again
        driver = appLogin.launchSingleUserApp();
        common = new Common(driver);
        appLogin.singleUserLogin(common.getData(dataFile, "user"), common.getData(dataFile, "password"));
        System.out.println("Password Assigning and Login is validated successfully");
    }

    public void grantAccessForMaster() throws InterruptedException, IOException, ParseException {
        common.clickElement("xpath", "//MenuItem[@Name='Configure']");
        common.clickElement("xpath", "//MenuItem[@Name='User Rights']");
        common.clickElement("xpath", "//MenuItem[@Name='Users']");
        common.clickElement("xpath", "//TreeItem[@Name='All Simple Users']");

        WebElement user1 = common.findWebElement("xpath", "//Text[@Name='Sample User_1']");
        Actions actions = new Actions(driver);
        actions.contextClick(user1).perform();
        common.clickElement("xpath", "//MenuItem[@Name='Properties']");
        Thread.sleep(2000);
        common.clickElement("xpath", "//Text[@Name='Master Groups']/following-sibling::Button[@Name='...']");
        common.clickElement("xpath", "//Button[@Name='Add Master Groups']");
        //
        List<WebElement> screens = common.findWebElements("xpath", "//Window[@Name='Add Master Groups']/Pane/Table/*[contains(@Name,'Row')]/*[starts-with(@Name,'Master Type Row ')]");
        System.out.println("Total no.of masters: "+screens.size());
        for (WebElement screenNo :screens) {
            String screenName = screenNo.getText();
            System.out.println("Master Name:" + screenName);
            if (screenName.equals(common.getData(dataFile, "masterName"))) {
                screenNo.click();
                screenNo.sendKeys(Keys.LEFT, Keys.SPACE);
                break;
            } else {
                screenNo.sendKeys(Keys.DOWN);
            }
        }
        common.clickElement("xpath", "//Button[@Name='Ok']");
        List<WebElement> elementList = common.findWebElements("xpath", "//Table/*[contains(@Name,'Row')]/*[contains(@Name,'Master Type *')]");
        System.out.println("Size :" + elementList.size());
        String value0Updtaed = "";
        for (WebElement i : elementList) {
            System.out.println(i.getText());
            if (i.getText().contains(common.getData(dataFile,"masterName"))) {
                value0Updtaed = i.getText();
                i.click();
                i.sendKeys(Keys.RIGHT, Keys.SPACE, Keys.RIGHT, Keys.SPACE); //can view master, can create master`
                break;
            }
        }
        System.out.println("Updated value: " + value0Updtaed);
        common.clickElement("xpath", "//Button[@Name='Ok']");
        //delete invalid rows in Master Properties
        common.clickElement("xpath", "//Text[@Name='Master Properties']/following-sibling::Button[@Name='...']");
        Thread.sleep(2500);
        common.deleteInvalidRows();
        common.clickElement("xpath", "//Button[@Name='Ok']");
        //save
        common.clickElement("xpath", "//Button[@Name='Save']");
        common.clickElement("xpath", "//Button[@Name='Yes']");
        common.clickElement("xpath", "//Button[@Name='OK']");

        //verify in reports
        common.clickElement("xpath", "//MenuItem[@Name='Configure']");
        common.clickElement("xpath", "//MenuItem[@Name='User Rights']");
        common.clickElement("xpath", "//MenuItem[@Name='User - Masters']");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Button[@Name='Submit']");
        Thread.sleep(1000);
        // Locate the row containing 'Sample User_1'
        List<WebElement> userRows = common.findWebElements("xpath", "//Table/*[@Name='Data Panel']/ListItem[contains(@Name,'Row')]");
        System.out.println("user size: " + userRows.size());
        for (int i = 0; i < userRows.size(); i++) {
            WebElement userRow = userRows.get(i);
            System.out.println("Row " + (i + 1) + ": " + userRow.getText());

            try {
                WebElement userElement = userRow.findElement(By.xpath(".//*[contains(@Name, 'User')]"));
                WebElement reportElement = userRow.findElement(By.xpath(".//*[contains(@Name, 'Master Types')]"));

                String userName = userElement.getText();
                String reportName = reportElement.getText();

                if (userName.equals(common.getData(dataFile, "user")) && reportName.equals(value0Updtaed)) {
                    System.out.println("User and Report are a match, verifying can view status");
                    //if user and reportName are a match, verify the can view status
                    WebElement canViewColumn = userRow.findElement(By.xpath(".//*[contains(@Name,'Can View')]"));
                    WebElement canCreateColumn = userRow.findElement(By.xpath(".//*[contains(@Name,'Can Create')]"));

                    String canViewStatus = canViewColumn.getText();
                    System.out.println("Can View Status: " + canViewStatus);

                    String canCreateStatus = canCreateColumn.getText();
                    System.out.println("Can Create Status: " + canCreateStatus);

                    if (canViewStatus.equalsIgnoreCase(common.getData(dataFile, "canView")) && canCreateStatus.equalsIgnoreCase(common.getData(dataFile, "canCreate"))) {
                        System.out.println("Can View/Create Status is updated. Report is Verified in Super User Level");
                        break;
                    } else {
                        Assert.fail("Pls check the Can View/Create Status. Report Verification Failed");
                    }
                }
            } catch (Exception e) {
                System.out.println("Element not found for row " + (i + 1));
            }
        }
    }
    public void checkGrantForMaster() throws InterruptedException, IOException, ParseException {
        common.clickElement("xpath", "//MenuItem[@Name='Finance']");
        common.clickElement("xpath", "//MenuItem[@Name='Account Groups']");
        Thread.sleep(1700);
        System.out.println("Account Balances is in VIEW status");
        WebElement allUsers = common.findWebElement("xpath", "//TreeItem[@Name='Account Groups']/TreeItem[@Name='All Account Groups']");
        Actions actions = new Actions(driver);
        actions.contextClick(allUsers).perform();
        common.clickElement("name", "New Master");
        Thread.sleep(1500);
        common.inputText("xpath", "//Edit[@Name='New Account Group *']", common.getData(dataFile, "newAccountGroup"));
        //save
        common.clickElement("xpath", "//Pane/Button[@Name='Save']");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Window/Button[@Name='OK']");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane/Button[@Name='Cancel']");
        //close
        common.clickElement("xpath", "//TabItem[@Name='Account Groups']/Button[@Name='Close']");
        //refresh
        refresh();
        //open again and check
        common.clickElement("xpath", "//MenuItem[@Name='Finance']");
        common.clickElement("xpath", "//MenuItem[@Name='Account Groups']");
        common.clickElement("xpath", "//TreeItem[@Name='Account Groups']/TreeItem[@Name='All Account Groups']");
        //iterate through and verify the created master
        List<WebElement> masterItems = common.findWebElements("xpath", "//Pane[@Name='Account Groups']/Pane/Pane/Pane/Pane/Pane/List/ListItem");
        System.out.println("list:" + masterItems.size());
        for (int i = 0; i < masterItems.size(); i++) {
            WebElement element = masterItems.get(i);
            System.out.println("master: " + element.getText());
            if (element.getText().equalsIgnoreCase(common.getData(dataFile, "newAccountGroup"))) {
                element.click();
                System.out.println("Master can be created in User Level.");
                break;
            }
        }
        //close
        common.clickElement("xpath", "//TabItem[@Name='Account Groups']/Button[@Name='Close']");
    }
    public void revokeAccessForMaster() throws InterruptedException, IOException, ParseException {
        common.clickElement("xpath", "//MenuItem[@Name='Configure']");
        common.clickElement("xpath", "//MenuItem[@Name='User Rights']");
        common.clickElement("xpath", "//MenuItem[@Name='Users']");
        common.clickElement("xpath", "//TreeItem[@Name='All Simple Users']");
        WebElement user1 = common.findWebElement("xpath", "//Text[@Name='Sample User_1']");
        Actions actions = new Actions(driver);
        actions.contextClick(user1).perform();
        common.clickElement("xpath", "//MenuItem[@Name='Properties']");
        Thread.sleep(2000);
        common.clickElement("xpath", "//Text[@Name='Master Groups']/following-sibling::Button[@Name='...']");
        //REVOKE ACCESS
        List<WebElement> elementList = common.findWebElements("xpath", "//Table/*[contains(@Name,'Row')]/*[contains(@Name,'Master Type *')]");
        System.out.println("Size :" + elementList.size());
        String value0Updtaed = "";
        for (WebElement i : elementList) {
            System.out.println(i.getText());
            if (i.getText().contains(common.getData(dataFile, "masterName"))) {
                value0Updtaed = i.getText();
                i.click();
                i.sendKeys(Keys.LEFT, Keys.SPACE);
                common.clickElement("xpath", "//Button[@Name='Delete Selected']");
                common.clickElement("xpath", "//Button[@Name='Yes']");
                break;
            }
        }
        System.out.println("Updated value: " + value0Updtaed);
        common.clickElement("xpath", "//Button[@Name='Ok']");
        //save
        common.clickElement("xpath", "//Button[@Name='Save']");
        common.clickElement("xpath", "//Button[@Name='Yes']");
        common.clickElement("xpath", "//Button[@Name='OK']");

        //verify in reports
        common.clickElement("xpath", "//MenuItem[@Name='Configure']");
        common.clickElement("xpath", "//MenuItem[@Name='User Rights']");
        common.clickElement("xpath", "//MenuItem[@Name='User - Masters']");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Button[@Name='Submit']");
        Thread.sleep(1000);
        // Locate the row containing 'Sample User_1'
        List<WebElement> userRows = common.findWebElements("xpath", "//Table/*[@Name='Data Panel']/ListItem[contains(@Name,'Row')]");
        System.out.println("user size: " + userRows.size());
        boolean allRightsForMasterRevokedSuccessfully = true;
        for (int i = 0; i < userRows.size(); i++) {
            WebElement userRow = userRows.get(i);
            System.out.println("Row " + (i + 1) + ": " + userRow.getText());

            try {
                WebElement userElement = userRow.findElement(By.xpath(".//*[contains(@Name, 'User')]"));
                WebElement reportElement = userRow.findElement(By.xpath(".//*[contains(@Name, 'Master Types')]"));

                String userName = userElement.getText();
                String reportName = reportElement.getText();

                if (userName.equals(common.getData(dataFile, "user")) && reportName.equals(value0Updtaed)) {
                    allRightsForMasterRevokedSuccessfully = false;
                    System.out.println("User Right is not revoked properly. Report still Exists.");
                    break;
                }
            } catch (Exception e) {
                System.out.println("User/Master is not found. " + (i + 1));
            }
        }
        if (allRightsForMasterRevokedSuccessfully)
            System.out.println("User Rights revoked successfully for Master. Master in Reports verified successfully");
        else System.out.println("Pls check revoking rights for masters properly.");
    }
    public void checkRevokeAccessForMaster() {
        try {
            boolean menuItemFound = true, masterFound = true;
            List<WebElement> menuItem = common.findWebElements("xpath", "//MenuBar/MenuItem");
            System.out.println("items: " + menuItem.size());
            for (WebElement items : menuItem) {
                System.out.println(items.getText());
                //sometimes Finance will be there. so check it and click if it is present. then check inside masters
                if (items.getText().equalsIgnoreCase("Finance")) {
                    menuItemFound = false;
                    items.click();
                    WebDriverWait wait=new WebDriverWait(driver,10);
                    WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//MenuItem[@Name='Account Groups']")));
                    System.out.println("groups:" + element.getText());
                    if (element.isDisplayed()) {  //if the master is present, it will go inside loop and boolen will become false whic will fail the script.
                        masterFound = false;
                        System.out.println("Rights are not revoked properly. Master is still present");
                        break;
                    }
                }
            }
            if (menuItemFound) System.out.println("No Menu Item is found.");
            if (masterFound) System.out.println("Rights are Revoked Successfully");
            else Assert.fail("There are some issues regarding revoke access. pls solve them");
        } catch (Exception e) {
            System.out.println("Account Groups is not found at user level. Rights revokes successfully at User Level");
        }
    }

    public void grantUserRightsForReports() throws InterruptedException, IOException, ParseException {
        common.clickElement("xpath", "//MenuItem[@Name='Configure']");
        common.clickElement("xpath", "//MenuItem[@Name='User Rights']");
        common.clickElement("xpath", "//MenuItem[@Name='Users']");
        common.clickElement("xpath", "//TreeItem[@Name='All Simple Users']");

        WebElement user1 = common.findWebElement("xpath", "//Text[@Name='Sample User_1']");
        Actions actions = new Actions(driver);
        actions.contextClick(user1).perform();
        common.clickElement("xpath", "//MenuItem[@Name='Properties']");
        Thread.sleep(3000);
        common.clickElement("xpath", "//Text[@Name='Reports']/following-sibling::Button[@Name='...']");
        common.clickElement("xpath", "//Button[@Name='Add Reports']");
        //select any 1
        List<WebElement> screens = common.findWebElements("xpath", "//Window[@Name='Add Reports']/Pane/Table/*[contains(@Name,'Row')]/*[starts-with(@Name,'Name Row ')]");
        System.out.println("Total no.of reports: "+screens.size());
        for (WebElement screenNo :screens) {
            String screenName = screenNo.getText();
            System.out.println("Master Name:" + screenName);
            if (screenName.equals(common.getData(dataFile, "reportName"))) {
                screenNo.click();
                screenNo.sendKeys(Keys.LEFT,Keys.LEFT, Keys.SPACE);
                break;
            } else {
                screenNo.sendKeys(Keys.DOWN);
            }
        }
        common.clickElement("xpath", "//Button[@Name='Ok']");
        //GRANT ACCESS
        List<WebElement> elementList = common.findWebElements("xpath", "//Table/*[contains(@Name,'Row')]/*[contains(@Name,'Report *')]");
        System.out.println("Size :" + elementList.size());
        for (WebElement i : elementList) {
            System.out.println(i.getText());
            if (i.getText().equalsIgnoreCase(common.getData(dataFile,"reportNameUpdated"))) {
                i.click();
                i.sendKeys(Keys.RIGHT, Keys.SPACE);
                break;
            }
        }

        common.clickElement("xpath", "//Button[@Name='Ok']");
        common.clickElement("xpath", "//Button[@Name='Save']");
        common.clickElement("xpath", "//Button[@Name='Yes']");
        common.clickElement("xpath", "//Button[@Name='OK']");
        //verify in reports
        common.clickElement("xpath", "//MenuItem[@Name='Configure']");
        common.clickElement("xpath", "//MenuItem[@Name='User Rights']");
        common.clickElement("xpath", "//MenuItem[@Name='User - Reports']");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Button[@Name='Submit']");
        Thread.sleep(1000);

        // Locate the row containing 'Sample User_1'
        List<WebElement> userRows = common.findWebElements("xpath", "//Table/*[@Name='Data Panel']/ListItem[contains(@Name,'Row')]");
        System.out.println("user size: " + userRows.size());
        for (int i = 0; i < userRows.size(); i++) {
            WebElement userRow = userRows.get(i);
            System.out.println("Row " + (i + 1) + ": " + userRow.getText());

            try {
                WebElement userElement = userRow.findElement(By.xpath(".//*[contains(@Name, 'User')]"));
                WebElement reportElement = userRow.findElement(By.xpath(".//*[contains(@Name, 'Reports')]"));

                String userName = userElement.getText();
                String reportName = reportElement.getText();

                if (userName.equals(common.getData(dataFile, "user")) && reportName.equals(common.getData(dataFile,"reportNameUpdated"))) {
                    System.out.println("User and Report are a match, verifying can view status");
                    //if user and reportName are a match, verify the can view status
                    WebElement canViewColumn = userRow.findElement(By.xpath(".//*[contains(@Name,'Can View')]"));
                    String canViewStatus = canViewColumn.getText();
                    System.out.println("Can View Status: " + canViewStatus);
                    if (canViewStatus.equalsIgnoreCase(common.getData(dataFile, "canView"))) {
                        System.out.println("Can View Status is updated. Report is Verified in Super User Level");
                        break;
                    } else {
                        Assert.fail("Pls check the Can View Status. Report Verification Failed");
                    }
                }
            } catch (Exception e) {
                System.out.println("Element not found for row " + (i + 1));
            }
        }
    }
    public void checkGrantAccessForReports() throws InterruptedException {
        common.clickElement("xpath", "//MenuItem[@Name='Finance']");
        common.clickElement("xpath", "//MenuItem[@Name='Balances']");
        common.clickElement("xpath", "//MenuItem[@Name='Account Balances']");
        Thread.sleep(1700);
        common.clickElement("xpath", "//Button[@Name='Submit']");
        Thread.sleep(1200);
        System.out.println("Report is in Can View status in User Level");
    }
    public void revokeUserRightsForReports() throws InterruptedException, IOException, ParseException {
        common.clickElement("xpath", "//MenuItem[@Name='Configure']");
        common.clickElement("xpath", "//MenuItem[@Name='User Rights']");
        common.clickElement("xpath", "//MenuItem[@Name='Users']");
        common.clickElement("xpath", "//TreeItem[@Name='All Simple Users']");
        WebElement user1 = common.findWebElement("xpath", "//Text[@Name='Sample User_1']");
        Actions actions = new Actions(driver);
        actions.contextClick(user1).perform();
        common.clickElement("xpath", "//MenuItem[@Name='Properties']");
        Thread.sleep(3000);
        common.clickElement("xpath", "//Text[@Name='Reports']/following-sibling::Button[@Name='...']");
        //REVOKE ACCESS
        List<WebElement> elementList = common.findWebElements("xpath", "//Table/*[contains(@Name,'Row')]/*[contains(@Name,'Report *')]");
        System.out.println("Size :" + elementList.size());
        for (WebElement i : elementList) {
            System.out.println(i.getText());
            if (i.getText().equals(common.getData(dataFile, "reportNameUpdated"))) {
                i.click();
                i.sendKeys(Keys.LEFT, Keys.SPACE);
                common.clickElement("xpath", "//Button[@Name='Delete Selected']");
                common.clickElement("xpath", "//Button[@Name='Yes']");
                break;
            }
        }
//        System.out.println("Updated value: " + value0Updtaed);
        common.clickElement("xpath", "//Button[@Name='Ok']");
        common.clickElement("xpath", "//Button[@Name='Save']");
        common.clickElement("xpath", "//Button[@Name='Yes']");
        common.clickElement("xpath", "//Button[@Name='OK']");
        //verify in reports
        common.clickElement("xpath", "//MenuItem[@Name='Configure']");
        common.clickElement("xpath", "//MenuItem[@Name='User Rights']");
        common.clickElement("xpath", "//MenuItem[@Name='User - Reports']");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Button[@Name='Submit']");
        Thread.sleep(1000);
        // Locate the row containing 'Sample User_1'
        List<WebElement> userRows = common.findWebElements("xpath", "//Table/*[@Name='Data Panel']/ListItem[contains(@Name,'Row')]");
        System.out.println("user size: " + userRows.size());
        for (int i = 0; i < userRows.size(); i++) {
            WebElement userRow = userRows.get(i);
            System.out.println("Row " + (i + 1) + ": " + userRow.getText());

            try {
                WebElement userElement = userRow.findElement(By.xpath(".//*[contains(@Name, 'User')]"));
                WebElement reportElement = userRow.findElement(By.xpath(".//*[contains(@Name, 'Reports')]"));
                String userName = userElement.getText();
                String reportName = reportElement.getText();

                if (userName.equals(common.getData(dataFile, "user")) && reportName.equals(common.getData(dataFile,"reportNameUpdated"))) {
                    Assert.fail("User Right for reports isn't revoked properly.Report Verification Failed");
                } else {
                    System.out.println("User Rights successfully. Report is not found.");
                }
            } catch (Exception e) {
                System.out.println("User/Report is not found for row " + (i + 1));
            }
        }
    }
    public void checkRevokeAccessForReports(){
        try {
            boolean menuItemFound = true, reportFound = true, reportItem = true;
            List<WebElement> menuItem = common.findWebElements("xpath", "//MenuBar/MenuItem");
            System.out.println("items: " + menuItem.size());
            for (WebElement items : menuItem) {
                System.out.println(items.getText());
                //sometimes Finance will be there. so check it and click if it is present. then check inside masters
                if (items.getText().equalsIgnoreCase("Finance")) {
                    menuItemFound = false;
                    items.click();
                    WebElement element = common.findWebElement("xpath", "//MenuItem[@Name='Balances']");
                    System.out.println("reportItem:" + element.getText());
                    if (element.isDisplayed()) {
                        element.click();//if the master is present, it will go inside loop and boolen will become false whic will fail the script.
                        reportItem = false;
                        WebDriverWait wait=new WebDriverWait(driver,10);
                        WebElement report = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//MenuItem[@Name='Account Balances']")));
                        System.out.println("report: " + report);
                        if (report.isDisplayed()) {
                            reportFound = false;
                            System.out.println("Rights are not revoked properly. Report is still present");
                            break;
                        }
                    }
                }
            }
            if (menuItemFound) System.out.println("No Menu Item is found.");
            if (reportItem) System.out.println("Sub-Menu is not there");
            if (reportFound) System.out.println("Rights are Revoked Successfully");
            else Assert.fail("There are some issues regarding revoke access. pls solve them");
        } catch (Exception e) {
            System.out.println("Account Groups is not found at user level. Rights revokes successfully at User Level");
        }
    }

    public void grantAccessForTransactions() throws InterruptedException, IOException, ParseException {
        common.clickElement("xpath", "//MenuItem[@Name='Configure']");
        common.clickElement("xpath", "//MenuItem[@Name='User Rights']");
        common.clickElement("xpath", "//MenuItem[@Name='Users']");
        common.clickElement("xpath", "//TreeItem[@Name='All Simple Users']");

        WebElement user1 = common.findWebElement("xpath", "//Text[@Name='Sample User_1']");
        Actions actions = new Actions(driver);
        actions.contextClick(user1).perform();
        common.clickElement("xpath", "//MenuItem[@Name='Properties']");
        Thread.sleep(3000);
        common.clickElement("xpath", "//Text[@Name='Screens']/following-sibling::Button[@Name='...']");
        common.clickElement("xpath", "//Button[@Name='Add Screens']");
        //select any 1
        List<WebElement> screens = common.findWebElements("xpath", "//Window[@Name='Add Screens']/Pane/Table/*[contains(@Name,'Row')]/*[starts-with(@Name,'Name Row')]");
        System.out.println("Total no.of screens: "+screens.size());
        for (WebElement screenNo :screens) {
            String screenName = screenNo.getText();
//            System.out.println("Transaction Name:" + screenName);
            if (screenName.equals(common.getData(dataFile, "transactionName"))) {
                screenNo.click();
                screenNo.sendKeys(Keys.LEFT, Keys.LEFT, Keys.SPACE);
                break;
            } else {
                screenNo.sendKeys(Keys.DOWN);
            }
        }
        common.clickElement("xpath", "//Button[@Name='Ok']");
        //GRANT ACCESS
        List<WebElement> elementList = common.findWebElements("xpath", "//Table/*[contains(@Name,'Row')]/*[contains(@Name,'Screen *')]");
        //System.out.println("Size :" + elementList.size());
        String value0Updtaed = "";
        for (WebElement i : elementList) {
            //System.out.println(i.getText());
            if (i.getText().contains(common.getData(dataFile,"transactionName"))) {
                value0Updtaed = i.getText();
                i.click();
                i.sendKeys(Keys.RIGHT, Keys.SPACE, Keys.RIGHT, Keys.SPACE); //can view, can enter`
                break;
            }
        }

        System.out.println("updated value: "+value0Updtaed);
        common.clickElement("xpath", "//Button[@Name='Ok']");
        common.clickElement("xpath", "//Button[@Name='Save']");
        common.clickElement("xpath", "//Button[@Name='Yes']");
        common.clickElement("xpath", "//Button[@Name='OK']");
        //verify in reports
        common.clickElement("xpath", "//MenuItem[@Name='Configure']");
        common.clickElement("xpath", "//MenuItem[@Name='User Rights']");
        common.clickElement("xpath", "//MenuItem[@Name='User - Transactions']");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Button[@Name='Submit']");
        Thread.sleep(1000);

        // Locate the row containing 'Sample User_1'
        List<WebElement> userRows = common.findWebElements("xpath", "//Table/*[@Name='Data Panel']/ListItem[contains(@Name,'Row')]");
        System.out.println("user size: " + userRows.size());
        for (int i = 0; i < userRows.size(); i++) {
            WebElement userRow = userRows.get(i);
            System.out.println("Row " + (i + 1) + ": " + userRow.getText());

            try {
                WebElement userElement = userRow.findElement(By.xpath(".//*[contains(@Name, 'User')]"));
                WebElement reportElement = userRow.findElement(By.xpath(".//*[contains(@Name, 'Screens')]"));

                String userName = userElement.getText();
                String reportName = reportElement.getText();

                if (userName.equals(common.getData(dataFile, "user")) && reportName.equals(value0Updtaed)) {
                    System.out.println("User and Report are a match, verifying can view status");
                    //if user and reportName are a match, verify the can view status and edit transaction status
                    WebElement canViewColumn = userRow.findElement(By.xpath(".//*[contains(@Name,'Can View')]"));
                    String canViewStatus = canViewColumn.getText();

                    WebElement enterTransactionCol = userRow.findElement(By.xpath(".//*[contains(@Name,'Enter Transaction ')]"));
                    String enterTransStatus = enterTransactionCol.getText();

                    System.out.println("Can View Status: " + canViewStatus);
                    System.out.println("Enter Transaction Status: "+ enterTransStatus);

                    if (canViewStatus.equalsIgnoreCase(common.getData(dataFile, "canView")) && enterTransStatus.equalsIgnoreCase(common.getData(dataFile,"enterScreen"))) {
                        System.out.println("Can View/Enter Transaction Status is updated. Report is Verified in Super User Level");
                        break;
                    } else {
                        Assert.fail("Pls check the Can View/Edit Transaction Status. Report Verification Failed");
                    }
                }
            } catch (Exception e) {
                System.out.println("Element not found for row " + (i + 1));
            }
        }
    }
    public void checkGrantForTransaction() throws InterruptedException {
        common.clickElement("xpath", "//MenuItem[@Name='Finance']");
        common.clickElement("xpath", "//MenuItem[@Name='Payments']");
        common.clickElement("xpath", "//MenuItem[@Name='Payments to Parties']");
        Thread.sleep(1700);
        System.out.println("Payments to Parties is in VIEW status in user level");
        //close
        common.clickElement("xpath", "//TabItem[@Name='Payments to Parties']/Button[@Name='Close']");
    }
    public void revokeRightsForTransaction() throws InterruptedException, IOException, ParseException {
        common.clickElement("xpath", "//MenuItem[@Name='Configure']");
        common.clickElement("xpath", "//MenuItem[@Name='User Rights']");
        common.clickElement("xpath", "//MenuItem[@Name='Users']");
        common.clickElement("xpath", "//TreeItem[@Name='All Simple Users']");

        WebElement user1 = common.findWebElement("xpath", "//Text[@Name='Sample User_1']");
        Actions actions = new Actions(driver);
        actions.contextClick(user1).perform();
        common.clickElement("xpath", "//MenuItem[@Name='Properties']");
        Thread.sleep(3000);
        common.clickElement("xpath", "//Text[@Name='Screens']/following-sibling::Button[@Name='...']");
        //REVOKE ACCESS
        List<WebElement> elementList = common.findWebElements("xpath", "//Table/*[contains(@Name,'Row')]/*[contains(@Name,'Screen *')]");
        System.out.println("Size :" + elementList.size());
        for (WebElement i : elementList) {
            System.out.println(i.getText());
            if (i.getText().equals(common.getData(dataFile,"transactionUpdated"))) {
                i.click();
                i.sendKeys(Keys.LEFT, Keys.SPACE); //select and delete
                common.clickElement("xpath", "//Button[@Name='Delete Selected']");
                common.clickElement("xpath", "//Button[@Name='Yes']");
                break;
            }
        }

        common.clickElement("xpath", "//Button[@Name='Ok']");
        common.clickElement("xpath", "//Button[@Name='Save']");
        common.clickElement("xpath", "//Button[@Name='Yes']");
        common.clickElement("xpath", "//Button[@Name='OK']");
        //verify in reports
        common.clickElement("xpath", "//MenuItem[@Name='Configure']");
        common.clickElement("xpath", "//MenuItem[@Name='User Rights']");
        common.clickElement("xpath", "//MenuItem[@Name='User - Transactions']");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Button[@Name='Submit']");
        Thread.sleep(1000);

        // Locate the row containing 'Sample User_1'
        List<WebElement> userRows = common.findWebElements("xpath", "//Table/*[@Name='Data Panel']/ListItem[contains(@Name,'Row')]");
        System.out.println("user size: " + userRows.size());
        boolean transactionFound=true;
        for (int i = 0; i < userRows.size(); i++) {
            WebElement userRow = userRows.get(i);
            System.out.println("Row " + (i + 1) + ": " + userRow.getText());

            try {
                WebElement userElement = userRow.findElement(By.xpath(".//*[contains(@Name, 'User')]"));
                WebElement reportElement = userRow.findElement(By.xpath(".//*[contains(@Name, 'Screens')]"));

                String userName = userElement.getText();
                String reportName = reportElement.getText();

                if (userName.equals(common.getData(dataFile, "user")) && reportName.equals(common.getData(dataFile,"transactionUpdated"))) {
                    transactionFound=false;
                    Assert.fail("Transaction is still present. Report Verification Failed");
                }
            } catch (Exception e) {
                System.out.println("Element not found for row " + (i + 1));
            }
        }
        if (transactionFound){
            System.out.println("Transaction is successfully deleted. Report verification at super user level is passed");
        }
    }
    public void checkRevokeAccessForTransaction(){
        try {
            boolean menuItemFound = true, reportFound = true, reportItem = true;
            List<WebElement> menuItem = common.findWebElements("xpath", "//MenuBar/MenuItem");
            System.out.println("items: " + menuItem.size());
            for (WebElement items : menuItem) {
                System.out.println(items.getText());
                //sometimes Finance will be there. so check it and click if it is present. then check inside masters
                if (items.getText().equalsIgnoreCase("Finance")) {
                    menuItemFound = false;
                    items.click();
                    WebDriverWait wait=new WebDriverWait(driver,10);
                    WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//MenuItem[@Name='Payments']")));
                    System.out.println("subItem:" + element.getText());
                    if (element.isDisplayed()) {
                        element.click();//if the master is present, it will go inside loop and boolen will become false whic will fail the script.
                        reportItem = false;
                        WebElement report = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//MenuItem[@Name='Payments to Parties']")));
                        System.out.println("transaction: " + report.getText());
                        if (report.isDisplayed()) {
                            Assert.fail("Revoke isn't done properly. Transaction is still present");
                        }
                    }
                }
            }
            if (menuItemFound) System.out.println("No Menu Item is found.");
            if (reportItem) System.out.println("Sub-Menu is not there");
        } catch (Exception e) {
            System.out.println("Payments to Parties is not found at user level. Rights revokes successfully at User Level");
        }
    }

    public void grantAccessForMasterProperties() throws InterruptedException, IOException, ParseException {
        common.clickElement("xpath", "//MenuItem[@Name='Configure']");
        common.clickElement("xpath", "//MenuItem[@Name='User Rights']");
        common.clickElement("xpath", "//MenuItem[@Name='Users']");
        common.clickElement("xpath", "//TreeItem[@Name='All Simple Users']");

        WebElement user1 = common.findWebElement("xpath", "//Text[@Name='Sample User_1']");
        Actions actions = new Actions(driver);
        actions.contextClick(user1).perform();
        common.clickElement("xpath", "//MenuItem[@Name='Properties']");
        Thread.sleep(2000);
        common.clickElement("xpath", "//Text[@Name='Master Groups']/following-sibling::Button[@Name='...']");
        common.clickElement("xpath", "//Button[@Name='Add Master Groups']");
        List<String> properties=new ArrayList<>();
        List<WebElement> screens = common.findWebElements("xpath", "//Window[@Name='Add Master Groups']/Pane/Table/*[contains(@Name,'Row')]/*[starts-with(@Name,'Master Type Row ')]");
       // System.out.println("Total no.of masters: "+screens.size());
        for (WebElement screenNo :screens) {
            String screenName = screenNo.getText();
            //System.out.println("Master Name:" + screenName);
            if (screenName.equals(common.getData(dataFile, "propertyMaster"))) {
                screenNo.click();
                screenNo.sendKeys(Keys.LEFT, Keys.SPACE);
                break;
            } else {
                screenNo.sendKeys(Keys.DOWN);
            }
        }
        common.clickElement("xpath", "//Button[@Name='Ok']");
        List<WebElement> elementList = common.findWebElements("xpath", "//Table/*[contains(@Name,'Row')]/*[contains(@Name,'Master Type *')]");
        //System.out.println("Size :" + elementList.size());
        String value0Updtaed = "";
        for (WebElement i : elementList) {
            //System.out.println(i.getText());
            if (i.getText().equalsIgnoreCase(common.getData(dataFile,"propertyMaster"))) {
                value0Updtaed = i.getText();
                i.click();
                i.sendKeys(Keys.RIGHT, Keys.SPACE, Keys.RIGHT, Keys.SPACE,Keys.RIGHT,Keys.RIGHT,Keys.RIGHT,Keys.RIGHT,Keys.RIGHT,Keys.SPACE); //can view master, can create master,properties
                //fetch the properties
                List<WebElement> property = common.findWebElements("xpath", "//Window[@Name='MasterProperties']/Pane/Pane/Pane/Pane/Table/*[starts-with(@Name,'Row')]/*[starts-with(@Name,'Property *')]");
                System.out.println("list of properties: "+ property.size());
                for (WebElement prop: property){
                    //System.out.println("Text:"+prop.getText());
                    if (!prop.getText().equals("(null)")) properties.add(prop.getText());
                }
                //view
                WebElement viewButton= common.findWebElement("xpath","//Window[@Name='MasterProperties']/Pane/Pane/Pane/Pane/Table/*[@Name='Top Row']/Header[@Name='View']");
                Actions actions1=new Actions(driver);
                actions1.contextClick(viewButton).perform();
                Thread.sleep(2000);
                WebElement checkAll=common.findWebElement("xpath","//MenuItem[@Name='Check All']");
                Actions clicked = actions1.moveToElement(checkAll).click();
                clicked.perform();
                common.clickElement("xpath", "//Window[@Name='Message']/Button[@Name='OK']");
                //edit
                WebElement editButton= common.findWebElement("xpath","//Window[@Name='MasterProperties']/Pane/Pane/Pane/Pane/Table/*[@Name='Top Row']/Header[@Name='Edit']");
                Actions actions2=new Actions(driver);
                actions2.contextClick(editButton).perform();
                Thread.sleep(2000);
                WebElement checkAll1=common.findWebElement("xpath","//MenuItem[@Name='Check All']");
                Actions clicked1 = actions2.moveToElement(checkAll1).click();
                clicked1.perform();
                common.clickElement("xpath", "//Window[@Name='Message']/Button[@Name='OK']");
                Thread.sleep(1000);
                common.clickElement("xpath", "//Button[@Name='Ok']");
                break;
            }
        }

        System.out.println("Updated value: " + value0Updtaed);
        common.clickElement("xpath", "//Button[@Name='Ok']");
        //save
        common.clickElement("xpath", "//Button[@Name='Save']");
        common.clickElement("xpath", "//Button[@Name='Yes']");
        common.clickElement("xpath", "//Button[@Name='OK']");
        //verify in reports
        common.clickElement("xpath", "//MenuItem[@Name='Configure']");
        common.clickElement("xpath", "//MenuItem[@Name='User Rights']");
        common.clickElement("xpath", "//MenuItem[@Name='User - Master Properties']");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Button[@Name='Submit']");
        Thread.sleep(1000);
        // Locate the row containing 'Sample User_1'
        List<String> reportProperties=new ArrayList<>();
        List<WebElement> userRows = common.findWebElements("xpath", "//Table/*[@Name='Data Panel']/ListItem[contains(@Name,'Row')]");
        System.out.println("user size: " + userRows.size());
        for (int i = 0; i < userRows.size(); i++) {
            WebElement userRow = userRows.get(i);
            System.out.println("Row " + (i + 1) + ": " + userRow.getText());
            WebElement userElement = userRow.findElement(By.xpath(".//*[contains(@Name, 'User')]"));
            WebElement masterTypeElement = userRow.findElement(By.xpath(".//*[contains(@Name, 'Master Types')]"));
            String userName = userElement.getText();
            String masterType = masterTypeElement.getText();
            if (userName.equals(common.getData(dataFile, "user")) && masterType.equals(value0Updtaed)) {
                //System.out.println("User and Report are a match, fetching master properties");
                // Fetch Master Property column
                WebElement masterPropertyElement = userRow.findElement(By.xpath(".//*[starts-with(@Name,'Master Properties')]"));
                String masterProperty = masterPropertyElement.getText().trim();
                //System.out.println("Master Property: " + masterProperty);
                //add masterProperty to reportsProperties list
                reportProperties.add(masterPropertyElement.getText());
                //if user and reportName are a match, verify the can view status
                WebElement canViewColumn = userRow.findElement(By.xpath(".//*[contains(@Name,'Can View')]"));
                WebElement canEditColumn = userRow.findElement(By.xpath(".//*[contains(@Name,'Can Edit')]"));
                String canViewStatus = canViewColumn.getText();
                String canEditStatus = canEditColumn.getText();
                System.out.println("Can View: " + canViewStatus + ", Can Edit: " + canEditStatus);
                if (canViewStatus.equalsIgnoreCase(common.getData(dataFile, "canView")) && canEditStatus.equalsIgnoreCase(common.getData(dataFile, "canEditMasterProperty"))) {
                    System.out.println("Verification passed for Master Property: " + masterProperty);
                } else {
                    Assert.fail("Mismatch in Can View / Can Edit Status for Master Property: " + masterProperty);
                }
            }
            else {
                break;
            }
        }
        Assert.assertTrue(properties.equals(reportProperties),"Properties are not of same length. Pls verify properties");
    }
    public void checkGrantForMasterProperties(){
        common.clickElement("xpath","//MenuItem[@Name='Sales']");
        common.clickElement("xpath","//MenuItem[@Name='Customers']");
//        Thread.sleep(1000);
        WebDriverWait wait=new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//TreeItem[@Name='All Customers']"))).click();
        WebElement regCust= common.findWebElement("xpath","//Text[@Name='AT_Cus_Reg_Inter']");
        Actions actions=new Actions(driver);
        actions.doubleClick(regCust).perform();
        //fetch all the panes
        List<WebElement> propertyPanes = common.findWebElements("xpath", "//Pane[@Name='Transaction']/Pane/Pane/Pane/*");
        System.out.println("no of panes: "+propertyPanes.size());
        for (WebElement pp:propertyPanes){
            System.out.println(pp.getText());
        }
    }
    public void revokeAccessForMasterProperties(){}
    public void checkRevokeAccessForMasterProperties(){}

}
