package com.wings.pages.finance.transactions.Banking;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import com.wings.pages.Transaction;
import com.wings.utils.Common;
import java.awt.*;
import java.io.IOException;

public class InterBankFundTransfers extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;


    public InterBankFundTransfers(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void bankFundTransfer() throws InterruptedException, IOException, ParseException, AWTException {
        common.clickElement("name", "Finance");
        common.clickElement("name", "Banking");
        common.clickElement("xpath", "//MenuItem[@Name='Inter Bank Fund Transfers']");
        Thread.sleep(1000);
        super.lastTransactionName();
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        super.selectMaster(common.getData(dataFile, "branch"));
        common.clickElement("xpath", "//Edit[@Name='Trans Currency *']");
        super.selectMaster(common.getData(dataFile, "transaction"));
        common.clickElement("xpath", "//Edit[@Name='From Bank Code']");
        super.selectMaster(common.getData(dataFile, "fromBank"));
        common.clickElement("xpath", "//Edit[@Name='To Bank Code']");
        super.selectMaster(common.getData(dataFile, "toBank"));
        common.inputText("xpath","//Edit[@Name='Amount *']", common.getData(dataFile,"amount"));
        common.inputText("xpath","//Edit[@Name='Cheque/EFT No *']", common.getData(dataFile,"cheque"));
        common.clickElement("xpath","//Edit[@Name='Charges Account Code']");
        common.inputText("xpath","//Edit[@Name='Transfered Charges']", common.getData(dataFile,"charges"));
        //slider
        int offset=550;
        WebElement slider=common.findWebElement("xpath","//Thumb[@Name='Position']");
        Actions actions=new Actions(driver);
        actions.clickAndHold(slider).moveByOffset(offset,0).release().perform();

        common.inputText("xpath","//Edit[@Name='Supplier Bill No *']", common.getData(dataFile,"supplier Bill"));
        common.inputText("xpath","//Edit[@Name='Supplier Bill Date *']", common.getData(dataFile,"supplierDate"));
        common.clickElement("xpath", "//Edit[@Name='Executive *']");
        super.selectMaster(common.getData(dataFile,"executive"));
        Thread.sleep(1000);
        common.clickElement("name","  F11 Summary  ");

        //save
        super.transactionSave();
        super.lastTransactionName();
        super.transactionClose(common.getData(dataFile,"close"));



    }
}