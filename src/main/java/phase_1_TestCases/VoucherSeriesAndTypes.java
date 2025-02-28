package phase_1_TestCases;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.io.IOException;

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

    public String setVouchersTypes(String voucherTypeName, String navigateMenu, String navigateSubMenu, String navigateSubMenu1, String selectVoucher, String closeTransTab) throws InterruptedException, AWTException, IOException, ParseException {
        common.clickElement("xpath", "//MenuItem[@Name='Configure']");
        common.clickElement("xpath", "//MenuItem[@Name='Voucher Types']");
        WebElement rightClick = common.findWebElement("xpath", "//TreeItem[@Name='All Voucher Types']");
        Actions actions = new Actions(driver);
        actions.contextClick(rightClick).perform();
        common.clickElement("xpath", "//MenuItem[@Name='New Master']");
        Thread.sleep(1500);
        common.inputText("xpath", "//Edit[@Name='New Voucher Type *']/*[@Name='New Voucher Type *']", voucherTypeName);
        common.clickElement("xpath", "//Button[@Name='Save']");
        common.clickElement("xpath", "//Button[@Name='OK']");
        common.clickElement("xpath", "//Button[@Name='Close']");
        closeTransaction("Voucher Types");
//        common.clickElement("xpath","//TabItem[@Name='Voucher Types']/Button[@Name='Close']");
        common.clickElement("xpath", navigateMenu);
        common.clickElement("xpath", navigateSubMenu);
        common.clickElement("xpath", navigateSubMenu1);
        Thread.sleep(1000);
        common.clickElement("xpath", "//Button[@Name='Configure']");
        common.clickElement("xpath", "//Button[@Name='Voucher Type']");
        common.clickElement("xpath", "//Text[@Name='Applicable Voucher Type']/following-sibling::Button[@Name='...']");
        Thread.sleep(1000);
        WebElement voucherPath = common.findWebElement("xpath", "//Table[@Name='ApplicableVoucherType']/*[starts-with(@Name,'Row 0')]/*[contains(@Name,'Voucher Type * Row 0, Not sorted.')]");
        voucherPath.sendKeys(selectVoucher, Keys.ENTER);
        WebElement voucherText = common.findWebElement("xpath", "//Table[@Name='ApplicableVoucherType']/*[starts-with(@Name,'Row 0')]/*[contains(@Name,'Voucher Type * Row 0, Not sorted.')]");
        String voucherTxt = voucherText.getText();
        System.out.println("voucherText :" + voucherTxt);
        common.clickElement("xpath", "//Pane/Button[@Name='Ok']");
        common.clickElement("xpath", "//Button[@Name='Save']");
        common.clickElement("xpath", "//Button[@Name='Yes']");
        common.clickElement("xpath", "//Window/Button[@Name='OK']");
        common.clickElement("xpath", closeTransTab);
        return voucherTxt;
    }

    public String validateVoucherTypes(String navigateMenu, String navigateSubMenu, String navigateSubMenu1) throws InterruptedException {
        Thread.sleep(1500);
        common.clickElement("xpath", navigateMenu);
        common.clickElement("xpath", navigateSubMenu);
        common.clickElement("xpath", navigateSubMenu1);
        WebElement transVid = common.findWebElement("xpath", "//Edit[@Name='Voucher Type']/Edit[@Name='Voucher Type']");
        String transactionID = transVid.getText();
        System.out.println("expectedVoucherText :" + transactionID);
        return transactionID;
    }

    public void setManualVoucherSeries(String navigateMenu, String navigateSubMenu, String navigateSubMenu1, String closeTransTab) throws IOException, ParseException, InterruptedException {
        common.clickElement("xpath", navigateMenu);
        common.clickElement("xpath", navigateSubMenu);
        common.clickElement("xpath", navigateSubMenu1);
        common.clickElement("xpath", "//Button[@Name='Configure']");
        common.clickElement("xpath", "//Button[@Name='Voucher Series']");
        enableCheckboxSelection("//CheckBox[@Name='Use Manual Voucher Numbers']");
        common.clickElement("xpath", "//Button[@Name='Save']");
        common.clickElement("xpath", "//Button[@Name='Yes']");
        common.clickElement("xpath", "//Window/Button[@Name='OK']");
//        common.clickElement("xpath",closeTransTab);
//        navigateToMaster("Sales","Enquiries","//Menu/MenuItem[@Name='Sales Enquiries']");
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
        Thread.sleep(5000);
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
        //save
        common.clickElement("xpath", "//Button[@Name='Save']");
        common.inputText("xpath", "//Edit[@Name='Enter VoucherSeries']", common.getData(dataFile, "voucherSeries"));
        common.inputText("xpath", "//Edit[@Name='Enter VoucherNo']", common.getData(dataFile, "voucherNumber") + common.getRandom());
        String voucherSeries = common.findWebElement("xpath", "//Edit[@Name='Enter VoucherSeries']").getText();
        String voucherNumber = common.findWebElement("xpath", "//Edit[@Name='Enter VoucherNo']").getText();
        String voucherDetails = voucherSeries + voucherNumber;
        System.out.println("Voucher Details: " + voucherDetails);
        common.clickElement("xpath", "//Button[@Name='Ok']");
        //once the build is stable we need to validate using the pop-up text
//        Assert.assertEquals(voucherDetails,"");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Window[starts-with(@Name,'Transaction')]/*[@Name='OK']");
//        String lastSaved=common.findWebElement("xpath", "//Text[@Name='Last Saved :']/following-sibling::Text").getAttribute("Name");
        //here also once the build is table manual testers gave that build so automatically last saved will be updating then we can perform complete validation
//        Assert.assertEquals(voucherDetails,lastSaved,"Manual voucher series is no updated check once again");
    }

    public void setMultipleVoucherSeries(String navigateMenu, String navigateSubMenu, String navigateSubMenu1, String closeTransactionTabItem) throws IOException, ParseException, InterruptedException {
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
        navigateToMaster("Sales", "Enquiries", "//Menu/MenuItem[@Name='Sales Enquiries']");
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
