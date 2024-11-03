package menuItems.finance.reports.FinancialStatements;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.finance.reports.FinancialStatements.FundsFlowReportCode;
import java.awt.*;
import java.io.IOException;

public class FundsFlowReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws InterruptedException, IOException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void fundsFlow() throws InterruptedException, AWTException {
        FundsFlowReportCode fundsFlowReportCode=new FundsFlowReportCode(driver);
        fundsFlowReportCode.fundsFlow();

    }

    @AfterTest
    public void afterTest(){
        appLogin.logout();
    }
}
