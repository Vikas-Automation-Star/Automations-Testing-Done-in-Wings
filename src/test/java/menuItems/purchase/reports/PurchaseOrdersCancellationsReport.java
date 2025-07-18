package menuItems.purchase.reports;

import com.wings.pages.AppLogin;
import com.wings.pages.purchase.reports.PurchaseOrderCancellations;
import io.appium.java_client.windows.WindowsDriver;

import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class PurchaseOrdersCancellationsReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void purchaseOrderCancellations() throws InterruptedException, AWTException {
        PurchaseOrderCancellations poc = new PurchaseOrderCancellations(driver);
        poc.purchaseOrderCancellation();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
