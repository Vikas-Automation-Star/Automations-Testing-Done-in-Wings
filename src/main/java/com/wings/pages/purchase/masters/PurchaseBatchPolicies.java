package com.wings.pages.purchase.masters;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class PurchaseBatchPolicies extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public PurchaseBatchPolicies(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void batchPolicies() throws InterruptedException, IOException, ParseException, AWTException {
        common.clickElement("name", "Purchase");
        common.clickElement("name", "Purchase Batch Policies");
        Thread.sleep(2500);
        super.createMaster("xpath", "//TreeItem[@Name='Purchase Batch Policies']/TreeItem[@Name='All Purchase Batch Policies']");
        Thread.sleep(2000);
        super.inputTextWithValidation("xpath","//Edit[@Name='New Purchase Batch Policy *']", common.getData(dataFile,"newPurchaseBatchPolice")+common.getRandom());
        common.clickElement("xpath","//Edit[@Name='Batch Policy Type *']/Button[@Name='Open']");
        Thread.sleep(2000);
        Robot robot=new Robot();
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        super.inputTextWithValidation("xpath","//Edit[@Name='Seperator']", common.getData(dataFile,"separator"));
        super.inputTextWithValidation("xpath","//Edit[@Name='Description']", common.getData(dataFile,"description"));
        Thread.sleep(2000);
        common.clickElement("xpath","//Text[@Name='Purchase Batch Policy']/following-sibling::Button[@Name='...']");
        WebElement element= common.findWebElement("xpath","//Edit[@Name='Include Option * Row 0, Not sorted.']");
        element.click();
        element.sendKeys(common.getData(dataFile,"includeOption"),Keys.ENTER);
        common.clickElement("xpath","//Edit[@Name='Policy Sequence * Row 0, Not sorted.']");
        common.inputText("xpath","//Edit[@Name='Policy Sequence * Row 0, Not sorted.']",common.getData(dataFile,"policySequence"));
        WebElement element1= common.findWebElement("xpath","//Edit[@Name='Policy Option Format * Row 0, Not sorted.']");
        element1.click();
        element.sendKeys(common.getData(dataFile,"policyOptionFormate"),Keys.ENTER);
        common.clickElement("xpath","//Button[@Name='Ok']");
        super.saveAfterMasterCreate();
        super.closeMaster("Purchase Batch Policies");
        Allure.step("Purchase Batch Policies");
    }
}
