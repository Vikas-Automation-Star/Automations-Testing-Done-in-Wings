package menuItems.finance.reports.FinancialStatements;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.finance.reports.FinancialStatements.ProfitAndLossReportCode;
import java.awt.*;
import java.io.IOException;

public class ProfitAndLossReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws InterruptedException, IOException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void profitAndLoss() throws InterruptedException, AWTException {
        ProfitAndLossReportCode lossReportCode=new ProfitAndLossReportCode(driver);
        lossReportCode.profitLoss();

    }

    @AfterTest
    public void afterTest(){
        appLogin.logout();
    }
}
