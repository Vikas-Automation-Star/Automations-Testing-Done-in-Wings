package menuItems.finance.reports.openingBalances;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.finance.reports.OpeningBalance.PartyOpeningBalanceReportCode;
import java.awt.*;
import java.io.IOException;

public class PartyOpeningBalanceReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void partyBalance() throws  InterruptedException, AWTException {
        PartyOpeningBalanceReportCode balanceReportCode=new PartyOpeningBalanceReportCode(driver);
        balanceReportCode.partyBalanceReport();

    }

    @AfterTest
    public void afterTest(){
        appLogin.logout();
    }

}
