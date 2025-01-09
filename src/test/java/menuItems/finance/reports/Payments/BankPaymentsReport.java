package menuItems.finance.reports.Payments;

import com.wings.pages.AppLogin;
import com.wings.pages.finance.reports.payments.BankPaymentsReportCode;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class BankPaymentsReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void bankPayment() throws InterruptedException, AWTException {
        BankPaymentsReportCode paymentsReportCode = new BankPaymentsReportCode(driver);
        paymentsReportCode.bankPayment();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
