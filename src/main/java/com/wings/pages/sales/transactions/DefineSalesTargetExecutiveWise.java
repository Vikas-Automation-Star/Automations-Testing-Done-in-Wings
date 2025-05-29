package com.wings.pages.sales.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import com.wings.utils.FileUtil;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;

public class DefineSalesTargetExecutiveWise extends Transaction {

        WindowsDriver driver;
        Common common;
        String dataFile;

        public DefineSalesTargetExecutiveWise(WindowsDriver driver, String file) {
            super(driver);
            this.driver = driver;
            common = new Common(driver);
            dataFile = file;
        }

        public void salesTargetExecutiveWise() throws InterruptedException, IOException, ParseException, IOException, ParseException {
            long start = System.nanoTime();
            System.out.println("Define Sales Target Executive Wise started in :"+start);
            Thread.sleep(100);
            navigateToMastersWhen3Steps(common.getData(dataFile,"salesTarget","firstMenu"),common.getData(dataFile,"salesTarget","secondMenu"),common.getData(dataFile,"salesTarget","thirdMenu") );
            Thread.sleep(3000);

            String oldVoucherID =oldTTransactionID();
            System.out.println("oldID: "+ oldVoucherID);
            //branch selection
            enterInput("xpath", "//Edit[@Name='Branch *']", dataFile, "salesTarget", "branch");
            //month
            WebElement month= common.findWebElement("xpath","//Edit[@Name='Month *']/Button[@Name='Open']");
            month.click();
            common.findWebElement("xpath","//Edit[@Name='Search Box']").sendKeys("May",Keys.DOWN,Keys.ENTER);
            //year
            WebElement year = common.findWebElement("xpath","//Edit[@Name='Year *']/Button[@Name='Open']");
            year.click();
            common.findWebElement("xpath","//Edit[@Name='Search Box']").sendKeys("2025",Keys.DOWN,Keys.ENTER);
            Thread.sleep(1000);
            enterData("xpath","//Edit[@Name='Sales Executive * Row 0, Not sorted.']",dataFile,"salesTarget","salesExecutive");
            enterData("xpath","//Edit[@Name='Product Sales Target Group * Row 0, Not sorted.']",dataFile,"salesTarget","salesTargetGroup");
            enterData("xpath","//Edit[@Name='Target Quantity Row 0, Not sorted.']",dataFile,"salesTarget","targetQuantity");
            enterData("xpath","//Edit[@Name='Amount Row 0, Not sorted.']",dataFile,"salesTarget","amount");
            enterOtherInfo(dataFile,"salesTarget");
            //summary
            common.clickElement("xpath","//TabItem[@Name='  F7 Summary  ']");
            quantityPresentInSummary();
            //save
            transactionSave();
            String newVoucherID = newTransactionID(oldVoucherID);
            System.out.println("newID: " + newVoucherID);
            Assert.assertNotEquals(newVoucherID, oldVoucherID, "Voucher Numbers are same. Check Transaction.");
            Thread.sleep(1000);
            common.clickElement("xpath", "//MenuItem[@Name='Sales']");
            common.clickElement("xpath", "//MenuItem[@Name='Targets']");
            common.clickElement("xpath", "//MenuItem[@Name='Define Sales Targets-Executive Wise'][2]");
            Thread.sleep(1000);
            common.clickElement("xpath", "//Pane/Button[@Name='Submit']");
            Thread.sleep(1500);
            verifyReport(newVoucherID, dataFile, "salesTarget");

            long duration = System.nanoTime() - start;
            FileUtil.writeTimeLog("delivery returns",duration/1000000000);
        }
    }
