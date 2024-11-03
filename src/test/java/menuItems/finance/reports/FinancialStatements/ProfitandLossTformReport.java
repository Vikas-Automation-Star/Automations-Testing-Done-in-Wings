package menuItems.finance.reports.FinancialStatements;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.finance.reports.FinancialStatements.ProfitAndLossTFormReportCode;
import java.awt.*;
import java.io.IOException;

public class ProfitandLossTformReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws InterruptedException, IOException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void profitAndLossTform() throws InterruptedException, AWTException {
        ProfitAndLossTFormReportCode lossTFormReportCode=new ProfitAndLossTFormReportCode(driver);
        lossTFormReportCode.profitLossTform();

    }

    @AfterTest
    public void afterTest(){
        appLogin.logout();
    }

}
