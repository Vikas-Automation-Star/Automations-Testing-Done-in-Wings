package mobileTesing;

import com.wings.utils.Time;
import org.json.simple.parser.ParseException;
import org.kie.api.builder.KieFileSystem;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.io.IOException;
import java.util.List;

public class SalesInvoiceMobile extends BaseHelper {
    String dataFile;

    public SalesInvoiceMobile(WebDriver driver, String file) {
        super(driver);
        dataFile = file;
    }

    public void salesInvoiceMobile() throws InterruptedException, IOException, ParseException {
        findWebElementWithTime("xpath", "//*[text()='Explorer']", 10).click();
        Thread.sleep(3000);
        WebElement element = driver.findElement(By.xpath("(//ion-label[@class='sc-ion-label-ios-h sc-ion-label-ios-s ios'][normalize-space()='Sales Invoices'])[2]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        findWebElementInMobile("xpath", "(//ion-label[@class='sc-ion-label-ios-h sc-ion-label-ios-s ios'][normalize-space()='Sales Invoices'])[2]").click();

        //genInfo
        Thread.sleep(1000);
        findWebElementWithTime("xpath", "(//ion-button[@class='ios button button-small button-clear ion-activatable ion-focusable'][normalize-space()='Edit'])[1]", 10).click();
        Thread.sleep(2000);
        inputText("xpath", "//*[@placeholder='Enter Date']", Time.getFormattedFutureDate(0));
        findWebElementWithTime("xpath", "//*[@placeholder='Select Branch']", 10).click();
        findWebElementWithTime("xpath", "//*[text()='AT_Branch 1_Reg']", 10).click();
        findWebElementWithTime("xpath", "//*[@placeholder='Select Location']", 10).click();
        Thread.sleep(500);
        WebElement clickHold = findWebElementInMobile("xpath", "//*[@placeholder='Search Location']");
        Actions actions = new Actions(driver);
        actions.clickAndHold(clickHold).moveByOffset(0, -200).release().perform();
        findWebElementWithTime("xpath", "//*[text()='AT_Branch 1_Location 1']", 10).click();
        //create party if required
        findWebElementWithTime("xpath", "//*[text()='Create Party ']", 10).click();
        Thread.sleep(1000);
        findWebElementInMobile("xpath", "//*[@placeholder='Customer Type']").click();
        findWebElementInMobile("xpath", "//div[text()=' AT_Type of Customer 1_Regular ']").click();
        List<WebElement> okButtons = driver.findElements(By.xpath("//button[.//span[normalize-space()='OK']]"));
        for (WebElement btn : okButtons) {
            if (btn.isDisplayed()) {
                btn.click();
                break;
            }
        }
        findWebElementInMobile("xpath", "//*[@placeholder='Master Node']").click();
        findWebElementInMobile("xpath", "//div[text()=' All Customers ']").click();
        List<WebElement> okButtons1 = driver.findElements(By.xpath("//button[.//span[normalize-space()='OK']]"));
        for (WebElement btn : okButtons1) {
            if (btn.isDisplayed()) {
                btn.click();
                break; // usually only one OK button
            }
        }
        findWebElementInMobile("xpath", "//*[@placeholder='Master Group']").click();
        findWebElementInMobile("xpath", "//div[text()=' Customers ']").click();
        List<WebElement> okButtons3 = driver.findElements(By.xpath("//button[.//span[normalize-space()='OK']]"));
        for (WebElement btn : okButtons3) {
            if (btn.isDisplayed()) {
                btn.click();
                break; // usually only one OK button
            }
        }
        inputText("xpath", "//input[contains(@placeholder,'Enter Customer Name')]", getData(dataFile, "createParty", "customerName"));
        findWebElementInMobile("xpath", "//*[@ng-reflect-placeholder='Assessee Type']").click();
        findWebElementInMobile("xpath", "//div[text()=' Registered Dealer ']").click();
        List<WebElement> okButtons4 = driver.findElements(By.xpath("//button[.//span[normalize-space()='OK']]"));
        for (WebElement btn : okButtons4) {
            if (btn.isDisplayed()) {
                btn.click();
                break; // usually only one OK button
            }
        }
        inputText("xpath", "//input[contains(@placeholder,'GSTIN')]", getData(dataFile, "createParty", "gstin"));
        Thread.sleep(1000);
        findWebElementInMobile("xpath", "//*[text()=' Verify GSTIN ']").click();
        Thread.sleep(1500);
        WebElement email = driver.findElement(By.xpath("(//ion-card-title[normalize-space()='GST Contact Details'])[1]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", email);
        inputText("xpath", "//input[contains(@placeholder,'Mobile No')]", getData(dataFile, "createParty", "MobileNo"));
        inputText("xpath", "//input[contains(@placeholder,'Fax')]", getData(dataFile, "createParty", "Fax"));
        inputText("xpath", "//input[contains(@placeholder,'Email')]", getData(dataFile, "createParty", "Email"));
        inputText("xpath", "//input[contains(@placeholder,'Website')]", getData(dataFile, "createParty", "Website"));
        inputText("xpath", "//input[contains(@placeholder,'Contact Person Name')]", getData(dataFile, "createParty", "ContactPersonName"));
        inputText("xpath", "//input[contains(@placeholder,'Contact Person Designation')]", getData(dataFile, "createParty", "ContactPersonDesignation"));
        inputText("xpath", "//input[contains(@placeholder,'Contact Person Mobile No')]", getData(dataFile, "createParty", "ContactPersonMobileNo"));
        inputText("xpath", "//input[contains(@placeholder,'Contact Person Email')]", getData(dataFile, "createParty", "ContactPersonEmail"));
        Thread.sleep(1000);
        WebElement shipping = driver.findElement(By.xpath("(//ion-card-title[normalize-space()='Shipping Address'])[1]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", shipping);
        inputText("xpath", "//input[contains(@placeholder,'Address Name')]", getData(dataFile, "createParty", "AddressName"));
        WebElement gstin=driver.findElement(By.xpath("//input[contains(@placeholder,'Address Name')]"));
        gstin.sendKeys(Keys.TAB,getData(dataFile, "createParty", "gstin"),Keys.TAB,getData(dataFile, "createParty", "City"),Keys.TAB,Keys.ENTER,Keys.DOWN,Keys.DOWN,Keys.DOWN,Keys.DOWN);
        List<WebElement> okButtons5 = driver.findElements(By.xpath("//button[.//span[normalize-space()='OK']]"));
        for (WebElement btn : okButtons5) {
            if (btn.isDisplayed()) {
                btn.click();
                break; // usually only one OK button
            }
        }

//        inputText("xpath", "//input[@placeholder='GSTIN']", getData(dataFile, "createParty", "gstin"));
//        inputText("xpath", "//input[contains(@placeholder,'City')]", getData(dataFile, "createParty", "City"));
//        Thread.sleep(1000);
//        findWebElementInMobile("xpath", "//*[text()='State']").click();
        findWebElementInMobile("xpath", "//*[text()=' Andhra Pradesh ']").click();
        inputText("xpath", "//input[contains(@placeholder,'Zip/Postal Code')]", getData(dataFile, "createParty", "Zip/PostalCode"));
        findWebElementInMobile("xpath", "//*[text()='Country']").click();
        inputText("xpath", "//input[contains(@placeholder,'MobileNo')]", getData(dataFile, "createParty", "MobileNo"));

        //

//        findWebElementWithTime("xpath", "//*[@placeholder='Select Cash/Party']", 10).click();
//        Thread.sleep(500);
//        inputText("xpath", "//*[contains(@placeholder,'Search Cash/Party')]", getData(dataFile, "GenInfo", "party"));
//        Thread.sleep(3000);
//        findWebElementInMobile("xpath", "/html[1]/body[1]/app-root[1]/ion-app[1]/ion-modal[2]/div[1]/ion-content[1]/ion-list[1]/ion-item[1]/ion-label[1]").click();
//        findWebElementWithTime("xpath", "//*[text()='Intra State Sales to Registered Dealers']", 10).click();
//        findWebElementWithTime("xpath", "(//button[@type='button'])[11]", 10).click();
//        Thread.sleep(500);
//        inputText("xpath", "//*[contains(@placeholder,'Enter Customer Mobile Number')]", getData(dataFile, "GenInfo", "custMobileNum"));
////        findWebElementWithTime("xpath","//*[@placeholder='Select Sales Account']",10).click();
//        Thread.sleep(500);
//        findWebElementWithTime("xpath", "//*[text()='AT_Sales  Acc 1']", 10).click();
//        findWebElementWithTime("xpath", "//*[@placeholder='Select TCS Trans Nature']", 10).click();
//        findWebElementWithTime("xpath", "//*[text()='AT_TCS Transaction Nature 1']", 10).click();
//        findWebElementWithTime("xpath", "//*[@placeholder='Select Invoice Type']", 10).click();
//        findWebElementWithTime("xpath", "//*[text()='Regular']", 10).click();
//        findWebElementWithTime("xpath", "//*[@placeholder='Select Price List']", 10).click();
//        findWebElementWithTime("xpath", "//*[text()='AT_Exclusive Sales Price List 1']", 10).click();
//        findWebElementWithTime("xpath", "//*[@placeholder='Select Executive']", 10).click();
//        findWebElementWithTime("xpath", "//*[text()='AT_Executive 1']", 10).click();
//        findWebElementWithTime("xpath", "//*[@placeholder='Enter Remarks']", 10).click();
//        inputText("xpath", "//*[@placeholder='Enter Remarks']", getData(dataFile, "GenInfo", "remarks"));
//        findWebElementWithTime("xpath", "//*[text()='Done']", 10).click();
//
//        for (int i = 0; i < Integer.parseInt(getData(dataFile, "items", "itemsSizeCount")); i++) {
//            findWebElementWithTime("xpath", "(//ion-button[@size='small'][normalize-space()='Edit'])[2]", 10).click();
//            Thread.sleep(1000);
//            findWebElementInMobile("xpath", "(//ion-button[normalize-space()='+ New'])[1]").click();
//            Thread.sleep(1000);
//            findWebElementWithTime("xpath", "//*[contains(@placeholder,'Select Product')]", 10).click();
//            Thread.sleep(500);
//            addData("xpath", "(//input[@placeholder='Search Product'])[1]", dataFile, "items", "product", i);
//            Thread.sleep(500);
//            searchAndClick(dataFile, "items", "productPath", i);
//            Thread.sleep(500);
//            findWebElementWithTime("xpath", "//*[@placeholder='Select Storage Bin']", 10).click();
//            findWebElementWithTime("xpath", "//*[text()='AT_Branch 1_Location 1 Default Bin']", 10).click();
//            addData("xpath", "//*[contains(@placeholder,'Enter Quantity')]", dataFile, "items", "quantity", i);
//            addData("xpath", "//*[contains(@placeholder,'Enter Free Quantity')]", dataFile, "items", "freeQuantity", i);
//            addData("xpath", "//*[contains(@placeholder,'Enter No Of Packs')]", dataFile, "items", "numOfPacks", i);
////            Thread.sleep(500);
////            inputText("xpath","//*[@placeholder='Enter Delivery Date']", Time.getFormattedFutureDate(9));
////            Thread.sleep(1000);
////            findWebElementWithTime("xpath","//*[contains(@placeholder,'Enter Minimum Rate')]",5).click();
////            inputText("xpath","//*[contains(@placeholder,'Enter Minimum Rate')]",getData(dataFile,"items","minRate"));
////            Thread.sleep(1000);
////            findWebElementWithTime("xpath","//*[contains(@placeholder,'Enter Maximum Rate')]",5).click();
////            inputText("xpath","//*[contains(@placeholder,'Enter Maximum Rate')]",getData(dataFile,"items","maxRate"));
////            Thread.sleep(1000);
////            findWebElementWithTime("xpath","//*[contains(@placeholder,'Enter Unit Rate')]",5).click();
////            inputText("xpath","//*[contains(@placeholder,'Enter Unit Rate')]",getData(dataFile,"items","unitRate"));
//            Thread.sleep(500);
//            WebElement comments3 = driver.findElement(By.xpath("//*[contains(@placeholder,'Enter Comments')]"));
//            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", comments3);
//            inputText("xpath", "//*[contains(@placeholder,'Enter Comments')]", getData(dataFile, "items", "comments"));
//            Thread.sleep(2000);
//            findWebElementInMobile("cssselector", "body > app-root:nth-child(1) > ion-app:nth-child(1) > ion-modal:nth-child(3) > div:nth-child(1) > ion-footer:nth-child(3) > ion-toolbar:nth-child(1) > ion-button:nth-child(1)").click();
//            findWebElementWithTime("xpath", "//*[text()='Done']", 10).click();
//        }
//
//        //Charges And Deduction
//        for (int i = 0; i < Integer.parseInt(getData(dataFile, "chargesAndDeduction", "chargesAndDeductionCount")); i++) {
//            Thread.sleep(1000);
//            findWebElementWithTime("xpath", "(//ion-button[@class='ios button button-small button-clear ion-activatable ion-focusable'][normalize-space()='Edit'])[3]", 10).click();
//            Thread.sleep(1000);
//            findWebElementInMobile("xpath", "(//ion-button[normalize-space()='+ New'])[1]").click();
//            Thread.sleep(1000);
//            findWebElementInMobile("xpath", "//*[contains(@placeholder,'Select Charges Or Deductions')]").click();
//            Thread.sleep(1000);
//            findWebElementWithTime("xpath", "//*[contains(@placeholder,'Search Charges Or Deductions')]", 10).click();
//            addData("xpath", "//*[contains(@placeholder,'Search Charges Or Deductions')]", dataFile, "chargesAndDeduction", "chargesOrDeductions", i);
//            Thread.sleep(2000);
//            findWebElementInMobile("cssselector", "body > app-root:nth-child(1) > ion-app:nth-child(1) > ion-modal:nth-child(4) > div:nth-child(1) > ion-content:nth-child(2) > ion-list:nth-child(2) > ion-item:nth-child(1) > ion-label:nth-child(1)").click();
////            searchAndClick(dataFile,"chargesAndDeduction","chargesOrDeductionsPath",i);
//            findWebElementWithTime("xpath", "//*[contains(@placeholder,'Select Account')]", 10).click();
//            findWebElementWithTime("xpath", "//*[text()='AT_Charges  Acc 1']", 10).click();
//            Thread.sleep(1000);
//            addData("xpath", "//*[contains(@placeholder,'Enter Amount')]", dataFile, "chargesAndDeduction", "chargesAndDeductionAmount", i);
//            inputText("xpath", "//*[contains(@placeholder,'Enter Comments')]", getData(dataFile, "chargesAndDeduction", "comments"));
//            Thread.sleep(3000);
//            findWebElementInMobile("xpath", "(//ion-button[@class='ion-color ion-color-wings ios button button-block button-solid in-toolbar ion-activatable ion-focusable'][normalize-space()='Done'])[2]").click();
//            findWebElementWithTime("xpath", "//*[text()='Done']", 10).click();
//        }
//
//        //Other charges
//        WebElement cashTab = driver.findElement(By.xpath("(//ion-button[@class='ios button button-small button-clear ion-activatable ion-focusable'][normalize-space()='Edit'])[6]"));
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cashTab);
//        for (int i = 0; i < Integer.parseInt(getData(dataFile, "otherCharges", "otherChargesCount")); i++) {
//            findWebElementWithTime("xpath", "(//ion-button[@class='ios button button-small button-clear ion-activatable ion-focusable'][normalize-space()='Edit'])[4]", 10).click();
//            Thread.sleep(1000);
//            findWebElementInMobile("xpath", "(//ion-button[normalize-space()='+ New'])[1]").click();
//            Thread.sleep(1000);
//            findWebElementWithTime("xpath", "//*[contains(@placeholder,'Select Account')]", 10).click();
//            findWebElementWithTime("xpath", "//*[text()='AT_Charges  Acc 1']", 10).click();
////            findWebElementWithTime("xpath","//*[text()='Inclusive Tax']",10).click();
//            Thread.sleep(1000);
//            inputText("xpath", "//*[contains(@placeholder,'Enter Amount')]", getData(dataFile, "otherCharges", "otherChargesAmount"));
//            WebElement comments1 = driver.findElement(By.xpath("//*[contains(@placeholder,'Enter Comments')]"));
//            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", comments1);
//            inputText("xpath", "//*[contains(@placeholder,'Enter Comments')]", getData(dataFile, "otherCharges", "comments"));
//            Thread.sleep(1500);
//            findWebElementInMobile("xpath", "(//ion-button[@class='ion-color ion-color-wings ios button button-block button-solid in-toolbar ion-activatable ion-focusable'][normalize-space()='Done'])[2]").click();
//            findWebElementWithTime("xpath", "//*[text()='Done']", 10).click();
//        }
//
////        Tcs
////        WebElement summary = driver.findElement(By.xpath("(//ion-button[@class='ios button button-small button-clear ion-activatable ion-focusable'][normalize-space()='Edit'])[13]"));
////        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", summary);
////        findWebElementWithTime("xpath", "(//ion-button[@class='ios button button-small button-clear ion-activatable ion-focusable'][normalize-space()='Edit'])[5]", 10).click();
////        findWebElementInMobile("xpath", "(//ion-button[@class='ion-color ion-color-wings ios button button-block button-solid in-toolbar ion-activatable ion-focusable'][normalize-space()='Done'])[1]").click();
//
//        //Cash
//        Thread.sleep(1000);
//        for (int i = 0; i < Integer.parseInt(getData(dataFile, "Cash", "cashCount")); i++) {
//            WebElement cashTab2= driver.findElement(By.xpath("(//ion-button[@class='ios button button-small button-clear ion-activatable ion-focusable'][normalize-space()='Edit'])[5]"));
//            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cashTab2);
//            Thread.sleep(1000);
//            findWebElementWithTime("xpath", "(//ion-button[@class='ios button button-small button-clear ion-activatable ion-focusable'][normalize-space()='Edit'])[6]", 10).click();
//            Thread.sleep(1000);
//            findWebElementInMobile("xpath", "(//ion-button[normalize-space()='+ New'])[1]").click();
//            Thread.sleep(1000);
//            findWebElementInMobile("xpath", "//*[contains(@placeholder,'Select Cash Account')]").click();
//            findWebElementWithTime("xpath", "//*[text()='AT_Cash Acc 1']", 10).click();
//            Thread.sleep(1000);
//            inputText("xpath", "//*[contains(@placeholder,'Enter Amount')]", getData(dataFile, "Cash", "cashAmount"));
//            inputText("xpath", "//*[contains(@placeholder,'Enter Comments')]", getData(dataFile, "Cash", "comments"));
//            Thread.sleep(2000);
//            findWebElementInMobile("cssselector", "body > app-root:nth-child(1) > ion-app:nth-child(1) > ion-modal:nth-child(3) > div:nth-child(1) > ion-footer:nth-child(3) > ion-toolbar:nth-child(1) > ion-button:nth-child(1)").click();
//            findWebElementWithTime("xpath", "//*[text()='Done']", 10).click();
//        }
//
//        //Cheques
//        for (int i = 0; i < Integer.parseInt(getData(dataFile, "Cheques", "chequesCount")); i++) {
//            WebElement summaryy = driver.findElement(By.xpath("(//ion-button[@class='ios button button-small button-clear ion-activatable ion-focusable'][normalize-space()='Edit'])[6]"));
//            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", summaryy);
//            findWebElementWithTime("xpath","(//ion-button[@class='ios button button-small button-clear ion-activatable ion-focusable'][normalize-space()='Edit'])[7]",10).click();
//            Thread.sleep(1000);
//            findWebElementInMobile("xpath","(//ion-button[normalize-space()='+ New'])[1]").click();
//            Thread.sleep(1000);
//            findWebElementInMobile("xpath","//*[contains(@placeholder,'Select Bank Account')]").click();
//            findWebElementWithTime("xpath","//*[text()='AT_Bank Acc 1']",10).click();
//            Thread.sleep(1000);
//            inputText("xpath","//*[contains(@placeholder,'Enter Amount')]",getData(dataFile,"Cheques","chequesAmount"));
//            findWebElementWithTime("xpath","//*[@placeholder='Enter Cheque/EFT No']",10).click();
//            inputText("xpath","//*[@placeholder='Enter Cheque/EFT No']",getData(dataFile,"Cheques","chequesEFT/No"));
//            inputText("xpath","//*[contains(@placeholder,'Enter Cheque Date')]",getData(dataFile,"Cheques","chequesDate"));
//            findWebElementWithTime("xpath","//*[contains(@placeholder,'Select Drawn On Bank')]",10).click();
//            findWebElementWithTime("xpath","//*[text()='AT_Drawn On Bank 1']",10).click();
//            Thread.sleep(500);
//            findWebElementInMobile("xpath","//*[contains(@placeholder,'Select Charges Account')]").click();
//            Thread.sleep(500);
//            driver.findElement(By.xpath("//*[text()='AT_Charges  Acc 1']")).click();
//            Thread.sleep(1000);
//            inputText("xpath","//*[contains(@placeholder,'Enter Charges')]",getData(dataFile,"Cheques","chequesCharges"));
//            inputText("xpath","//*[contains(@placeholder,'Enter Comments')]",getData(dataFile,"Cheques","chequesComments"));
//            findWebElementWithTime("cssselector","body > app-root:nth-child(1) > ion-app:nth-child(1) > ion-modal:nth-child(3) > div:nth-child(1) > ion-footer:nth-child(3) > ion-toolbar:nth-child(1) > ion-button:nth-child(1)",10).click();
//            findWebElementWithTime("xpath","//ion-button[@class='ion-color ion-color-wings ios button button-block button-solid in-toolbar ion-activatable ion-focusable']",10).click();
//        }
//
////        //PostDatedCheques
//        for (int i = 0; i < Integer.parseInt(getData(dataFile, "PostDatedCheque", "PostDatedChequeCount")); i++) {
//            Thread.sleep(2000);
//            findWebElementInMobile("xpath","(//ion-button[@class='ios button button-small button-clear ion-activatable ion-focusable'][normalize-space()='Edit'])[8]").click();
//            Thread.sleep(1000);
//            findWebElementInMobile("xpath","(//ion-button[normalize-space()='+ New'])[1]").click();
//            Thread.sleep(1000);
//            findWebElementWithTime("xpath","//*[contains(@placeholder,'Select PDC Account')]",10).click();
//            findWebElementWithTime("xpath","//*[text()='AT_Cheques In Hand Acc 1']",10).click();
//            Thread.sleep(1000);
//            inputText("xpath","//*[@placeholder='Enter Amount']",getData(dataFile,"PostDatedCheque","postDCAmount"));
//            findWebElementWithTime("xpath","//*[contains(@placeholder,'Enter Cheque/EFT No')]",10).click();
//            inputText("xpath","//*[contains(@placeholder,'Enter Cheque/EFT No')]",getData(dataFile,"PostDatedCheque","postDCChequesEFT/No"));
//            inputText("xpath","//*[contains(@placeholder,'Enter Cheque Date')]",getData(dataFile,"PostDatedCheque","postDChequesDate"));
//            findWebElementWithTime("xpath","//*[contains(@placeholder,'Select Drawn On Bank')]",10).click();
//            findWebElementWithTime("xpath","//*[text()='AT_Drawn On Bank 1']",10).click();
//            Thread.sleep(1000);
//            inputText("xpath","//*[contains(@placeholder,'Enter Comments')]",getData(dataFile,"PostDatedCheque","postDCChequesComments"));
//            findWebElementWithTime("cssselector","body > app-root:nth-child(1) > ion-app:nth-child(1) > ion-modal:nth-child(3) > div:nth-child(1) > ion-footer:nth-child(3) > ion-toolbar:nth-child(1) > ion-button:nth-child(1)",10).click();
//            findWebElementWithTime("xpath","//ion-button[@class='ion-color ion-color-wings ios button button-block button-solid in-toolbar ion-activatable ion-focusable']",10).click();
//        }
//
////        //Cheques[PDC]
//        for (int i = 0; i < Integer.parseInt(getData(dataFile, "cheque[PDC]", "Cheques[PDC]Count")); i++) {
//            Thread.sleep(1000);
//            WebElement summaryyy = driver.findElement(By.xpath("(//ion-button[@class='ios button button-small button-clear ion-activatable ion-focusable'][normalize-space()='Edit'])[13]"));
//            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", summaryyy);
//            Thread.sleep(1500);
//            findWebElementInMobile("xpath","(//ion-button[@class='ios button button-small button-clear ion-activatable ion-focusable'][normalize-space()='Edit'])[9]").click();
//            Thread.sleep(1000);
//            findWebElementInMobile("xpath","(//ion-button[normalize-space()='+ New'])[1]").click();
//            Thread.sleep(1000);
//            findWebElementWithTime("xpath","//*[contains(@placeholder,'Select Bank Account')]",10).click();
//            findWebElementWithTime("xpath","//*[text()='AT_Bank_PDC_Acc 1']",10).click();
//            Thread.sleep(1000);
//            inputText("xpath","//*[@placeholder='Enter Amount']",getData(dataFile,"cheque[PDC]","PDCAmount"));
//            findWebElementWithTime("xpath","//*[@placeholder='Enter Cheque/EFT No']",10).click();
//            inputText("xpath","//*[@placeholder='Enter Cheque/EFT No']",getData(dataFile,"cheque[PDC]","PDCChequesEFT/No"));
//            inputText("xpath","//*[contains(@placeholder,'Enter Cheque Date')]",getData(dataFile,"cheque[PDC]","PDChequesDate"));
//            findWebElementWithTime("xpath","//*[contains(@placeholder,'Select Drawn On Bank')]",10).click();
//            Thread.sleep(1000);
//            findWebElementWithTime("xpath","//*[text()='AT_Drawn On Bank 1']",10).click();
//            Thread.sleep(1000);
//            inputText("xpath","//*[contains(@placeholder,'Enter Comments')]",getData(dataFile,"cheque[PDC]","PDCChequesComments"));
//            Thread.sleep(1000);
//            findWebElementWithTime("cssselector","body > app-root:nth-child(1) > ion-app:nth-child(1) > ion-modal:nth-child(3) > div:nth-child(1) > ion-footer:nth-child(3) > ion-toolbar:nth-child(1) > ion-button:nth-child(1)",10).click();
//            findWebElementWithTime("xpath","//ion-button[@class='ion-color ion-color-wings ios button button-block button-solid in-toolbar ion-activatable ion-focusable']",10).click();
//        }
//
////        //CreditCard
//        for (int i = 0; i < Integer.parseInt(getData(dataFile, "creditCard", "CreditCardCount")); i++) {
//            WebElement summary1= driver.findElement(By.xpath("(//ion-button[@class='ios button button-small button-clear ion-activatable ion-focusable'][normalize-space()='Edit'])[13]"));
//            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", summary1);
//            findWebElementWithTime("xpath","(//ion-button[@class='ios button button-small button-clear ion-activatable ion-focusable'][normalize-space()='Edit'])[10]",10).click();
//            Thread.sleep(1000);
//            findWebElementInMobile("xpath","(//ion-button[normalize-space()='+ New'])[1]").click();
//            Thread.sleep(1000);
//            findWebElementWithTime("xpath","//*[contains(@placeholder,'Select Swipe Machine Type')]",10).click();
//            findWebElementWithTime("xpath","//*[text()='AT_Swipe Machine Type 2']",10).click();
//            findWebElementWithTime("xpath","//*[contains(@placeholder,'Select Swipe Type')]",10).click();
//            findWebElementWithTime("xpath","//*[text()='AT_Swipe Type 2']",10).click();
//            Thread.sleep(1000);
//            inputText("xpath","//*[contains(@placeholder,'Enter Amount')]",getData(dataFile,"creditCard","cardAmount"));
//            Thread.sleep(500);
//            inputText("xpath","//input[@placeholder='Enter Card No']",getData(dataFile,"creditCard","cardNumber"));
//            Thread.sleep(500);
//            inputText("xpath","//*[@placeholder='Enter Expiry Date']", Time.getFormattedFutureDate(10));
//            Thread.sleep(500);
//            inputText("xpath","//*[contains(@placeholder,'Enter Approval No')]",getData(dataFile,"creditCard","cardApprovalNumber"));
//            Thread.sleep(500);
//            inputText("xpath","//*[contains(@placeholder,'Enter Comments')]",getData(dataFile,"creditCard","creditCardComments"));
//            findWebElementWithTime("cssselector","body > app-root:nth-child(1) > ion-app:nth-child(1) > ion-modal:nth-child(3) > div:nth-child(1) > ion-footer:nth-child(3) > ion-toolbar:nth-child(1) > ion-button:nth-child(1)",10).click();
//            findWebElementWithTime("xpath","//ion-button[@class='ion-color ion-color-wings ios button button-block button-solid in-toolbar ion-activatable ion-focusable']",10).click();
//        }
//
////        //E-Way bill
////        findWebElementWithTime("xpath","(//ion-button[@class='ios button button-small button-clear ion-activatable ion-focusable'][normalize-space()='Edit'])[11]",10).click();
//
////        //E-Invoice
////        findWebElementWithTime("xpath","(//ion-button[@class='ios button button-small button-clear ion-activatable ion-focusable'][normalize-space()='Edit'])[12]",10).click();
//
////        //Summary
//        findWebElementWithTime("xpath","(//ion-button[@class='ios button button-small button-clear ion-activatable ion-focusable'][normalize-space()='Edit'])[13]",10).click();
//        findWebElementWithTime("xpath","(//ion-button[@class='ion-color ion-color-wings ios button button-block button-solid in-toolbar ion-activatable ion-focusable'])[1]",10).click();
//        Thread.sleep(1500);
//        findWebElementInMobile("xpath","(//ion-button[@class='ion-color ion-color-wings ios button button-block button-solid in-toolbar in-toolbar-color ion-activatable ion-focusable'])[1]").click();
//        Thread.sleep(5000);
//        WebElement validateTransaction=driver.findElement(By.xpath("(//h2[normalize-space()='Transaction saved.'])[1]"));
//        System.out.println(validateTransaction.getText());
//        if (validateTransaction.getText().equals("Transaction saved.")) {
//            List<WebElement> okays=findWebElementsInMobile("xpath", "//button[.//span[normalize-space()='OK']]", 25);
//            System.out.println(okays.size());
//            okays.get(4).click();
//            System.out.println("Transaction saved And it's clicked successfully");
//        }
//        else {
//            Assert.fail("Transaction not saved, you missed some fields pls check again");
//        }
//
    }
}

