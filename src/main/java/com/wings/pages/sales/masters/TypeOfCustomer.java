package com.wings.pages.sales.masters;

import com.wings.pages.Masters;
import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.IOException;

public class TypeOfCustomer extends Masters {
    WindowsDriver driver;
    Common common;
    String file;

    public TypeOfCustomer(WindowsDriver driver, String dataFile) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        file = dataFile;
    }

    public void typecust() throws InterruptedException, IOException, ParseException {
        navigateToMastersWhen2Steps(common.getData(file,"menu"), common.getData(file,"subMenu") );
        Thread.sleep(1000);
        createMaster("xpath", "//TreeItem[@Name='Types of Customer']/TreeItem[@Name='All Types of Customer']");
        Thread.sleep(2500);
        common.clickElement("xpath", "//Edit[@Name='New Type Of Customer *']");
        common.inputText("xpath", "//Edit[@Name='New Type Of Customer *']", common.getData(file, "newCust") + common.getRandom());
        common.inputAndVerify("xpath", "//Edit[@Name='Prefix *']", common.getData(file, "prefix"));
        common.inputAndVerify("xpath", "//Edit[@Name='No Of Digits *']", common.getData(file, "noOfDigits"));
        Thread.sleep(1000);
        saveMaster();
        closeMaster(common.getData(file,"customerType"));
        refresh();
        //validate
        navigateToMastersWhen2Steps(common.getData(file,"menu"), common.getData(file,"subMenu") );
        validateAndInactivate(common.getData(file,"customerType"), common.getData(file,"newCust"));
    }
}
