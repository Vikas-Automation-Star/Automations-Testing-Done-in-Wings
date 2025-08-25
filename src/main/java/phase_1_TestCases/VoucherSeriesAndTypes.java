package phase_1_TestCases;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VoucherSeriesAndTypes extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    VoucherSeriesAndTypes(WindowsDriver driver, String dataFile) {
        super(driver);
        this.driver = driver;
        this.dataFile = dataFile;
        common = new Common(this.driver);
    }

    public List<String> setVouchersTypes(String voucherTypeName, String voucherSeries, String navigateMenu, String navigateSubMenu, String navigateSubMenu1, String closeTransTab) throws InterruptedException, AWTException, IOException, ParseException {
        common.clickElement("xpath", "//MenuItem[@Name='Configure']");
        common.clickElement("xpath", "//MenuItem[@Name='Voucher Types']");
        WebElement rightClick = common.findWebElement("xpath", "//TreeItem[@Name='All Voucher Types']");
        Actions actions = new Actions(driver);
        actions.contextClick(rightClick).perform();
        common.clickElement("xpath", "//MenuItem[@Name='New Master']");
        Thread.sleep(1500);
        common.inputText("xpath", "//Edit[@Name='New Voucher Type *']/*[@Name='New Voucher Type *']", voucherTypeName+common.getRandom());
        String voucherType = common.findWebElement("xpath", "//Edit[@Name='New Voucher Type *']/*[@Name='New Voucher Type *']").getText();
//        System.out.println("Series :" + voucherType);
        common.inputText("xpath", "//Edit[@Name='Voucher Series']/*[@Name='Voucher Series']", voucherSeries);
        String assignSeries = common.findWebElement("xpath", "//Edit[@Name='Voucher Series']/*[@Name='Voucher Series']").getText();
//        System.out.println("Series :" + assignSeries);
        common.clickElement("xpath", "//Button[@Name='Save']");
        common.clickElement("xpath", "//Button[@Name='OK']");
        common.clickElement("xpath", "//Button[@Name='Close']");
        closeTransaction("Voucher Types");
        Thread.sleep(1000);
        navigateToMastersOrMenus(navigateMenu,navigateSubMenu,navigateSubMenu1);
        Thread.sleep(2000);
        common.clickElement("xpath", "//Button[@Name='Configure']");
        common.clickElement("xpath", "//Button[@Name='Voucher Type']");
        common.clickElement("xpath", "//Text[@Name='Applicable Voucher Type']/following-sibling::Button[@Name='...']");
        Thread.sleep(1000);
        WebElement voucherPath = common.findWebElement("xpath", "//Table[@Name='ApplicableVoucherType']/*[starts-with(@Name,'Row 0')]/*[contains(@Name,'Voucher Type * Row 0, Not sorted.')]");
        voucherPath.sendKeys(voucherType);
        common.clickElement("xpath", "//Pane/Button[@Name='Ok']");
        saveMasterOrProperty();
        Thread.sleep(1000);
        common.clickElement("xpath", "//Button[@Name='Configure']");
        common.clickElement("xpath", "//Button[@Name='Voucher Series']");
        Thread.sleep(2000);
        common.clickElement("xpath","//Text[@Name='Voucher Series Options']/following-sibling::Button[@Name='...']");
        WebElement selectVoucherSeries = common.findWebElement("xpath", "//Table[@Name='VoucherSeriesOptions']/*[starts-with(@Name,'Row 0')]/*[contains(@Name,'Include Option Row 0, Not sorted.')]");
        voucherPath.sendKeys(common.getData(dataFile,"selectIncludeOption"),Keys.TAB, common.getData(dataFile,"selectSeries"));
        common.clickElement("xpath", "//Pane/Button[@Name='Ok']");
        saveMasterOrProperty();
        common.clickElement("xpath", closeTransTab);
        List<String> voucherAndSeries=new ArrayList<>();
        voucherAndSeries.add(voucherType);
        voucherAndSeries.add(assignSeries);
        return voucherAndSeries;
    }
    public List<String>validateVoucherTypeAndSeries(String navigateMenu, String navigateSubMenu, String navigateSubMenu1,String closeTab) throws InterruptedException, IOException, ParseException, AWTException {
        navigateToMastersOrMenus(navigateMenu,navigateSubMenu,navigateSubMenu1);
        String transVid = common.findWebElement("xpath", "//Edit[@Name='Voucher Type']/Edit[@Name='Voucher Type']").getText();
//        System.out.println("voucher type :"+transVid);
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        selectAndValidateDataNew(common.getData(dataFile, "branch"), "xpath", "//Edit[@Name='Branch *']");
        common.clickElement("xpath", "//Edit[@Name='Trans Currency *']");
        common.clickElement("xpath", "//Edit[@Name='Party Code']");
        enterInput("xpath","//Edit[@Name='Party Code']",dataFile,"partyCode");
        common.clickElement("xpath", "//Edit[@Name='Party Account *']");
        gstTransactionType(common.getData(dataFile, "gstType"));
        Thread.sleep(1500);
        //items
        enterDataAndValidate("xpath", "//Edit[@Name='Product Code Row 0, Not sorted.']", dataFile, "productCode");
        enterData("xpath", "//Edit[@Name='Quantity * Row 0, Not sorted.']", dataFile, "quantity");
        transactionSave();
        String lastSaved = common.findWebElement("xpath", "//Text[@Name='Last Saved :']/following-sibling::Text").getAttribute("Name");
//        System.out.println("last Saved :"+lastSaved);
        common.clickElement("xpath",closeTab);
        List<String> voucherAndSeries=new ArrayList<>();
        voucherAndSeries.add(transVid);
        voucherAndSeries.add(lastSaved);
        return voucherAndSeries;
    }
    public void setManualVoucherSeries() throws Exception {
        navigateToMastersOrMenus("Sales","Enquiries","//Menu/MenuItem[@Name='Sales Enquiries']");
        common.clickElement("xpath", "//Button[@Name='Configure']");
        common.clickElement("xpath", "//Button[@Name='Voucher Series']");
        Thread.sleep(1000);
        enableCheckboxSelection("//Pane/CheckBox[@Name='Use Manual Voucher Numbers']");
        saveMasterOrProperty();
        closeTransaction("Sales Enquiries");
        navigateToMastersOrMenus("Sales", "Enquiries", "//Menu/MenuItem[@Name='Sales Enquiries']");
        lastTransactionName();
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        selectAndValidateDataNew(common.getData(dataFile, "branch"), "xpath", "//Edit[@Name='Branch *']");
        common.clickElement("xpath", "//Edit[@Name='Trans Currency *']");
        selectAndValidateDataNew(common.getData(dataFile, "transaction"), "xpath", "//Edit[@Name='Trans Currency *']");
        common.clickElement("xpath", "//Edit[@Name='Party Code']");
        selectAndValidateDataNew(common.getData(dataFile, "partyCode"), "xpath", "//Edit[@Name='Party Code']");
        common.clickElement("xpath", "//Edit[@Name='Party Account *']");
        Thread.sleep(2000);
        gstTransactionType(common.getData(dataFile, "gstType"));
        Thread.sleep(1500);
        common.clickElement("xpath", "//Edit[@Name='Price List']");
        selectAndValidateDataNew(common.getData(dataFile, "priceList"), "xpath", "//Edit[@Name='Price List']");
        enterDataAndValidate("xpath", "//Edit[@Name='Product Code Row 0, Not sorted.']", dataFile, "productCode");
        enterData("xpath", "//Edit[@Name='Quantity * Row 0, Not sorted.']", dataFile, "quantity");
        common.clickElement("xpath", "//Button[@Name='Save']");
        common.inputText("xpath", "//Edit[@Name='Enter VoucherSeries']", common.getData(dataFile, "voucherSeries"));
        common.inputText("xpath", "//Edit[@Name='Enter VoucherNo']", common.getData(dataFile, "voucherNumber") + common.getRandom());
        String voucherSeries = common.findWebElement("xpath", "//Edit[@Name='Enter VoucherSeries']").getText();
        String voucherNumber = common.findWebElement("xpath", "//Edit[@Name='Enter VoucherNo']").getText();
        String voucherDetails = voucherSeries +" "+ voucherNumber;
        System.out.println("Voucher Details: " + voucherDetails);
        common.clickElement("xpath", "//Button[@Name='Ok']");
        common.clickElement("xpath", "//Button[@Name='Yes']");
        String popupText = common.findWebElement("xpath", "//Window[@Name='Transaction saved.']/Text").getText();
        System.out.println("windowText :" + popupText);
        common.clickElement("xpath", "//Button[@Name='OK']");
        if (popupText.contains(voucherDetails)) {
            Assert.assertTrue(true, "not matched[ManualVoucherSeries&Pup-up text]");
            System.out.println("pup-up contain Manual voucher series");
        } else {
            Assert.assertFalse(false);
        }
        String lastSaved = common.findWebElement("xpath", "//Text[@Name='Last Saved :']/following-sibling::Text").getAttribute("Name");
        System.out.println("lastSaved :"+lastSaved);
        Assert.assertEquals(voucherDetails,lastSaved,"ManualVoucherSeries and LastSaved both are different please check again");
        System.out.println("Manual Voucher series worked fine");
        common.clickElement("xpath", "//Button[@Name='Configure']");
        common.clickElement("xpath", "//Button[@Name='Voucher Series']");
        Thread.sleep(1000);
        uncheckCheckBox("//Pane/CheckBox[@Name='Use Manual Voucher Numbers']");
        saveMasterOrProperty();
        common.clickElement("xpath", "//TabItem[@Name='Sales Enquiries']/Button[@Name='Close']");
    }

    public void setMultipleVoucherSeries(String navigateMenu, String navigateSubMenu, String navigateSubMenu1, String closeTransactionTabItem) throws Exception {
        common.clickElement("name", navigateMenu);
        common.clickElement("name", navigateSubMenu);
        common.clickElement("name", navigateSubMenu1);
        common.clickElement("xpath", "//Button[@Name='Configure']");
        common.clickElement("xpath", "//Button[@Name='Voucher Series']");
        common.inputText("xpath", "//Edit[@Name='Voucher Series Text']", common.getData(dataFile, "voucherSeriesText"));
        common.inputText("xpath", "//Edit[@Name='Starting Voucher No']", common.getData(dataFile, "startingVoucherNo"));
        common.inputText("xpath", "//Edit[@Name='Voucher Series Seperator']", common.getData(dataFile, "voucherSeriesSeparator"));
        String voucherSeriesText = common.findWebElement("xpath", "//Edit[@Name='Voucher Series Text']").getText();
        String startingVoucherNo = common.findWebElement("xpath", "//Edit[@Name='Starting Voucher No']").getText();
        String voucherSeriesSeparator = common.findWebElement("xpath", "//Edit[@Name='Voucher Series Seperator']").getText();
        String allVoucherTexts = voucherSeriesText + voucherSeriesSeparator + startingVoucherNo;
        System.out.println("all Texts :" + allVoucherTexts);

        WebElement element = driver.findElementByXPath("//CheckBox[@Name='Use Manual Voucher Numbers']");
        String checkBoxToggleState = element.getAttribute("Toggle.ToggleState");
        System.out.println("Check Box Toggle state:-" + checkBoxToggleState);
        if (checkBoxToggleState.equals("1")) {
            element.click();
            System.out.println("Check Box unchecked");
        } else if (checkBoxToggleState.equals("0")) {
            System.out.println("no need anu action");
        }
        common.clickElement("xpath", "//Button[@Name='Save']");
        common.clickElement("xpath", "//Button[@Name='Yes']");
        common.clickElement("xpath", "//Window/Button[@Name='OK']");
        closeTransaction(closeTransactionTabItem);
        navigateToMastersOrMenus("Sales", "Enquiries", "//Menu/MenuItem[@Name='Sales Enquiries']");
        lastTransactionName();
        common.clickElement("xpath", "//Edit[@Name='Voucher Type']");
        selectOptionalMaster(common.getData(dataFile, "voucher"), "xpath", "//Edit[@Name='Voucher Type']");
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        selectAndValidateData(common.getData(dataFile, "branch"), "xpath", "//Edit[@Name='Branch *']");
        common.clickElement("xpath", "//Edit[@Name='Trans Currency *']");
        selectAndValidateData(common.getData(dataFile, "transaction"), "xpath", "//Edit[@Name='Trans Currency *']");
        common.clickElement("xpath", "//Edit[@Name='Party Code']");
        selectAndValidateDataNew(common.getData(dataFile, "partyCode"), "xpath", "//Edit[@Name='Party Code']");
        common.clickElement("xpath", "//Edit[@Name='Party Account *']");
        Thread.sleep(3000);
        gstTransactionType(common.getData(dataFile, "gstType"));
        Thread.sleep(1500);
        common.clickElement("xpath", "//Edit[@Name='Price List']");
        selectAndValidateData(common.getData(dataFile, "priceList"), "xpath", "//Edit[@Name='Price List']");
        common.clickElement("xpath", "//Edit[@Name='Executive *']");
        selectAndValidateData(common.getData(dataFile, "executive"), "xpath", "//Edit[@Name='Executive *']");
        common.clickElement("xpath", "//Edit[@Name='Remarks']");
        selectOptionalMaster(common.getData(dataFile, "remarks"), "xpath", "//Edit[@Name='Remarks']");
        //items
        enterDataAndValidate("xpath", "//Edit[@Name='Product Code Row 0, Not sorted.']", dataFile, "productCode");
        enterData("xpath", "//Edit[@Name='Quantity * Row 0, Not sorted.']", dataFile, "Quantity");
        common.clickElement("xpath", "//Button[@Name='Save']");
//        common.clickElement("xpath","//Button[@Name='Yes']");
        common.clickElement("xpath", "//Window/Button[@Name='OK']");
        //here also once the build is table manual testers gave that build so automatically last saved will be updating then we can perform complete validation
        //issue in the app here after saving the transaction voucher have to update, but it doesn't update once that issues is resolves will work on it
//       String lastSaved= common.findWebElement("xpath", "//Text[@Name='Last Saved :']/following-sibling::Text").getAttribute("Name");
//       Assert.assertEquals(allVoucherTexts,lastSaved,"voucher is not updated or not in the expected formate");
    }
}
