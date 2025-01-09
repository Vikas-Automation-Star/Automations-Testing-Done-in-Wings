package menuItems.finance.reports.partyAdjustments;

import com.wings.pages.AppLogin;
import com.wings.pages.finance.reports.partyAdjustments.AdjustPartyBillsReportCode;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class AdjustPartyBillsReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws InterruptedException, IOException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void adjustPartyBillsReport() throws InterruptedException, AWTException {
        AdjustPartyBillsReportCode partyBillsReportCode = new AdjustPartyBillsReportCode(driver);
        partyBillsReportCode.adjustPartyBills();

    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
