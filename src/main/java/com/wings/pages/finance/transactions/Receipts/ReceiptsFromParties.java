package com.wings.pages.finance.transactions.Receipts;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import com.wings.pages.Transaction;
import com.wings.utils.Common;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;

public class ReceiptsFromParties extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;
    double finalAmount = 0.0;

    public ReceiptsFromParties(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void receiptFromParty() throws InterruptedException, IOException, ParseException, AWTException {
        navigateToMastersWhen3Steps("Finance","Receipts","Receipts from Parties");
        common.clickElement("xpath","//Edit[@Name='Voucher Type']");
        selectOptionalMaster(common.getData(dataFile,"voucher"),"xpath","//Edit[@Name='Voucher Type']");
        Thread.sleep(1000);
        String oldVoucherID =oldTTransactionID();
        enterInput("xpath","//Edit[@Name='Branch *']",dataFile,"receiptsFromParties","branch");
        enterInput("xpath", "//Edit[@Name='Party Code']",dataFile,"receiptsFromParties", "partyCode");
        gstTransactionType("Inter State Sales to Registered Dealers");
        enterInput("xpath", "//Edit[@Name='Email']",dataFile,"receiptsFromParties", "email");
        enterInput("xpath", "//Edit[@Name='MobileNumber']",dataFile,"receiptsFromParties", "mobileNum");
        enterInput("xpath", "//Edit[@Name='Discount Account']", dataFile, "receiptsFromParties","discAct");
        WebElement dropDown=common.findWebElement("xpath","//Button[@Name='Open']");dropDown.click();dropDown.sendKeys(Keys.DOWN,Keys.ENTER);
        enterInput("xpath", "//Edit[@Name='Executive *']", dataFile, "receiptsFromParties","executive");
        common.clickElement("xpath","//Edit[@Name='Remarks']");
        selectOptionalMaster(common.getData(dataFile,"remarks"),"xpath","//Edit[@Name='Remarks']");
        common.clickElement("xpath","//CheckBox[@Name='Advance Receipts']");

    }
}
