package com.wings.pages.finance.transactions.Banking;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import com.wings.pages.Transaction;
import com.wings.utils.Common;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class ReceivedChequesBounce  extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;


    public ReceivedChequesBounce(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void chequeBounce() throws InterruptedException, IOException, ParseException, AWTException {
        common.clickElement("name", "Finance");
        common.clickElement("name", "Banking");
        common.clickElement("xpath", "//MenuItem[@Name='Received Cheques Bounce']");
        Thread.sleep(1000);
        super.lastTransactionName();
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        super.selectMaster(common.getData(dataFile, "branch"));
        common.clickElement("xpath", "//Edit[@Name='Trans Currency *']");
        super.selectMaster(common.getData(dataFile, "transaction"));
        common.clickElement("xpath", "//Edit[@Name='Bank Code']");
        super.selectMaster(common.getData(dataFile, "bankAccount"));
        common.clickElement("xpath", "//Edit[@Name='Account Code']");
        super.selectMaster(common.getData(dataFile, "accountCode"));
        common.clickElement("xpath", "//Edit[@Name='Account *']");
        WebElement chequeDetails = common.findWebElement("xpath", "//Window[@Name='Cheque Details']");
        //if found
        common.clickElement("xpath", "//DataItem[@Name='select row 1']");
        common.clickElement("name", "OK");

        List<WebElement> elementList = common.findWebElements("xpath", "//Table/*[@Name='Data Panel']/*/*[contains(@Name,'GST Transaction Type row')]");
        System.out.println("Size :" + elementList.size());
        for (WebElement i : elementList) {
            System.out.println(i.getText());
            if (i.getText().contains("State Sales Returns from Registered Dealers")) {
                i.click();
                i.sendKeys(Keys.LEFT, Keys.SPACE,Keys.ENTER,Keys.ENTER);
            }
        }
    }
}
