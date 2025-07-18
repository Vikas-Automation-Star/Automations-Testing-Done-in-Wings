package menuItems.purchase.reports;

import com.wings.pages.AppLogin;
import com.wings.pages.purchase.reports.PendingMaterialReceipts;
import io.appium.java_client.windows.WindowsDriver;

import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class PendingMaterialReceiptsReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void pendingMaterialReceipts() throws InterruptedException, AWTException {
        PendingMaterialReceipts pmr = new PendingMaterialReceipts(driver);
        pmr.pendingMaterialReceipt();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
