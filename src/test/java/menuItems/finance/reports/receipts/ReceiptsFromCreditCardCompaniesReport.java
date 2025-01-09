package menuItems.finance.reports.receipts;

import com.wings.pages.AppLogin;
import com.wings.pages.finance.reports.receipts.ReceiptsFromCreditCardCompaniesReportCode;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class ReceiptsFromCreditCardCompaniesReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void receiptsFromCreditCard() throws InterruptedException, AWTException {
        ReceiptsFromCreditCardCompaniesReportCode creditCardCompaniesReportCode = new ReceiptsFromCreditCardCompaniesReportCode(driver);
        creditCardCompaniesReportCode.creditCardCompany();

    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
