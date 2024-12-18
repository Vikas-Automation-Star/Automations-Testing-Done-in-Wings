package com.wings.pages.production.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.List;

public class MaterialReceiptFromProduction extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public MaterialReceiptFromProduction(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void materialReceiptFromProduction() throws InterruptedException, IOException, ParseException {

        common.clickElement("name", "Production");
        common.clickElement("name", "Standard");
        common.clickElement("name", "Material Receipts from Production");
        Thread.sleep(3000);
        super.oldTTransaction();
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        super.selectMasterWithValidation(common.getData(dataFile,"branch"),"xpath", "//Edit[@Name='Branch *']");
        common.clickElement("xpath", "//Edit[@Name='Location *']");
        common.clickElement("xpath", "//Edit[@Name='Transaction Currency *']");
        super.selectMasterWithValidation(common.getData(dataFile,"currency"),"xpath", "//Edit[@Name='Transaction Currency *']" );
        WebElement code = common.findWebElement("xpath", "//Edit[@Name='Finished Product Code']");
        code.click();
        super.selectMasterWithValidation(common.getData(dataFile,"finishedProductCode"),"xpath", "//Edit[@Name='Finished Product Code']");
        common.clickElement("xpath", "//Edit[@Name='Finished Product *']");
        common.clickElement("xpath", "//CheckBox[@Name='Select Row 0']");
        common.clickElement("xpath", "//Window[@Name='Open Transactions']/Pane/Button[@Name='Ok']");
        common.clickElement("xpath","//Edit[@Name='Executive *']");
        super.selectMasterWithValidation(common.getData(dataFile,"executive"),"xpath","//Edit[@Name='Executive *']");

        List<WebElement> elementList = common.findWebElements("xpath", "//Edit[@Name='Quantity * Row 0, Not sorted.']");
        System.out.println("Size :" + elementList.size());
        for (WebElement j : elementList) {
            System.out.println(j.getText());
            j.click();
            j.sendKeys(common.getData(dataFile, "quantity"));
        }
        super.sliderHandle();
        List<WebElement> elementList1 = common.findWebElements("xpath", "//Edit[@Name='Unit Rate Row 0, Not sorted.']");
        System.out.println("Size :" + elementList1.size());
        for (WebElement j : elementList1) {
            System.out.println(j.getText());
            j.click();
            j.sendKeys(common.getData(dataFile, "unitRate"));
        }
//        common.clickElement("xpath","//Edit[@Name='Unit Rate Row 0, Not sorted.");
//        common.inputText("xpath","//Edit[@Name='Unit Rate Row 0, Not sorted.",common.getData(dataFile, "unitRate"));
        common.clickElement("xpath", "//Edit[@Name='Gross Amount Row 0, Not sorted.']");
        transactionSave();
        Thread.sleep(1000);
        super.newTransaction();
        super.closeTransaction("Material Receipts from Production");
        Thread.sleep(2000);
    }
}
