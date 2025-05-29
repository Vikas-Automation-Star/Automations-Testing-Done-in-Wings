package com.wings.pages.sales.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

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
        navigateToPartyProductwiseDiscountMenu();
        Thread.sleep(1000);
        String oldVoucherID =oldTTransactionID();
        enterInput("xpath","//Edit[@Name='Branch *']",dataFile,"partyProductDiscount","branch");
        //items
        enterInput("xpath","//Edit[@Name='Party Discount Group * Row 0, Not sorted.']",dataFile,"partyProductDiscount","partyDiscountGroup");
        enterInput("xpath","//Edit[@Name='Product Discount Group * Row 0, Not sorted.']",dataFile,"partyProductDiscount","productDiscountGroup");
        enterInput("xpath","//Edit[@Name='Disc % * Row 0, Not sorted.']",dataFile,"partyProductDiscount","discountPercentage");
        //save
        transactionSave();
        String newVoucherID =newTransactionID(oldVoucherID);
        Assert.assertNotEquals(newVoucherID, oldVoucherID,"both ID's should not Equal when we perform transaction");
    }
}