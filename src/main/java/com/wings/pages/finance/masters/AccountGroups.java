package com.wings.pages.finance.masters;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public class AccountGroups extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public AccountGroups(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void accountGroups() throws InterruptedException, IOException, ParseException, AWTException {
        navigateToAccountGroups();
        Thread.sleep(1000);
        createMaster("xpath","//TreeItem[@Name='Account Groups']/TreeItem[@Name='All Account Groups']");
        Thread.sleep(1000);
        common.inputText("xpath", "//Edit[@Name='New Account Group *']", common.getData(dataFile, "accountName") + common.getRandom());
        Thread.sleep(2000);
        common.inputText("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));
        Thread.sleep(1000);
        //save
        saveMaster();
        //verify created master
        closeTransaction(common.getData(dataFile, "closeTab"));
        refresh();
        navigateToAccountGroups();
//        WebElement allGroups = common.findWebElement("xpath", "//TreeItem[@Name='Account Groups']/TreeItem[@Name='All Account Groups']");
//        allGroups.click();
        //fetch list and verify
        validateAndInactivate(common.getData(dataFile, "closeTab"), common.getData(dataFile, "accountName"));
    }
}