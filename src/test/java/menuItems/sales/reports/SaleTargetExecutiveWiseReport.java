package menuItems.sales.reports;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.reports.SalesTargetExecutiveWiseReportCode;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class SaleTargetExecutiveWiseReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void salesTargetExecWise() throws InterruptedException {
        SalesTargetExecutiveWiseReportCode executiveWiseReport = new SalesTargetExecutiveWiseReportCode(driver);
        executiveWiseReport.executiveWiseReport();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }

}
