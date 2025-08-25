package menuItems.sales.reports;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.reports.PendingSalesOrderReportCode;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class PendingSalesOrderReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void pendingSalesOrder() throws Exception {
        PendingSalesOrderReportCode salesOrderReportCode = new PendingSalesOrderReportCode(driver);
        salesOrderReportCode.PendingsalesOrderReport();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
