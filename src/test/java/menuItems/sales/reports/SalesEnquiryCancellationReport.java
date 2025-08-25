package menuItems.sales.reports;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.reports.SalesEnquiryCancellationReportCode;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class SalesEnquiryCancellationReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void quotation() throws Exception {
        SalesEnquiryCancellationReportCode reports = new SalesEnquiryCancellationReportCode(driver);
        reports.enquiryCancelReport();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }

}
