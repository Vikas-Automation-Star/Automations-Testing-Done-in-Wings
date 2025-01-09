package menuItems.finance.reports.partyAdjustments;

import com.wings.pages.AppLogin;
import com.wings.pages.finance.reports.partyAdjustments.DebitNoteOnCustomersReportCode;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class DebitNoteOnCustomersReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void debitNoteOnCustomerReport() throws InterruptedException, AWTException {
        DebitNoteOnCustomersReportCode noteOnCustomersReportCode = new DebitNoteOnCustomersReportCode(driver);
        noteOnCustomersReportCode.debitNoteonCustomerReport();

    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
