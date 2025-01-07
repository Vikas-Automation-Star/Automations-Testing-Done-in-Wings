package menuItems.purchase.reports;

import com.wings.pages.purchase.reports.PendingPurchaseEnquiries;
import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class PendingPurchaseEnquiriesReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void test() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
        Allure.step("Before Test PendingPurchaseEnquiries Reports");

    }

    @Test
    public void pendingPurchaseEnquiriesReport() throws InterruptedException, AWTException {
        PendingPurchaseEnquiries ppe=new PendingPurchaseEnquiries(driver);
        ppe.pendingPurchaseEnquiry();

    }

    @AfterTest
    public void afterTest() throws IOException{
        appLogin.logout();
        Allure.step("After Test PendingPurchaseEnquiries Reports");

    }
}
