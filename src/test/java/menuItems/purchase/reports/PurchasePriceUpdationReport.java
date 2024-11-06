package menuItems.purchase.reports;

import com.wings.pages.purchase.reports.PurchasePriceUpdations;
import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class PurchasePriceUpdationReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void PurchasePriceUpdations() throws InterruptedException, AWTException {
        PurchasePriceUpdations ppu=new PurchasePriceUpdations(driver);
        ppu.PurchasePriceUpdation();
    }

    @AfterTest
    public void afterTest(){
        appLogin.logout();
    }
}
