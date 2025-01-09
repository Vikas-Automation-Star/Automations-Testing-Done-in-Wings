package menuItems.sales.reports;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.reports.PendingSalesQuotationsReportCode;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class PendingSalesQuotationsReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();

    }

    @Test
    public void pendingSalesQuotation() throws InterruptedException {
        PendingSalesQuotationsReportCode pendingQuotations = new PendingSalesQuotationsReportCode(driver);
        pendingQuotations.pendingQuotationReport();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }


}
