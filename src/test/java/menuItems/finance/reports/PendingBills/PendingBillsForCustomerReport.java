package menuItems.finance.reports.PendingBills;

import com.wings.pages.AppLogin;
import com.wings.pages.finance.reports.PendingBills.PendingBillsForCustomerReportCode;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class PendingBillsForCustomerReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, ParseException, InterruptedException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void pendingBillsForCustomer() throws IOException, ParseException, InterruptedException, AWTException {
        PendingBillsForCustomerReportCode billsForCustomerReportCode = new PendingBillsForCustomerReportCode(driver);
        billsForCustomerReportCode.pendingBillsForCustomer();

    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }

}
