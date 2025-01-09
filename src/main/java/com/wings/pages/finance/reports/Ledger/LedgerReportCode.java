package com.wings.pages.finance.reports.Ledger;

import com.wings.pages.Transaction;
import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import com.wings.pages.Report;
import com.wings.utils.Common;

import java.util.List;

public class LedgerReportCode extends Transaction {
    WindowsDriver driver;
    Common common;

    public LedgerReportCode(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void ledgerReport() throws InterruptedException {
        common.clickElement("name", "Finance");
        common.clickElement("name", "Ledger");
        common.clickElement("xpath","//MenuItem[@Name='Ledger']");
        Thread.sleep(1200);
        common.clickElement("xpath","//Edit[@Name='Account']/Button[@Name='Open']");
        //here write method for selecting from DD
        //List<WebElement> elementList = common.findWebElements("xpath", "//Window[@Name='Options - Ledger']/Table/*[@Name='Data Panel']/*/*[contains(@Name,'MasterName row')]");
        List<WebElement> elementList = common.findWebElements("xpath", "//Table/*[@Name='Data Panel']/*[contains(@Name,'Row')]");
        System.out.println("Size :" + elementList.size());

        for (WebElement i : elementList) {
            String elementText = i.getText();
            System.out.println("Element Text: " + elementText);
            System.out.println("IsSelected Attribute: " + i.getAttribute("SelectionItem.IsSelected"));

            if (i.getAttribute("SelectionItem.IsSelected").equals("True")) {
                if (elementText.contains("219")) {
                    Thread.sleep(1500); // Adjusted sleep time
                    i.click();
                    i.sendKeys(Keys.ENTER);

                    break;
                } else {
                    i.sendKeys(Keys.DOWN);
                }
            }
        }

//        for (WebElement i : elementList) {
//            System.out.println(i.getText());
//            System.out.println(i.getAttribute("SelectionItem.IsSelected"));
//            if (i.getAttribute("SelectionItem.IsSelected").equals("True")) {
//
//                if (!i.getText().contains("259")) {
//                    i.sendKeys(Keys.DOWN);
//                }
//                else if (i.getText().contains("259")){
//                    i.click();
//                    Thread.sleep(1200);
//                    i.sendKeys(Keys.ENTER);
//                    break;
//                }
//            }
//        }
        Thread.sleep(5000);
        common.clickElement("xpath","//Pane/Button[@Name='Submit']");
//        super.selectDropDownReport("Bank Account");
        Thread.sleep(5000);
//        super.bulkVerifyReport("ORFCC 2");
        super.closeReport("Ledger");
    }
}
