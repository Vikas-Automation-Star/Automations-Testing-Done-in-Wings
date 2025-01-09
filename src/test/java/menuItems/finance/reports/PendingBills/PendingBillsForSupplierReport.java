package menuItems.finance.reports.PendingBills;

import com.wings.pages.AppLogin;
import com.wings.pages.finance.reports.PendingBills.PendingBillsForSupplierReportCode;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class PendingBillsForSupplierReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, ParseException, InterruptedException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void pendingBillsForSupplier() throws IOException, ParseException, InterruptedException, AWTException {
        PendingBillsForSupplierReportCode billsForSupplierReportCode = new PendingBillsForSupplierReportCode(driver);
        billsForSupplierReportCode.pendingBillsForSupplier();


    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
