package com.wings.pages.finance.transactions.Banking;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.IOException;
import java.util.List;

public class ReceivedChequesBounce extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public ReceivedChequesBounce(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void receivedCheckBounce() throws InterruptedException, IOException, ParseException {
        navigateToReceivedChequesBounceMenu();
        Thread.sleep(3000);
        super.oldTTransaction();
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        super.selectMasterWithValidation(common.getData(dataFile, "branch"),"xpath", "//Edit[@Name='Branch *']");
        common.clickElement("xpath", "//Edit[@Name='Trans Currency *']");
        common.clickElement("xpath","//Edit[@Name='Bank Code']");
        super.selectMasterWithValidation(common.getData(dataFile,"bankCode"),"xpath","//Edit[@Name='Bank Code']");
        common.clickElement("xpath","//Edit[@Name='Bank Account *']");
        common.clickElement("xpath","//Edit[@Name='Account Code']");
        super.selectMasterWithValidation(common.getData(dataFile,"accountCode"),"xpath","//Edit[@Name='Account Code']");
        common.clickElement("xpath","//Edit[@Name='Account *']");
        Thread.sleep(4000);

        List<WebElement> elementList = common.findWebElements("xpath", "//Window[@Name='Cheque Details']/Table/*[@Name='Data Panel']/ListItem[contains(@Name,'Row')]");
        System.out.println("checkDetails Size :" + elementList.size());
        try {
            for (WebElement j : elementList) {
                System.out.println(j.getText());
                if (j.getText().contains("SI 17")) {
                    j.click();
                    j.sendKeys(Keys.LEFT,Keys.LEFT,Keys.LEFT,Keys.SPACE, Keys.ENTER, Keys.ENTER);
                    break;
                }
            }
        } catch (StaleElementReferenceException e) {
            System.out.println("exceptionHandled");
        }
        super.partyCodeGstSelection();
        int offset = 800;
        WebElement slider = common.findWebElement("xpath", "//ScrollBar[@Name='Horizontal']/Thumb[@Name='Position']");
        Actions actions = new Actions(driver);
        actions.clickAndHold(slider).moveByOffset(offset, 0).release().perform();

        common.clickElement("xpath", "//Edit[@Name='Supplier Bill No *']");
        common.inputText("xpath", "//Edit[@Name='Supplier Bill No *']", common.getData(dataFile,"supllierBillNo"));
        common.clickElement("xpath", "//Edit[@Name='Supplier Bill Date *']");
        common.inputText("xpath", "//Edit[@Name='Supplier Bill Date *']", common.getData(dataFile,"supllierBillDate"));
        common.clickElement("xpath","//Edit[@Name='Executive *']");
        selectMasterWithValidation(common.getData(dataFile,"executive"),"xpath","//Edit[@Name='Executive *']");
        Thread.sleep(3000);
        super.checkBoxSelectionBillsPayable("xpath","//Table[@Name='BillsPayable']/*[starts-with(@Name,'Row')]","//Edit[starts-with(@Name,'Voucherentity No * Row')]","//CheckBox[starts-with(@Name,'Adjust Row')]");
       //save
        transactionSave();
        lastTransactionName();
    }
}
