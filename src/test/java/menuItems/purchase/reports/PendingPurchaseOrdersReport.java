package menuItems.purchase.reports;

import com.wings.pages.AppLogin;
import com.wings.pages.purchase.reports.PendingPurchaseOrders;
import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class PendingPurchaseOrdersReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void pendingPurchaseOrders() throws InterruptedException, AWTException {
        PendingPurchaseOrders ppo = new PendingPurchaseOrders(driver);
        ppo.pendingPurchaseOrder();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
