package com.wings.pages.purchase.reports;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.awt.*;
import java.io.IOException;
import java.util.List;

import static java.awt.event.KeyEvent.VK_DOWN;
import static java.awt.event.KeyEvent.VK_ENTER;

public class Designer extends Transaction {
    WindowsDriver driver,rootDriver;
    Common common;
    String dataFile;

    public Designer(WindowsDriver driver,String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile=file;
    }

    public void designer() throws InterruptedException, IOException, AWTException {
        common.clickElement("name", "Purchase");
        common.clickElement("name", "Enquiries");
        common.clickElement("xpath", "//MenuItem[@Name='Purchase Enquiries'][2]");
        Thread.sleep(1500);
        common.clickElement("xpath", "//Button[@Name='Submit']");
        Thread.sleep(1500);
//        common.clickElement("xpath", "//Button[@Name='Designer']");
//        common.clickElement("xpath", "//TabItem[@Name='Data']");
//        common.clickElement("xpath", "//Button[@Name='Filter']");
//        rootDriver=common.initializeDriver("Root");
//        System.out.println("root navigated");
//        Thread.sleep(2000);
//        List<WebElement> columnsList=common.findWebElements("xpath","//Window[@Name='Filter']/Table/*[@Name='Data Panel']/ListItem[starts-with(@Name,'Row ')]");
//        System.out.println("size :"+columnsList.size());
//        for (int i=0;i<=8;i++){
//            WebElement v=columnsList.get(i);
//            System.out.println(v.getText());
//            if (v.getText().contains("VoucherNo")){
//                v.click();
//                v.sendKeys(Keys.ARROW_RIGHT,"=",Keys.ENTER,Keys.ARROW_RIGHT,"PE2");
//            }
//        }
//        common.clickElement("xpath", "//Button[@Name='Apply']");

        reportDesigner(rootDriver,"VoucherNo","=","PE2");







//        common.clickElement("xpath", "//TabItem[@Name='Format']");
//        common.clickElement("xpath", "//TabItem[@Name='Special Columns']");
//        common.clickElement("xpath", "//TabItem[@Name='Master Related']");
//        common.clickElement("xpath", "//TabItem[@Name='Dates']");
//        common.clickElement("xpath", "//TabItem[@Name='Transaction Fields']");
//        common.clickElement("xpath", "//TabItem[@Name='Account and Stock Balances']");
//        common.clickElement("xpath", "//TabItem[@Name='Report Options']");


//        super.bulkVerifyReport("");
//        super.closeReport("Purchase Enquiries");
    }
}
