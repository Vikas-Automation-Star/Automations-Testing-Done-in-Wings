package com.wings.pages.sales.masters;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import java.io.IOException;

public class SalesTargetGroup extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public SalesTargetGroup(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void salesTarget() throws InterruptedException, IOException, ParseException {
        navigatetoProductSalesTargetGroup();
        Thread.sleep(1000);
        createMaster("xpath", "//TreeItem[@Name='Product Sales Target Groups']/TreeItem[@Name='All Product Sales Target Groups']");
        Thread.sleep(1000);
        common.inputText("xpath", "//Edit[@Name='New Product Sales Target Group *']", common.getData(dataFile, "newAccount") + common.getRandom());
        Thread.sleep(2000);
        common.inputText("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));
        Thread.sleep(1000);
        saveMaster();
        closeTransaction(common.getData(dataFile,"menuItem"));
        refresh();
        //validate
        navigatetoProductSalesTargetGroup();
        validateAndInactivate(common.getData(dataFile,"menuItem"), common.getData(dataFile,"newAccount") );
    }
}