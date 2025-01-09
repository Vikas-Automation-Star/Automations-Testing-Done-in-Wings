package menuItems.inventory.reports.stock;

import com.wings.pages.AppLogin;
import com.wings.pages.inventory.reports.stock.StockList;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class StockListReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void stockListing() throws IOException, ParseException, InterruptedException, AWTException {
        StockList stockList = new StockList(driver);
        stockList.stckList();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
