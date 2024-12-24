package com.wings.pages.production.transactions;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.List;

public class MaterialissuestoProduction extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public MaterialissuestoProduction(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void materialIssuesToProduction() throws InterruptedException, IOException, ParseException {

        common.clickElement("name", "Production");
        common.clickElement("name", "Standard");
        common.clickElement("name", "Material Issues to Production");
        Thread.sleep(3000);
        super.oldTTransaction();
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        super.selectMasterWithValidation(common.getData(dataFile,"branch"),"xpath", "//Edit[@Name='Branch *']");
        common.clickElement("xpath", "//Edit[@Name='Location *']");
        common.clickElement("xpath", "//Edit[@Name='Transaction Currency *']");
        super.selectMasterWithValidation(common.getData(dataFile,"currency"),"xpath", "//Edit[@Name='Transaction Currency *']");
        common.clickElement("xpath", "//Edit[@Name='Finished Product Code']");
        super.selectAndValidateDataNew(common.getData(dataFile,"fProduct"),"xpath", "//Edit[@Name='Finished Product Code']");
        common.clickElement("xpath", "//Edit[@Name='Finished Product *']");
//        List<WebElement> clickCheckbox=common.findWebElements("xpath","//Window[@Name='Open Transactions']/Pane/Table/*[contains(@Name,'Row')]/*[contains(@Name,'Select Row')]");
//        System.out.println("Size :-"+clickCheckbox.size());
//        for(WebElement checkbox:clickCheckbox){
////            System.out.println(checkbox.getText());
//            checkbox.click();
//        }
//        common.clickElement("xpath","//Button[@Name='Ok']");

        common.clickElement("xpath","//CheckBox[@Name='Select Row 0']");
        common.clickElement("xpath", "//Window[@Name='Open Transactions']/Pane/Button[@Name='Ok']");
        common.clickElement("xpath","//Edit[@Name='Executive *']");
        super.selectMasterWithValidation(common.getData(dataFile,"executive"),"xpath","//Edit[@Name='Executive *']" );
        common.clickElement("xpath","//Edit[@Name='Department']");
        selectAndValidateDataNew(common.getData(dataFile,"department"),"xpath","//Edit[@Name='Department']");
        common.clickElement("xpath","//Edit[@Name='Quantity * Row 0, Not sorted.']");
        common.inputText("xpath","//Edit[@Name='Quantity * Row 0, Not sorted.']", common.getData(dataFile,"quantity"));
        transactionSave();
        Thread.sleep(1000);
        super.newTransaction();
        super.closeTransaction("Material Issues to Production");
        Thread.sleep(2000);

    }
}
