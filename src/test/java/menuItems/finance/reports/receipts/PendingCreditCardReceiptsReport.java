package menuItems.finance.reports.receipts;

import com.wings.pages.AppLogin;
import com.wings.pages.finance.reports.receipts.PendingCreditCardReceiptsReportCode;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class PendingCreditCardReceiptsReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void pendingCardReceipt() throws InterruptedException, AWTException {
        PendingCreditCardReceiptsReportCode receiptsReportCode = new PendingCreditCardReceiptsReportCode(driver);
        receiptsReportCode.pendingCardReceipt();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
