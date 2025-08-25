package phase_1_TestCases;

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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TransactionFeatures extends Transaction {

    WindowsDriver driver, rootdriver;
    Common common;
    String dataFile,dataset="beforeEditing";

    public TransactionFeatures(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void saveAsDraft() throws InterruptedException, IOException, ParseException {
        navigateToSalesEnquiryMenu();
        Thread.sleep(1500);
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        common.clickElement("xpath", "//Edit[@Name='Trans Currency *']");
        common.clickElement("xpath", "//Edit[@Name='Party Code']");
        common.clickElement("xpath", "//Edit[@Name='Party Account *']");
//        Thread.sleep(5000);
//        gstTransactionType(common.getData(dataFile, "gstType"));
        Thread.sleep(1500);
        common.clickElement("xpath", "//Edit[@Name='Price List']");
        common.clickElement("xpath", "//Edit[@Name='Executive *']");
        common.clickElement("xpath", "//Edit[@Name='Remarks']");
        common.inputText( "xpath", "//Edit[@Name='Remarks']",common.getData(dataFile, "remarks"));
        //items
        enterDataAndValidate("xpath", "//Edit[@Name='Product Code Row 0, Not sorted.']", dataFile,dataset, "draftProduct");
//        enterData("xpath", "//Edit[@Name='Quantity * Row 0, Not sorted.']", dataFile, "draftQuantity");
        //save as draft
        common.clickElement("xpath", "//ToolBar/Button[@Name='Tools']");
        common.clickElement("xpath", "//Button[@Name='Draft']");
        common.clickElement("xpath", "//Button[@Name='Save Draft']");
        common.clickElement("xpath", "//Button[@Name='OK']");
        common.clickElement("xpath", "//Window[@Name='Transaction saved.']/Button[@Name='OK']");
        //open draft
        common.clickElement("xpath", "//ToolBar/Button[@Name='Tools']");
        common.clickElement("xpath", "//Button[@Name='Draft']");
        common.clickElement("xpath", "//Button[@Name='Open Draft']");

        // Go to Root and navigate to Open Transaction Window
        DesiredCapabilities rootcapabilities = new DesiredCapabilities();
        rootcapabilities.setCapability("app", "Root");
        rootcapabilities.setCapability("deviceName", "WindowsPC");
        rootdriver = new WindowsDriver<>(new URL("http://127.0.0.1:4723/"), rootcapabilities);
        List<WebElement> panes = rootdriver.findElements(By.tagName("Window"));
        for (WebElement i : panes) {
            String nativeWindow = i.getAttribute("NativeWindowHandle");
            String hexloginid = Integer.toHexString(Integer.parseInt(nativeWindow));
            System.out.println("window id: " + hexloginid);
            String name = i.getAttribute("Name");
            System.out.println("Name:- " + name);
            if (i.getAttribute("Name").equals("Open Draft Transaction")) {
                // Find all DateOfSaving rows
                List<WebElement> dateElements = rootdriver.findElementsByXPath("//DataItem[contains(@Name, 'DateOfSaving row')]");
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                Date latestDate = null;
                WebElement latestDateElement = null;

                for (WebElement dateElement : dateElements) {
                    String dateText = dateElement.getAttribute("Value.Value");
                    try {
                        Date parsedDate = dateFormat.parse(dateText);
                        if (latestDate == null || parsedDate.after(latestDate)) {
                            latestDate = parsedDate;
                            latestDateElement = dateElement;
                        }
                    } catch (java.text.ParseException e) {
                        System.out.println("Error parsing date: " + dateText);
                    }
                }

                if (latestDateElement != null) {
                    latestDateElement.click(); // Click on the row with the latest date
                    rootdriver.findElementByXPath("//Button[@Name='Ok']").click(); // Click OK
                }
                break;
            }
        }
    }

    public void holdTransaction() throws Exception {
        navigateToSalesEnquiryMenu();
        Thread.sleep(1500);
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
//        selectAndValidateData(common.getData(dataFile, "branch"), "xpath", "//Edit[@Name='Branch *']");
        common.clickElement("xpath", "//Edit[@Name='Trans Currency *']");
//        selectAndValidateData(common.getData(dataFile, "transaction"), "xpath", "//Edit[@Name='Trans Currency *']");
        common.clickElement("xpath", "//Edit[@Name='Party Code']");
//        selectAndValidateDataNew(common.getData(dataFile, "partyCode"), "xpath", "//Edit[@Name='Party Code']");
        common.clickElement("xpath", "//Edit[@Name='Party Account *']");
        Thread.sleep(1500);
        common.clickElement("xpath", "//Edit[@Name='Price List']");
//        selectAndValidateData(common.getData(dataFile, "priceList"), "xpath", "//Edit[@Name='Price List']");
        common.clickElement("xpath", "//Edit[@Name='Executive *']");
//        selectAndValidateData(common.getData(dataFile, "executive"), "xpath", "//Edit[@Name='Executive *']");
        common.clickElement("xpath", "//Edit[@Name='Remarks']");
//        selectOptionalMaster(common.getData(dataFile, "remarks"), "xpath", "//Edit[@Name='Remarks']");
        //items
        enterDataAndValidate("xpath", "//Edit[@Name='Product Code Row 0, Not sorted.']", dataFile,dataset, "draftProduct");
//        enterData("xpath", "//Edit[@Name='Quantity * Row 0, Not sorted.']", dataFile, "draftQuantity");
        //save as hold
        common.clickElement("xpath", "//ToolBar/Button[@Name='Tools']");
        common.clickElement("xpath", "//Button[@Name='Hold']");
        common.clickElement("xpath", "//Button[@Name='Save']");
        common.inputText("xpath", "//Document[@Name='RichEdit Control']", "Holding Transaction");
        common.clickElement("xpath", "//Button[@Name='Ok']");
        common.clickElement("xpath", "//Window[@Name='Transaction saved.']/Button[@Name='OK']");
        //open Hold
        common.clickElement("xpath", "//ToolBar/Button[@Name='Tools']");
        common.clickElement("xpath", "//Button[@Name='Hold']");
        common.clickElement("xpath", "//Button[@Name='Open']");

        // Go to Root and navigate to Open Transaction Window
        DesiredCapabilities rootcapabilities = new DesiredCapabilities();
        rootcapabilities.setCapability("app", "Root");
        rootcapabilities.setCapability("deviceName", "WindowsPC");
        rootdriver = new WindowsDriver<>(new URL("http://127.0.0.1:4723/"), rootcapabilities);
        List<WebElement> panes = rootdriver.findElements(By.tagName("Window"));
        for (WebElement i : panes) {
            String name = i.getAttribute("Name");
            System.out.println("Name:- " + name);
            if (name.equals("Open Hold Transaction")) {
                // Find all DateOfSaving rows
                List<WebElement> dateElements = rootdriver.findElementsByXPath("//DataItem[contains(@Name, 'DateOfSaving row')]");
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                Date latestDate = null;
                WebElement latestDateElement = null;

                for (WebElement dateElement : dateElements) {
                    String dateText = dateElement.getAttribute("Value.Value"); // Extract date text
                    try {
                        Date parsedDate = dateFormat.parse(dateText); // Convert to Date object

                        if (latestDate == null || parsedDate.after(latestDate)) {
                            latestDate = parsedDate;
                            latestDateElement = dateElement;
                        }
                    } catch (java.text.ParseException e) {
                        System.out.println("Error parsing date: " + dateText);
                    }
                }

                if (latestDateElement != null) {
                    latestDateElement.click(); // Click on the row with the latest date
                    rootdriver.findElementByXPath("//Button[@Name='Ok']").click(); // Click OK
                }
                break;
            }
        }
    }

    public void saveAsTemplate() throws InterruptedException, IOException, ParseException {
        navigateToSalesEnquiryMenu();
        Thread.sleep(1500);
        common.clickElement("xpath", "//Text[@Name='Remarks']");
        WebElement remarksButton = common.findWebElement("xpath", "//Text[@Name='Remarks']");
        Actions actions = new Actions(driver);
        actions.contextClick(remarksButton).sendKeys(Keys.DOWN, Keys.ENTER).perform();
        Thread.sleep(1500);
        common.clickElement("xpath", "//Window[@Name='Configure Field']/Tab/Pane/Pane/Text[@Name='Hide']/following-sibling::CheckBox");
        common.clickElement("xpath", "//Button[@Name='Ok']");
        Thread.sleep(1500);
        closeTransaction("Sales Enquiries");
        refresh();
        Thread.sleep(3000);
        navigateToSalesEnquiryMenu();
        List<WebElement> remarks = common.findWebElements("xpath", "//Text[@Name='Remarks']");
        if (!remarks.isEmpty()) Assert.fail("Element is not hidden.Pls check again");  //if the element is found, fail
        else System.out.println("Element is not found. Hidden Successfully");
        //save as template
        common.clickElement("xpath", "//ToolBar/Button[@Name='Tools']");
        common.clickElement("xpath", "//Button[@Name='Save Template']");
        common.inputText("xpath", "//Edit[@Name='Enter VoucherSeries']", "Template");
        common.inputText("xpath", "//Edit[@Name='Enter VoucherNo']", "2123");
        common.clickElement("xpath", "//Button[@Name='Ok']");
        common.clickElement("xpath", "//Window[@Name='Transaction saved.']/Button[@Name='OK']");
        //open template
        common.clickElement("xpath", "//MenuItem[@Name='Tools']");
        common.clickElement("xpath", "//MenuItem[@Name='Vouchers']");
        common.clickElement("xpath", "//MenuItem[@Name='Open Template Transaction']");
        Thread.sleep(3000);
        //go to driver and navigate to select template window
        WindowsDriver root = common.initializeDriver("Root");
        WebElement login = root.findElement(By.name("Select Template"));

        List<WebElement> dateElements = root.findElementsByXPath("//DataItem[contains(@Name, 'DateOfSaving row')]");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date latestDate = null;
        WebElement latestDateElement = null;

        for (WebElement dateElement : dateElements) {
            String dateText = dateElement.getAttribute("Value.Value"); // Extract date text
            try {
                Date parsedDate = dateFormat.parse(dateText); // Convert to Date object
                if (latestDate == null || parsedDate.after(latestDate)) {
                    latestDate = parsedDate;
                    latestDateElement = dateElement;
                }
            } catch (java.text.ParseException e) {
                System.out.println("Error parsing date: " + dateText);
            }
        }
        if (latestDateElement != null) {
            latestDateElement.click(); // Click on the row with the latest date
            root.findElementByXPath("//Button[@Name='Ok']").click(); // Click OK
        }
        //unhide remarks again
        Thread.sleep(1500);
        closeTransaction("Sales Enquiries");
        Thread.sleep(1500);
        navigateToSalesEnquiryMenu();

        common.clickElement("xpath", "//Text[@Name='Shipping Address']");
        WebElement shippingAddress = common.findWebElement("xpath", "//Text[@Name='Shipping Address']");
        actions=new Actions(root);
        actions.contextClick(shippingAddress).sendKeys(Keys.DOWN,Keys.DOWN, Keys.ENTER).perform();
        driver.getKeyboard().sendKeys(Keys.TAB,Keys.TAB,Keys.TAB,Keys.SPACE,Keys.ENTER,Keys.ENTER);

        Thread.sleep(2000);
        closeTransaction("Sales Enquiries");
        //verify if it is unhidden or not
        navigateToSalesEnquiryMenu();
        List<WebElement> remarksText = common.findWebElements("xpath", "//Text[@Name='Remarks']");
        if (remarksText.isEmpty()) Assert.fail("Element is not unhidden.Pls check again");  //if the element is not found, fail
        else System.out.println("Element is unhidden Successfully");

    }

    public void editTransaction() throws Exception {
        navigateToSalesEnquiryMenu();
        Thread.sleep(1500);
//        enterBranch(dataFile,dataset,"branch");
//        enterPartyCode(dataFile,dataset, "partyCode");
        gstTransactionType(common.getData(dataFile,dataset,"gstType"));
        enterPriceList(dataFile, dataset,"priceList");
        //enter details
        enterData("xpath", "//Edit[@Name='Product Code Row 0, Not sorted.']", dataFile,dataset, "productCode");
        enterData("xpath", "//Edit[@Name='Quantity * Row 0, Not sorted.']", dataFile,dataset, "Quantity");
        enterDataAndValidate("xpath", "//Edit[@Name='HSN Row 0, Not sorted.']", dataFile,dataset, "HSNCode");
        transactionSave();
        String voucherNo=getNewTransactionId().replace(" ","");
        System.out.println(voucherNo);
        navigateToSalesEnquiryReport();
        verifyReport(voucherNo,dataFile,dataset);
        //edit a transaction
        List<WebElement> elementList = common.findWebElements("xpath", "//Table/*[@Name='Data Panel']/ListItem[contains(@Name,'Row')]");
        for (WebElement i : elementList) {
//            System.out.println("nvufihf:" + i.getText());
            if (i.getText().contains(voucherNo)) {
                System.out.println("element present");
                WebElement element = i.findElement(By.xpath("//DataItem[contains(@Name,'Voucher No row')]"));
                element.click();
                Actions actions = new Actions(driver);
                actions.contextClick(element).perform();
                break;
            }
        }
        Thread.sleep(1500);
        common.clickElement("xpath", "//MenuItem[@Name='Edit Transaction']");
        Thread.sleep(5000);
        enterData("xpath", "//Edit[@Name='Quantity * Row 0, Not sorted.']", dataFile,dataset, "editedQuantity");
        //save
        common.clickElement("xpath", "//Button[@Name='Save']");
        common.clickElement("xpath", "//Button[@Name='Yes']");
        Thread.sleep(1500);
        common.clickElement("xpath", "//Button[@Name='Yes']");
        Thread.sleep(1500);
        common.clickElement("xpath", "//Window[@Name='Transaction saved.']/Button[@Name='OK']");
        //refresh
        common.clickElement("xpath","//ToolBar[@Name='Tool Bar']/Button[@Name='Refresh']");
        verifyReport(voucherNo,dataFile,"afterEditing");
    }



    public void addToFavourites(String transactionName) throws InterruptedException, IOException, ParseException {
        navigateToSalesEnquiryMenu();
        Thread.sleep(5000);
        common.clickElement("xpath","//ToolBar/Button[@Name='Tools']");
        common.clickElement("xpath","//Button[@Name='Add To Favourites']");
        Thread.sleep(1500);
        String myText=common.getText("xpath","//Window[starts-with(@Name,'Wings Finance - PRO')]/*/Text");
        if (myText.contains(common.getData(dataFile,"addToFavorites","alreadyExists"))){
            System.out.println("Item is already in favorites");
            common.clickElement("xpath","//Button[@Name='OK']");
        } else if (myText.contains(common.getData(dataFile,"addToFavorites","newlyAdded"))) {
            System.out.println("Item is successfully added to favorites");
            common.clickElement("xpath","//Button[@Name='OK']");
        }
        else{
            Assert.fail("Something Went wrong. Please try again");
        }
        //verify favourites
        Thread.sleep(1500);
        common.clickElement("xpath","//TabItem[@Name='My Page']");
        Thread.sleep(2000);
        //fetch list
        List<WebElement> favouritesList = common.findWebElements("xpath", "//Pane/Pane[@Name='Apps']/Pane[@Name='Apps']/*");
        boolean notFound=false;
        for (WebElement list:favouritesList) {
            if (list.getText().equals(transactionName)) {
                System.out.println("Screen is added to favourites successfully");
                notFound = true;
                break;
            }
        }
        if (!notFound) Assert.fail("Screen is not added to favourites");
        //remove from favourites
        WebElement clickLink=common.findWebElement("xpath","//Pane[@Name='Sales Enquiries']/Text[@Name='Sales Enquiries']/*[@Name='Sales Enquiries']");
        Actions actions=new Actions(driver);
        actions.contextClick(clickLink).perform();
        actions.contextClick(clickLink).perform();
        common.clickElement("xpath","//MenuItem[@Name='Remove "+ transactionName+"']");
        //verify
        boolean found=true;
        List<WebElement> removedList = common.findWebElements("xpath", "//Pane[@Name='Apps']/Pane[@Name='Apps']/Pane[@Name='Apps']/*");
        for (WebElement list: removedList) {
            if (list.getText().equals(transactionName)) {
                found=false;
                Assert.fail("Not Removed Successfully");
            }
        }
        if (found) System.out.println("Removed From Favourites successfully");
    }

    public void voidTransaction() throws Exception {
        common.clickElement("xpath", "//MenuItem[@Name='Tools']");
        common.clickElement("xpath", "//MenuItem[@Name='Vouchers']");
        common.clickElement("xpath", "//MenuItem[@Name='Void']");
        //enter series num
        System.out.println("capabilities");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("app", "Root");
        rootdriver = new WindowsDriver<>(new URL("http://127.0.0.1:4723/"), capabilities);
        Thread.sleep(5000);
        List<WebElement> panes = By.tagName("Window").findElements(rootdriver);
        System.out.println("outside for");
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
                if (name.equals("Void Transaction")) {
                    Thread.sleep(5000);
                    System.out.println("inside if");
                    WebElement series = rootdriver.findElementByXPath("//Pane/Edit[@Name='Document Series']");
                    series.sendKeys("PV");
                    WebElement number = rootdriver.findElementByXPath("//Pane/Edit[@Name='Document No']");
                    number.sendKeys("5");
                    rootdriver.findElementByXPath("//Button[@Name='Void']").click();
                    WebDriverWait wait = new WebDriverWait(rootdriver, 30);
                    WebElement yesButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//Button[@Name='Yes']")));
                    yesButton.click();
                    WebElement okButton=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//Button[@Name='OK']")));
                    okButton.click();
                    try {
                        WebElement voidButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//Button[@Name='Void']")));
                        if (voidButton.isDisplayed()) {
                            System.out.println("Void is displayed");
                            WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//TitleBar/Button[@Name='Close']")));
                            closeButton.click();
                            break;
                        } else {
                            System.out.println("Void button is not present or not displayed. Skipping.");
                        }
                    }catch (TimeoutException e) {
                        // If the button is not found within the timeout, skip without throwing an exception
                        System.out.println("Void button not found within the timeout. Skipping.");
                        break;
                    }
                }
            }
            rootdriver.findElementByXPath("//MenuItem[@Name='Audit']").click();
            rootdriver.findElementByXPath("//Menu/MenuItem[@Name='Deleted Transactiones']").click();
            Thread.sleep(2500);
            rootdriver.findElementByXPath("//Button[@Name='Submit']").click();
            List<WebElement> listElements = common.findWebElements("xpath", "//Table/*[@Name='Data Panel']/ListItem[contains(@Name,'Row')]/DataItem[contains(@Name,'Voucher No row')]");
            System.out.println("Size of elements under List: " + listElements.size());
            boolean isTransactionFound=false;
            for (int i = 0; i < listElements.size(); i++) {
                WebElement element = listElements.get(i);
                String transactionText = element.getText();
                System.out.println("Voucher Num: " + transactionText);
                if (transactionText.equals("PV 5")) {
                    System.out.println("Transaction voided Successfully");
                    isTransactionFound = true;
                    break;
                }
            }
            if(!isTransactionFound){
                System.out.println("No Transactions Found");
            }
        }
    }

}