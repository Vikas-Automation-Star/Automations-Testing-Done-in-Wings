package com.wings.pages.purchases.transactions;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import com.wings.utils.Common;

import java.io.IOException;

public class PurchaseOrdrCancellation{
    WindowsDriver driver;
    Common common;
    String dataFile;

    public PurchaseOrdrCancellation(WindowsDriver driver, String file) {
        this.driver = driver;
        common = new Common(this.driver);
        dataFile=file;
    }

    public void purchaseOrderCancellation() throws InterruptedException, IOException, ParseException {
        common.clickElement("name", "Purchase");
        common.clickElement("name", "Orders");
        common.clickElement("xpath","//MenuItem[@Name='Purchase Orders Cancellation']");
        Thread.sleep(1000);
        System.out.println("old-"+common.findWebElement("xpath","//Text[@Name='Last Saved :']/following-sibling::Text").getAttribute("Name"));

        WebElement vocher= common.findWebElement("xpath","//Edit[@Name='Voucher Type']");
        vocher.sendKeys(Keys.TAB,Keys.TAB,Keys.TAB,Keys.TAB,Keys.TAB,Keys.TAB,Keys.TAB,Keys.TAB,Keys.TAB,Keys.TAB,common.getData(dataFile,"productCode"),Keys.ENTER,Keys.TAB,Keys.TAB, common.getData(dataFile,"Quantity"),Keys.TAB,Keys.TAB, common.getData(dataFile,"MRP"),Keys.TAB, common.getData(dataFile,"UnitRate"),Keys.TAB,Keys.TAB,Keys.TAB);
        //save
        common.clickElement("xpath","//Button[@Name='Save']");
        common.clickElement("xpath","//Button[@Name='Yes']");
        common.clickElement("xpath","//Window[@Name='Transaction saved.']/Button[@Name='OK']");
        Thread.sleep(1000);
        System.out.println("new:-"+common.findWebElement("xpath","//Text[@Name='Last Saved :']/following-sibling::Text").getAttribute("Name"));
        Thread.sleep(2000);

        common.clickElement("xpath", "//TabItem[@Name='Purchase Orders Cancellation']/Button[@Name='Close']");
    }
}
