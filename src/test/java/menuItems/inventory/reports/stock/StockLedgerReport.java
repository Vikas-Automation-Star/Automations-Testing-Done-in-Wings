package menuItems.inventory.reports.stock;

import com.wings.pages.AppLogin;
import com.wings.pages.inventory.reports.stock.StockLedger;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class StockLedgerReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void stockLedger() throws IOException, ParseException, InterruptedException, AWTException {
        StockLedger stockLedger = new StockLedger(driver);
        stockLedger.stckLedger();

    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
