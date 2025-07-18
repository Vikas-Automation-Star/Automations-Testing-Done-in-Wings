package menuItems.purchase.reports;


import com.wings.pages.AppLogin;
import com.wings.pages.purchase.reports.PendingPurchaseOrdersWithReceipts;
import io.appium.java_client.windows.WindowsDriver;

import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class PendingPurchaseOrdersWithReceiptsReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void pendingPurchaseOrdersWithReceipt() throws InterruptedException, AWTException {
        PendingPurchaseOrdersWithReceipts ppowr = new PendingPurchaseOrdersWithReceipts(driver);
        ppowr.pendingPurchaseOrdersWithReceipt();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }

}
