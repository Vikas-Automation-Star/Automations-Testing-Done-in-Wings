package menuItems.finance.reports.FinancialStatements;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.finance.reports.FinancialStatements.TrialBalanceReportCode;
import java.awt.*;
import java.io.IOException;

public class TrialBalanceReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws InterruptedException, IOException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void trialBalance() throws InterruptedException, AWTException {
        TrialBalanceReportCode trialBalanceReportCode=new TrialBalanceReportCode(driver);
        trialBalanceReportCode.trialBalance();
    }

    @AfterTest
    public void afterTest() throws  IOException{
        appLogin.logout();
    }

}
