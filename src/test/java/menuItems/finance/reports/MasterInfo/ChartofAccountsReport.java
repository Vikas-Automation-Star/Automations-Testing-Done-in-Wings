package menuItems.finance.reports.MasterInfo;

import com.wings.pages.AppLogin;
import com.wings.pages.finance.reports.MasterInfo.ChartofAccountsReportCode;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class ChartofAccountsReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException, IOException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void chartOfAccount() throws InterruptedException, AWTException {
        ChartofAccountsReportCode accountsReportCode = new ChartofAccountsReportCode(driver);
        accountsReportCode.chartOfAccountsReport();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
