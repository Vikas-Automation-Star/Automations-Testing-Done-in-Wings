package com.wings.pages.inventory.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.awt.*;
import java.io.IOException;

public class InterLocationTransfers extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public InterLocationTransfers(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void locationTransfer() throws InterruptedException, AWTException, IOException, ParseException {
        navigateToMastersWhen2Steps("Inventory","Inter Location Transfers");
        Thread.sleep(1000);
        String oldVoucherID =oldTTransactionID();
        enterInput("xpath","//Edit[@Name='Branch *']",dataFile,"interLocationTransfer","branch");
        enterInput("xpath","//Edit[@Name='Location *']",dataFile,"interLocationTransfer","location");
        enterInput("xpath","//Edit[@Name='To Location *']",dataFile,"interLocationTransfer","toLocation");
        enterInput("xpath", "//Edit[@Name='Price List']", dataFile, "interLocationTransfer","priceList");
        enterInput("xpath", "//Edit[@Name='Executive *']", dataFile, "interLocationTransfer","executive");
    }
}
