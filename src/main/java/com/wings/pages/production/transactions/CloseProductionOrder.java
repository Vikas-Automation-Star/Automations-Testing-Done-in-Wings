package com.wings.pages.production.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.List;

public class CloseProductionOrder extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public CloseProductionOrder(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void closeProductionOrder() throws InterruptedException, IOException, ParseException {

        common.clickElement("name", "Production");
        common.clickElement("name", "Standard");
        common.clickElement("name", "Close Production Order");
        Thread.sleep(3000);
        super.oldTTransaction();
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        super.selectMasterWithValidation(common.getData(dataFile,"branch"),"xpath", "//Edit[@Name='Branch *']");
        common.clickElement("xpath", "//Edit[@Name='Location *']");
        common.clickElement("xpath", "//Edit[@Name='Transaction Currency *']");
        super.selectMasterWithValidation(common.getData(dataFile,"currency"), "xpath", "//Edit[@Name='Transaction Currency *']");
        WebElement code = common.findWebElement("xpath", "//Edit[@Name='Finished Product Code']");
        code.click();
        super.selectAndValidateDataNew(common.getData(dataFile,"finishedProductCode"),"xpath", "//Edit[@Name='Finished Product Code']");
        common.clickElement("xpath", "//Edit[@Name='Finished Product *']");
        common.clickElement("xpath", "//CheckBox[@Name='Select Row 3']");
        common.clickElement("xpath", "//Window[@Name='Open Transactions']/Pane/Button[@Name='Ok']");
        common.clickElement("xpath","//Edit[@Name='Executive *']");
        super.selectAndValidateDataNew(common.getData(dataFile,"executive"),"xpath","//Edit[@Name='Executive *']");

        List<WebElement> elementList = common.findWebElements("xpath", "//Edit[@Name='Quantity * Row 0, Not sorted.']");
        System.out.println("Size :" + elementList.size());
        for (WebElement j : elementList) {
            System.out.println(j.getText());
            j.click();
            j.sendKeys(common.getData(dataFile, "F3quantity"));
        }
        common.clickElement("xpath","//CheckBox[@Name='Cancel * Row 0']");
        common.clickElement("xpath","//TabItem[@Name='  F5 Pending Stock To Be Received From Production  ']");
        List<WebElement> elementList1 = common.findWebElements("xpath", "//Edit[@Name='Quantity * Row 0, Not sorted.']");
        System.out.println("Size :" + elementList1.size());
        for (WebElement j : elementList1) {
            System.out.println(j.getText());
            j.click();
            j.sendKeys(common.getData(dataFile, "F5quantity"));
        }
        common.clickElement("xpath","//CheckBox[@Name='Cancel * Row 0']");
        super.saveTransaction();
        Thread.sleep(1000);
        super.newTransaction();
        super.closeTransaction("Close Production Order");
        Thread.sleep(2000);
    }
}
