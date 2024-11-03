package menuItems.purchases.reports;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.purchases.reports.PendingPurchaseEnqryReport;

import java.io.IOException;

public class PendingPurchaseEnquiryReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void pendingPurchaseEnquiryTrsncn() throws IOException, ParseException, InterruptedException {
        PendingPurchaseEnqryReport enqryReport=new PendingPurchaseEnqryReport(driver);
        enqryReport.pendingEnquiryReport();

    }

    @AfterTest
    public void afterTest(){
        appLogin.logout();
    }
}
