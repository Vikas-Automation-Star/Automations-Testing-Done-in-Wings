package menuItems.finance.reports.Banking;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.finance.reports.Banking.CashDepositAndWithdrawlsReportCode;
import java.awt.*;
import java.io.IOException;

public class CashDepositAndWithdrawlsReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void cashDepositAdnWithdrawl() throws  InterruptedException, AWTException {
        CashDepositAndWithdrawlsReportCode withdrawlsReportCode=new CashDepositAndWithdrawlsReportCode(driver);
        withdrawlsReportCode.cashDeposit();
    }

    @AfterTest
    public void afterTest() throws IOException{
        appLogin.logout();
    }
}
