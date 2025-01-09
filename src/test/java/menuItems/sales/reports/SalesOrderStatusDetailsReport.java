package menuItems.sales.reports;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.reports.SalesOrderStatusDetailsReportCode;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class SalesOrderStatusDetailsReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void salesOrderStatusDetails() throws IOException, ParseException, InterruptedException {
        SalesOrderStatusDetailsReportCode salesOrderStatusDetailsReportCode = new SalesOrderStatusDetailsReportCode(driver);
        salesOrderStatusDetailsReportCode.salesOrderStatusDetails();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
