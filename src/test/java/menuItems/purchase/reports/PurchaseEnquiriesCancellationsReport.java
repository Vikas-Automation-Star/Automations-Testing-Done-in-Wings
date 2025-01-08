package menuItems.purchase.reports;

import com.wings.pages.purchase.reports.PurchaseEnquiriesCancellations;
import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class PurchaseEnquiriesCancellationsReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
        Allure.step("Before  Test PurchaseEnquiriesCancellation Report");
    }

    @Test
    public void purchaseEnquiriesCancellations() throws InterruptedException, AWTException {
        PurchaseEnquiriesCancellations pec=new PurchaseEnquiriesCancellations(driver);
        pec.purchaseEnquiriesCancellation();
    }

    @AfterTest
    public void afterTest() throws IOException{
        appLogin.logout();
        Allure.step("After  Test PurchaseEnquiriesCancellation Report");
    }
}
