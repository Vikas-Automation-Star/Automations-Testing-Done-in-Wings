package menuItems.inventory.reports;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.inventory.reports.StockConversionReprt;

import java.awt.*;
import java.io.IOException;

public class StockConversionReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void stockConversion() throws  InterruptedException, AWTException {
        StockConversionReprt conversionReprt=new StockConversionReprt(driver);
        conversionReprt.stockConversion();
    }

    @AfterTest
    public void afterTest(){
        appLogin.logout();
    }
}
