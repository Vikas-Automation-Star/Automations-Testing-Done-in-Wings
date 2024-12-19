package com.wings.pages.taxes.transactions.TDS;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

public class TdsPayments extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public TdsPayments(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void tdsPayments() throws InterruptedException, IOException, ParseException {

        common.clickElement("name", "Taxes");
        common.clickElement("name", "TDS");
        common.clickElement("name", "TDS Payments");
        Thread.sleep(1000);
        common.clickElement("xpath","//Edit[@Name='Voucher Type']");
        super.selectOptionalMaster(common.getDataEvenNoKeyPresent(dataFile,"voucher"),"xpath","//Edit[@Name='Voucher Type']" );
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        super.selectMaster(common.getData(dataFile, "branch"));
        common.clickElement("xpath", "//Edit[@Name='Transaction Currency *']");
        WebElement startingYear= common.findWebElement("xpath","//Edit[@Name='From Year And Month *']");
        startingYear.clear();
        startingYear.sendKeys(common.getData(dataFile,"startingYear"));
        WebElement endingYear = common.findWebElement("xpath","//Edit[@Name='To Year And Month *']");
        endingYear.clear();
        endingYear.sendKeys(common.getData(dataFile,"endingYear"));
        common.clickElement("xpath", "//Edit[@Name='TDS Sub Type *']");
        super.selectMaster(common.getData(dataFile,"New Sub TaxDeductedSource38"));
        common.clickElement("xpath","//Edit[@Name='TDS Account *']");
        super.selectMaster(common.getData(dataFile,"TDS Collected Account"));
        Thread.sleep(2000);
        common.inputText("xpath","//Edit[@Name='Bank Challan No']", common.getData(dataFile,"challanNo"));
        common.inputText("xpath","//Edit[@Name='BSR Code']", common.getData(dataFile,"bsrCode"));
        common.clickElement("xpath","//Edit[@Name='Executive *']");
        super.selectAndValidateDataNew(common.getData(dataFile,"executive"),"xpath","//Edit[@Name='Executive *']");
        common.clickElement("xpath","//Edit[@Name='Remarks']");
        super.selectOptionalMaster(common.getDataEvenNoKeyPresent(dataFile,"remarks"), "xpath","//Edit[@Name='Remarks']");
        Thread.sleep(2000);

        List<WebElement> checkbox=common.findWebElements("xpath","//Table[@Name='TDSDetails']/*[contains(@Name,'Row')]/CheckBox[contains(@Name,'TDS Paid * Row')]");
        System.out.println(checkbox.size());
        for(WebElement cheks:checkbox){
            System.out.println(cheks.getText());
            cheks.click();
        }
        common.clickElement("xpath","//TabItem[@Name='  F5 Cheques  ']");
        common.clickElement("xpath","//Edit[@Name='Bank Account Code Row 0, Not sorted.']");
        common.inputText("xpath","//Edit[@Name='Bank Account Code Row 0, Not sorted.']",common.getData(dataFile,"bankCode"));
        Thread.sleep(2000);
        common.clickElement("xpath","//Edit[@Name='Amount * Row 0, Not sorted.']");
        common.inputText("xpath","//Edit[@Name='Amount * Row 0, Not sorted.']", common.getData(dataFile,"amount"));
        common.clickElement("xpath","//Edit[@Name='Cheque/EFT No * Row 0, Not sorted.']");
        common.inputText("xpath","//Edit[@Name='Cheque/EFT No * Row 0, Not sorted.']", common.getData(dataFile,"checkNO"));
        common.clickElement("xpath","//Edit[@Name='Cheque Date * Row 0, Not sorted.']");
        Thread.sleep(2000);
        common.clickElement("xpath","//TabItem[@Name='  F9 Summary  ']");
        Assert.assertEquals("20","20");
        super.saveTransaction();
        Thread.sleep(1000);
        super.closeTransaction("TDS Payments");
        Thread.sleep(2000);
    }
}
