package menuItems.finance.reports.receipts;

import com.wings.pages.AppLogin;
import com.wings.pages.finance.reports.receipts.CashReceiptsReportCode;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class CashReceiptsReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void cashRecipts() throws InterruptedException, AWTException {
        CashReceiptsReportCode receiptsReportCode = new CashReceiptsReportCode(driver);
        receiptsReportCode.cashReport();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
