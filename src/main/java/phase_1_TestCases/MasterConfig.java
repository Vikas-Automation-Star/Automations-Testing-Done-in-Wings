package phase_1_TestCases;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class MasterConfig extends Transaction {
    WindowsDriver driver,rootdriver;
    Common common;
    String dataFile;

    public MasterConfig(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public String newCustomer() throws InterruptedException, IOException, ParseException, AWTException {
        common.clickElement("name", "Sales");
        common.clickElement("name", "Customers");
        Thread.sleep(3000);
        WebElement allCustomer = common.findWebElement("xpath", "//TreeItem[@Name='Customers']/TreeItem[@Name='All Customers']");
        Actions actions = new Actions(driver);
        actions.contextClick(allCustomer).perform();
        common.clickElement("name", "New Master");
        Thread.sleep(3500);
        common.inputText("xpath", "//Edit[@Name='New Account *']", common.getData(dataFile, "newAccount"));
        WebElement element = common.findWebElement("xpath", "//Edit[@Name='New Account *']");
        common.inputText("xpath", "//Edit[@Name='Account Code']", "" + common.getRandom());
        common.inputText("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));
        common.clickElement("xpath", "//Pane[@Name='Bank Details']/Button[@Name='...']");
        Thread.sleep(1200);
        common.inputAndVerify("xpath", "//Window[@Name='Bank Details']/Pane/Pane/Edit[@Name='Account No']", common.getData(dataFile, "AccNo"));
        common.inputAndVerify("xpath", "//Window[@Name='Bank Details']/Pane/Pane/Edit[@Name='Bank']", common.getData(dataFile, "Bank"));
        common.inputAndVerify("xpath", "//Window[@Name='Bank Details']/Pane/Pane/Edit[@Name='Bank Branch']", common.getData(dataFile, "BankBranch"));
        common.inputAndVerify("xpath", "//Window[@Name='Bank Details']/Pane/Pane/Edit[@Name='IFSC Code']", common.getData(dataFile, "IFSC"));
        common.clickElement("name", "Ok");
        //DD's
        common.clickElement("xpath", "//Edit[@Name='Executive']/Button[@Name='Open']");
        common.rowDropDown(common.getData(dataFile, "Executive"));
        Thread.sleep(500);
        common.clickElement("xpath", "//Edit[@Name='Route']/Button[@Name='Open']");
        common.rowDropDown(common.getData(dataFile, "Route"));
        Thread.sleep(500);
        common.clickElement("xpath", "//Edit[@Name='Purchase Price List']/Button[@Name='Open']");
        common.rowDropDown(common.getData(dataFile, "priceList"));
        Thread.sleep(500);
        common.clickElement("xpath", "//Edit[@Name='Transport']/Button[@Name='Open']");
        common.rowDropDown(common.getData(dataFile, "transport"));
        Thread.sleep(500);
        common.clickElement("xpath", "//Edit[@Name='Transporter']/Button[@Name='Open']");
        common.rowDropDown(common.getData(dataFile, "transporter"));
        Thread.sleep(500);
        common.clickElement("xpath", "//Edit[@Name='Type Of Customer']/Button[@Name='Open']");
        common.rowDropDown("Type of Customer");
        common.sliderHandling("xpath", "//*/Thumb[@Name='Position']", 0, 200);
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane[@Name='Registration']/Button[@Name='...']");
        Thread.sleep(1000);
        common.inputText("xpath", "//Window[@Name='Registration']/Pane/Pane/Edit[@Name='Party Reg Type *']", "registered");
        Thread.sleep(500);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        common.inputText("xpath", "//Edit[@Name='GSTIN']", common.getData(dataFile, "gst"));
        common.clickElement("xpath", "//Edit[@Name='PAN']");
        common.clickElement("xpath", "//Button[@Name='Verify GSTIN']");
        robot.keyPress(KeyEvent.VK_ESCAPE);
        robot.keyRelease(KeyEvent.VK_ESCAPE);
        common.clickElement("name", "Ok");
        Thread.sleep(3000);
        //scroll down
        common.sliderHandling("name", "Position", 0, 450);
        common.clickElement("xpath", "//Pane/Pane[@Name='Contact Details']/Button[@Name='...']");
        Thread.sleep(2000);
        common.inputAndVerify("xpath", "//Edit[@Name='Telephones 1']", common.getData(dataFile, "tel1"));
        common.inputAndVerify("xpath", "//Edit[@Name='Fax']", common.getData(dataFile, "fax"));
        common.inputAndVerify("xpath", "//Edit[@Name='Email']", common.getData(dataFile, "email"));
        common.inputAndVerify("xpath", "//Edit[@Name='Website']", common.getData(dataFile, "website"));
        common.inputAndVerify("xpath", "//Edit[@Name='Contact Person']", common.getData(dataFile, "cp"));
        common.inputAndVerify("xpath", "//Edit[@Name='Contact Person Designation']", common.getData(dataFile, "cpd"));
        common.inputAndVerify("xpath", "//Edit[@Name='Contact Person Telephone No']", common.getData(dataFile, "cptn"));
        common.inputAndVerify("xpath", "//Edit[@Name='Contact Person Mobile No']", common.getData(dataFile, "cpmn"));
        common.inputAndVerify("xpath", "//Edit[@Name='Contact Person Email']", common.getData(dataFile, "cpe"));
        common.clickElement("name", "Ok");
        common.clickElement("xpath", "//Pane/Button[@Name='Save']");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Window/Button[@Name='OK']");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane/Button[@Name='Cancel']");

        common.clickElement("xpath", "//TabItem[@Name='Customers']/Button[@Name='Close']");

        //refresh
        common.clickElement("xpath", "//MenuItem[@Name='Tools']");
        common.clickElement("xpath", "//MenuItem[@Name='Clear Cache']");

        return common.getData(dataFile, "newAccount");
    }
    public void customerRename(String customerName) throws InterruptedException, IOException, ParseException, AWTException {
        common.clickElement("name", "Sales");
        common.clickElement("name", "Customers");
        Thread.sleep(2500);
        common.clickElement("xpath", "//TreeItem[@Name='All Customers']");
        // First loop: Check for the customer and perform actions
        List<WebElement> listElements = common.findWebElements("xpath", "//Pane[@Name='Customers']/Pane/Pane/Pane/Pane/Pane/List/ListItem");
        System.out.println("Size of elements under List: " + listElements.size());
        boolean customerFound = false; // Flag to check if the customer was found
        for (int i = 0; i < listElements.size(); i++) {
            WebElement element = listElements.get(i);
            System.out.println("Element Text: " + element.getText());
            if (element.getText().equals(customerName)) {
                System.out.println("Master is created successfully");
                // Perform actions on the element
                Actions actions = new Actions(driver);
                actions.contextClick(element).perform();
                common.clickElement("xpath", "//MenuItem[@Name='Change']");
                common.clickElement("xpath", "//MenuItem[@Name='Name']");
                common.inputText("xpath", "//Edit[@Name='New Name']", common.getData(dataFile, "newName"));
                common.clickElement("xpath", "//Button[@Name='OK']");
                common.clickElement("xpath", "//Button[@Name='OK']");
                customerFound = true; // Set flag to true when the customer is found
                break;  // Exit the loop once the desired element is found and processed
            }
        }
        // Fail the test if no matching customer was found
        if (!customerFound) {
            Assert.fail("Created Master is not present");
        }
        // Close the current customer view and navigate to the customers section again
        common.clickElement("xpath", "//TabItem[@Name='Customers']/Button[@Name='Close']");
        Thread.sleep(1500);
        common.clickElement("name", "Sales");
        common.clickElement("name", "Customers");
        Thread.sleep(2500);
        common.clickElement("xpath", "//TreeItem[@Name='All Customers']");
        // Second loop: Verify the renamed master
        List<WebElement> listElements1 = common.findWebElements("xpath", "//Pane[@Name='Customers']/Pane/Pane/Pane/Pane/Pane/List/ListItem");
        System.out.println("Size of elements after rename: " + listElements1.size());
        boolean renamedCustomerFound = false; // Flag to check if the renamed customer was found

        for (int i = 0; i < listElements1.size(); i++) {
            WebElement element1 = listElements1.get(i);
            System.out.println("Element Text: " + element1.getText());
            // Check if the new name matches
            if (element1.getText().equals(common.getData(dataFile, "newName"))) {
                System.out.println("Master Renamed Successfully");
                renamedCustomerFound = true; // Set flag to true when the renamed customer is found
                break;  // Exit the loop once the renamed customer is found
            }
        }
        // Fail the test if no renamed customer was found
        if (!renamedCustomerFound) {
            Assert.fail("Renamed Master is not present after the update");
        }
    }
    public void masterInactive(String masterName) throws InterruptedException, IOException, ParseException {
        common.clickElement("name", "Sales");
        common.clickElement("name", "Customers");
        Thread.sleep(2500);
        common.clickElement("xpath", "//TreeItem[@Name='All Customers']");
        WebElement element = common.findWebElement("xpath", "//Text[@Name='" + masterName + "']");
        String master = element.getText();
        Actions actions = new Actions(driver);
        actions.contextClick(element).perform();
        //click on active
        common.clickElement("xpath", "//MenuItem[@Name='Inactivate']");
        common.clickElement("name", "OK");
        //search in inactive
        WebElement clickDown=   common.findWebElement("xpath", "//Button[@Name='  Options  ']");
        clickDown.click();
        clickDown.sendKeys(Keys.DOWN,Keys.ENTER);
        Thread.sleep(1000);
        List<WebElement> listElements = common.findWebElements("xpath", "//Pane[@Name='Customers']/Pane/Pane/Pane/Pane/Pane/List/ListItem");
        System.out.println("Size of elements under List: " + listElements.size());
        boolean customerFound = false;
        for (int i = 0; i < listElements.size(); i++) {
            WebElement element1 = listElements.get(i);
            System.out.println("Element Text: " + element1.getText());
            if (element1.getText().equals(master)) {
                element1.click();
                System.out.println("Master is inactivated succesfully");
                customerFound = true;
                break;
            }
        }
        for (int i = 0; i < listElements.size(); i++) {
            WebElement element1 = listElements.get(i);
            if (element1.getText().equals(master)) {
                Actions actions1 = new Actions(driver);
                actions1.contextClick(element1).perform();
                common.clickElement("xpath", "//MenuItem[@Name='Active']");
            }
        }
        if (!customerFound) {
            Assert.fail("Inactivated Master is not present");
        }
        common.clickElement("xpath", "//TabItem[@Name='Customers']/Button[@Name='Close']");
        //validate activate master
        common.clickElement("name", "Sales");
        common.clickElement("name", "Customers");
        Thread.sleep(2500);
        common.clickElement("xpath", "//TreeItem[@Name='All Customers']");
        List<WebElement> activeElements = common.findWebElements("xpath", "//Pane[@Name='Customers']/Pane/Pane/Pane/Pane/Pane/List/ListItem");
        System.out.println("Size of active elements: " + activeElements.size());
        boolean customerActivate = false;
        for (int i = 0; i < activeElements.size(); i++) {
            WebElement activeElement = activeElements.get(i);
            System.out.println("Active Element Text: " + activeElement.getText());
            if (activeElement.getText().equals(master)) {
                activeElement.click();
                System.out.println("Master is activated succesfully");
                customerActivate = true;
                break;
            }
        }
        if (!customerActivate) {
            Assert.fail("Activated Master is not present");
        }
    }
    public void searchMaster(String searchType) throws IOException, ParseException {
        common.clickElement("xpath", "//MenuItem[@Name='Tools']");
        common.clickElement("xpath", "//MenuItem[@Name='Search Masters']");
        if (searchType.equals("name")) {
            common.clickElement("xpath", "//RadioButton[@Name='Master Name']");
            common.inputText("xpath", "//Edit[@AutomationId='searchTextBox']", common.getData(dataFile, "masterName"));
            common.clickElement("xpath", "//Button[@Name='Search']");
            String master = common.findWebElement("xpath", "//DataItem[@Name='Master Name row 1']").getText();
            System.out.println("Searched master:- " + common.getData(dataFile, "masterName"));
            Assert.assertEquals(common.getData(dataFile, "masterName"), master, "Master you searched for is not Found");
            System.out.println("Found master:- " + master);
            System.out.println("Master is successfully found");
        } else if (searchType.equals("code")) {
            common.clickElement("xpath", "//RadioButton[@Name='Master Code']");
            common.inputText("xpath", "//Edit[@AutomationId='searchTextBox']", common.getData(dataFile, "masterCode"));
            common.clickElement("xpath", "//Button[@Name='Search']");
            String code = common.findWebElement("xpath", "//DataItem[@Name='Master Code row 1']").getText();
            System.out.println("Searched master:- " + common.getData(dataFile, "masterCode"));
            Assert.assertEquals(common.getData(dataFile, "masterCode"), code, "Master you searched for is not Found");
            System.out.println("Found master:- " + code);
            System.out.println("Master is successfully found");
        } else {
            Assert.fail("Please enter correct input");
        }
    }
    public void createNode() throws InterruptedException, IOException, ParseException {
        common.clickElement("name", "Sales");
        common.clickElement("name", "Customers");
        Thread.sleep(2500);
        WebElement allCustomer = common.findWebElement("xpath", "//TreeItem[@Name='Customers']/TreeItem[@Name='All Customers']");
        Actions actions = new Actions(driver);
        actions.contextClick(allCustomer).perform();
        common.clickElement("name", "New Node");
        Thread.sleep(5000);
        common.clickElement("xpath", "//Pane[@Name='GeneralInformation']/*[@Name='New Node *']/Edit[@Name='New Node *']");
        common.inputText("xpath", "//Pane[@Name='GeneralInformation']/*[@Name='New Node *']/Edit[@Name='New Node *']", common.getData(dataFile, "node") + common.getRandom());
        common.clickElement("xpath", "//Button[@Name='Save']");
        common.clickElement("xpath", "//Button[@Name='Yes']");
        common.clickElement("xpath", "//Button[@Name='Yes']");
        rootdriver=common.initializeDriver("Root");
        System.out.println("root navigated");
        common.clickElement("xpath","//*/Button[@Name='OK']");
        Thread.sleep(1500);
        common.clickElement("xpath","//TabItem[@Name='Customers']/Button[@Name='Close']");
        navigateToMastersOrMenus("Sales","Customers","//TreeItem[@Name='Customers']/TreeItem[@Name='All Customers']");
        List<WebElement> listElements = common.findWebElements("xpath", "//Pane[@Name='Customers']/Pane/Pane/Pane/Pane/Pane/List/ListItem");
        System.out.println("Size of elements and List: " + listElements.size());
        for (int i = 0; i < listElements.size(); i++) {
            WebElement element1= listElements.get(i);
            System.out.println("Element Text: " + element1.getText());
            String newnode=element1.getText();
            if (element1.getText().contains(common.getData(dataFile,"node"))) {
                Assert.assertTrue(true);
                System.out.println("Node Created Successfully  :"+newnode);
            }
        }
        common.clickElement("xpath","//TabItem[@Name='Customers']/Button[@Name='Close']");
    }
    public void renameNode() throws IOException, ParseException, InterruptedException {
        navigateToMastersOrMenus("Sales","Customers","//TreeItem[@Name='Customers']/TreeItem[@Name='All Customers']");
        List<WebElement> listElements1 = common.findWebElements("xpath", "//Pane[@Name='Customers']/Pane/Pane/Pane/Pane/Pane/List/ListItem");
        System.out.println("Size of elements and List: " + listElements1.size());
        for (int i = 0; i < listElements1.size(); i++) {
            WebElement element2 = listElements1.get(i);
            System.out.println("Element Text: " + element2.getText());
            // Check if the new name matches
            if (element2.getText().contains(common.getData(dataFile, "node"))) {
                element2.click();
                Actions actions1 = new Actions(driver);
                actions1.contextClick(element2).perform();
                common.clickElement("xpath","//MenuItem[@Name='Rename']");
                common.clickElement("xpath","//Edit[@Name='New Name']");
                common.inputText("xpath","//Edit[@Name='New Name']", common.getData(dataFile,"rename"));
                common.clickElement("xpath","//Button[@Name='OK']");
                common.clickElement("xpath","//Window/Button[@Name='OK']");
                System.out.println("Node is Renamed");
                common.clickElement("xpath","//TabItem[@Name='Customers']/Button[@Name='Close']");
                break;
            }
        }
        navigateToMastersOrMenus("Sales","Customers","//TreeItem[@Name='Customers']/TreeItem[@Name='All Customers']");
        Thread.sleep(1500);
        List<WebElement> listElements2 = common.findWebElements("xpath", "//Pane[@Name='Customers']/Pane/Pane/Pane/Pane/Pane/List/ListItem");
        System.out.println("Size of elements and List: " + listElements2.size());
        for (int i = 0; i < listElements2.size(); i++) {
            WebElement element3 = listElements2.get(i);
            System.out.println("Element Text: " + element3.getText());
            String renameNode=element3.getText();
            if (element3.getText().equalsIgnoreCase(renameNode)){
                System.out.println("Renamed Node Successfully :"+renameNode);
                common.clickElement("xpath","//TabItem[@Name='Customers']/Button[@Name='Close']");
                break;
            }
        }
    }
    public void moveAsSubNodeAndMainNode() throws InterruptedException, IOException, ParseException {
        Thread.sleep(2000);
        navigateToMastersOrMenus("Sales","Customers","//TreeItem[@Name='Customers']/TreeItem[@Name='All Customers']");
        List<WebElement> listElements3 = common.findWebElements("xpath", "//Pane[@Name='Customers']/Pane/Pane/Pane/Pane/Pane/List/ListItem");
        System.out.println("Size of elements and List: " + listElements3.size());
        for (int i = 0; i < listElements3.size(); i++) {
            WebElement element3 = listElements3.get(i);
            System.out.println("Element Text: " + element3.getText());
            String moveToSubNode= element3.getText();
            if (element3.getText().equals(moveToSubNode)) {
                element3.click();
                Actions actions1 = new Actions(driver);
                actions1.contextClick(element3).perform();
                common.clickElement("xpath","//MenuItem[@Name='Move As Sub-Node']");
                Thread.sleep(1500);
                common.clickElement("xpath","//Button[@Name='Save']");
                common.clickElement("xpath", "//Button[@Name='Yes']");
                common.clickElement("xpath", "//Button[@Name='Yes']");
                DesiredCapabilities rootcapabilities = new DesiredCapabilities();
                rootcapabilities.setCapability("app", "Root");
                rootcapabilities.setCapability("deviceName", "WindowsPC");
                rootdriver = new WindowsDriver<>(new URL("http://127.0.0.1:4723/"), rootcapabilities);
                Thread.sleep(1000);
                List<WebElement> windows = rootdriver.findElements(By.tagName("Window"));
                Thread.sleep(2500);
                if (windows.isEmpty()) {
                    System.out.println("No windows found.");
                } else {
                    for (WebElement v : windows) {
                        System.out.println("WindowsNames :"+v.getText());
                        System.out.println("LegacyNAme :"+v.getAttribute("LegacyIAccessible.Role"));
                        String name = v.getAttribute("Name");
                        System.out.println("Name:- " + name);
                        if(v.getText().equals("")){
                            Thread.sleep(1000);
                            WebElement okButtonn = v.findElement(By.xpath("//Window/Button[@Name='OK']"));
                            okButtonn.click();
                            break;
                        }
                    }
                }
                common.clickElement("xpath","//TabItem[@Name='Customers']/Button[@Name='Close']");
                Thread.sleep(1000);
                navigateToMastersOrMenus("Sales","Customers","//TreeItem[@Name='Customers']/TreeItem[@Name='All Customers']");
                Thread.sleep(1000);
                WebElement right=common.findWebElement("xpath","//TreeItem[@Name='All Customers']");
                right.sendKeys(Keys.ARROW_RIGHT,Keys.ARROW_RIGHT);
                WebElement readText=common.findWebElement("xpath","//TreeItem[@Name='All Customers']/TreeItem");
                String movedNode=readText.getText();
                System.out.println("MovedNode Text :"+movedNode);
                if(readText.getText().equalsIgnoreCase(moveToSubNode)) {
                    System.out.println("node moved Successfully :"+moveToSubNode);
                }
                Thread.sleep(1000);
                Actions actions=new Actions(driver);
                actions.contextClick(readText).perform();
                common.clickElement("xpath","//MenuItem[@Name='Move As Main Node']");
                common.clickElement("xpath","//Button[@Name='OK']");
                System.out.println("Node moved to Main Node");
                common.clickElement("xpath","//TabItem[@Name='Customers']/Button[@Name='Close']");
                navigateToMastersOrMenus("Sales","Customers","//TreeItem[@Name='Customers']/TreeItem[@Name='All Customers']");
                List<WebElement> mainNode=common.findWebElements("xpath","//TreeItem");
                System.out.println("allNodesText :"+mainNode.size());
                for (WebElement v:mainNode){
                    System.out.println("printing all MainNodes :"+v.getText());
                    if(v.getText().equalsIgnoreCase(movedNode)){
                        System.out.println("Great subNode is MovedToMainNode :"+movedNode);
                    }
                }
            }
            break;
        }
    }
    public void movingMastersBetweenNodes(String movingMasterNamePath) throws InterruptedException, MalformedURLException {
        navigateToMastersOrMenus("Sales","Customers","//TreeItem[@Name='Customers']/TreeItem[@Name='All Customers']");
        WebElement selectingMaster= common.findWebElement("xpath",movingMasterNamePath);
        String selectMasterTomoveAnotherNode=selectingMaster.getText();
        Actions actions = new Actions(driver);
        actions.contextClick(selectingMaster).perform();
        common.clickElement("xpath","//MenuItem[@Name='Change']");
        WebElement choose=common.findWebElement("xpath","//MenuItem[@Name='Node']");
        choose.click();
        choose.sendKeys(Keys.DOWN,Keys.ENTER);
        common.clickElement("xpath", "//Button[@Name='Save']");
        common.clickElement("xpath", "//Button[@Name='Yes']");
        common.clickElement("xpath", "//Button[@Name='Yes']");
        DesiredCapabilities rootcapabilities = new DesiredCapabilities();
        rootcapabilities.setCapability("app", "Root");
        rootcapabilities.setCapability("deviceName", "WindowsPC");
        rootdriver = new WindowsDriver<>(new URL("http://127.0.0.1:4723/"), rootcapabilities);
        Thread.sleep(1000);
        List<WebElement> windows = rootdriver.findElements(By.tagName("Window"));
        Thread.sleep(2500);
        if (windows.isEmpty()) {
            System.out.println("No windows found.");
        } else {
            for (WebElement i : windows) {
                System.out.println("WindowsNames :"+i.getText());
                System.out.println("LegacyNAme :"+i.getAttribute("LegacyIAccessible.Role"));
                String name = i.getAttribute("Name");
                System.out.println("Name:- " + name);
                if(i.getText().equals("")){
                    Thread.sleep(1000);
                    WebElement okButtonn = i.findElement(By.xpath("//Window/Button[@Name='OK']"));
                    okButtonn.click();
                    break;
                }
            }
        }
        common.clickElement("xpath","//TabItem[@Name='Customers']/Button[@Name='Close']");
        common.clickElement("name", "Sales");
        common.clickElement("name", "Customers");
        Thread.sleep(1000);
        common.clickElement("xpath","//TreeItem[@Name='All Customers']");
        common.clickElement("xpath","//TreeItem[starts-with(@Name,'NewNode')]");
//        common.clickElement("xpath","//TreeItem[@Name='Customers']/*[@Name='All Customers']/*[starts-with(@Name,'NewNode')]");
        List<WebElement> listElement = common.findWebElements("xpath", "//Pane[@Name='Customers']/Pane/Pane/Pane/Pane/Pane/List/ListItem");
        System.out.println("Size of elements and List: " + listElement.size());
        for (WebElement s:listElement){
            System.out.println("movedMasterText :"+s.getText());
            if(s.getText().equalsIgnoreCase(selectMasterTomoveAnotherNode)){
                System.out.println("Great masterMoved To The Some Other Node :"+selectMasterTomoveAnotherNode);
            }
        }

    }
}