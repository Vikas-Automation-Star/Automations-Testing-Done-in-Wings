package menuItems.finance.reports.PendingBills;

import com.wings.pages.AppLogin;
import com.wings.pages.finance.reports.PendingBills.PendingBillsForCustomersReportCode;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class PendingBillsForCustomersReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws Exception {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void pendingBillsForCustomer() throws Exception, AWTException {
        PendingBillsForCustomersReportCode billsForCustomersReportCode = new PendingBillsForCustomersReportCode(driver);
        billsForCustomersReportCode.pendingBillsForCustomers();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
