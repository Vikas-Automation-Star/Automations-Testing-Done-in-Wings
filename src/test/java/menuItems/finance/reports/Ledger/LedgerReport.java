package menuItems.finance.reports.Ledger;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.finance.reports.Ledger.LedgerReportCode;

import java.awt.*;
import java.io.IOException;

public class LedgerReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void ledgerReport() throws  InterruptedException, AWTException {
        LedgerReportCode reportCode=new LedgerReportCode(driver);
        reportCode.ledgerReport();

    }

    @AfterTest
    public void afterTest() throws IOException{
        appLogin.logout();
    }
}

