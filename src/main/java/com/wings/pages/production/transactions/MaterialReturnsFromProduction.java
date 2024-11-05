package com.wings.pages.production.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.List;

public class MaterialReturnsFromProduction extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public MaterialReturnsFromProduction(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void materialReturnsFromProduction() throws InterruptedException, IOException, ParseException {

        common.clickElement("name", "Production");
        common.clickElement("name", "Standard");
        common.clickElement("name", "Material Returns from Production");
        Thread.sleep(3000);
        super.oldTTransaction();
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        super.selectMasterWithValidation(common.getData(dataFile,"branch"),"xpath","//Edit[@Name='Branch *']" );
        common.clickElement("xpath", "//Edit[@Name='Location *']");
        common.clickElement("xpath", "//Edit[@Name='Trans Currency *']");
        super.selectMasterWithValidation(common.getData(dataFile,"currency"),"xpath", "//Edit[@Name='Trans Currency *']");
        WebElement code = common.findWebElement("xpath", "//Edit[@Name='Material Issue No']");
        code.click();
        code.sendKeys(common.getData(dataFile, "MaterialIssueNo"));
        List<WebElement> elementList = common.findWebElements("xpath", "//Edit[@Name='Quantity * Row 0, Not sorted.']");
        System.out.println("Size :" + elementList.size());
        for (WebElement j : elementList) {
            System.out.println(j.getText());
            j.click();
            j.sendKeys(common.getData(dataFile, "quantity"));
        }
        common.clickElement("xpath", "//Edit[@Name='Unit Rate Row 0, Not sorted.']");
        super.saveTransaction();
        Thread.sleep(1000);
        super.newTransaction();
        super.closeTransaction("Material Returns from Production");
        Thread.sleep(2000);

    }
}

