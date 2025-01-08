package menuItems.purchase.reports;

import com.wings.pages.purchase.reports.PurchaseQuotations;
import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class PurchaseQuotationsReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
        Allure.step("Before Test PurchaseQuotations Report");
    }

    @Test
    public void purchaseQuotations() throws InterruptedException, AWTException {
        PurchaseQuotations pq=new PurchaseQuotations(driver);
        pq.purchaseQuotation();
    }

    @AfterTest
    public void afterTest() throws IOException{
        appLogin.logout();
        Allure.step("After Test PurchaseQuotations Report");
    }
}
