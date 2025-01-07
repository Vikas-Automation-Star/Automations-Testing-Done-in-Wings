package menuItems.inventory.reports.stock;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.inventory.reports.stock.StockAgeing;
import java.awt.*;
import java.io.IOException;

public class StockAgeingReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void stockAgeing() throws IOException, ParseException, InterruptedException, AWTException {
        StockAgeing ageing=new StockAgeing(driver);
        ageing.stckAgeing();
    }

    @AfterTest
    public void afterTest() throws IOException{
        appLogin.logout();
    }
}
