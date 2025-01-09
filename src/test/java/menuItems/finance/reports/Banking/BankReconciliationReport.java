package menuItems.finance.reports.Banking;

import com.wings.pages.AppLogin;
import com.wings.pages.finance.reports.Banking.BankReconciliationReportCode;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class BankReconciliationReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void bankReconciliation() throws InterruptedException, AWTException {
        BankReconciliationReportCode reconciliationReportCode = new BankReconciliationReportCode(driver);
        reconciliationReportCode.reconciliation();

    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
