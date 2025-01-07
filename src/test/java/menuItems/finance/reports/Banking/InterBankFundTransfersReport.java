package menuItems.finance.reports.Banking;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.finance.reports.Banking.InterBankFundTransfersReportCode;
import java.awt.*;
import java.io.IOException;

public class InterBankFundTransfersReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void interBankFund() throws  InterruptedException, AWTException {
        InterBankFundTransfersReportCode transfersReportCode=new InterBankFundTransfersReportCode(driver);
        transfersReportCode.bankFundTransfer();

    }

    @AfterTest
    public void afterTest() throws IOException{
        appLogin.logout();
    }
}
