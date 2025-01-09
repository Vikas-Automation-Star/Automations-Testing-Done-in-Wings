package menuItems.sales.reports;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.reports.DeliveryReturnReportCode;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class DeliveryReturnReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void deliveryReturns() throws InterruptedException {
        DeliveryReturnReportCode reportCode = new DeliveryReturnReportCode(driver);
        reportCode.DeliveryReturns();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
