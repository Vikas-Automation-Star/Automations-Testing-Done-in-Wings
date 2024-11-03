package com.wings.pages.sales.transactions;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import com.wings.pages.Transaction;
import com.wings.utils.Common;

import java.io.IOException;

public class ProductndPartyDiscount extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public ProductndPartyDiscount(WindowsDriver driver, String file) {
        super(driver);
        common = new Common(this.driver = driver);
        dataFile = file;
    }

    public void productDiscount() throws InterruptedException, IOException, ParseException {
        common.clickElement("name", "Sales");
        common.clickElement("xpath", "//MenuItem[@Name='Prices and Discounts'][2]");
        common.clickElement("name", "Party and Product wise Discounts");
        Thread.sleep(1000);
        lastTransactionName();
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        selectAndValidateData(common.getData(dataFile,"branch"), "xpath", "//Edit[@Name='Branch *']");
        common.clickElement("xpath","//Edit[@Name='Remarks']");
        selectOptionalMaster(common.getData(dataFile,"remarks"),"xpath","//Edit[@Name='Remarks']");
        //f3-items
        WebElement partyDiscount =common.findWebElement("xpath","//Edit[@Name='Party Discount Group * Row 0, Not sorted.']");
        partyDiscount.click();
        common.inputAndVerify("xpath","//Edit[@Name='Party Discount Group * Row 0, Not sorted.']", common.getData(dataFile,"partyDiscount"));
        WebElement element=common.findWebElement("name","Product Discount Group * Row 0, Not sorted.");
        element.click();
        common.inputAndVerify("name","Product Discount Group * Row 0, Not sorted.", common.getData(dataFile,"productDiscount"));
        Thread.sleep(1000);
        common.inputText("name","Disc % * Row 0, Not sorted.", common.getData(dataFile,"discount"));
        common.clickElement("name","With Effect From * Row 0, Not sorted.");
        //save
        transactionSave();
        lastTransactionName();
    }
}
