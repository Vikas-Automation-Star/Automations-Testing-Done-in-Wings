package menuItems.purchases.reports;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.purchases.reports.PurchaseEnquryReport;
import java.io.IOException;

public class PurchaseEnquiryReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void newPurchaseEnquiryTrsncn() throws InterruptedException {
        PurchaseEnquryReport enquryReport=new PurchaseEnquryReport(driver);
        enquryReport.enquiryReport();
    }

    @AfterTest
    public void afterTest(){
        appLogin.logout();
    }
}
