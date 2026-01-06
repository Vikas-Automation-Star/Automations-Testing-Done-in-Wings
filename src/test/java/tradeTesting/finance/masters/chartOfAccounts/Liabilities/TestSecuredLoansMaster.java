package tradeTesting.finance.masters.chartOfAccounts.Liabilities;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import tradeTesting.finance.masters.ChartOfAccounts.Liabilities.CreateSecuredLoansMaster;
import java.awt.*;
import java.io.IOException;

public class TestSecuredLoansMaster {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String file="./src/main/resources/tradeAutomation/finance/masters/securedLoansMaster.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void testSecuredLoansMaster() throws InterruptedException, IOException, ParseException, AWTException {
        CreateSecuredLoansMaster createSecuredLoansMaster=new CreateSecuredLoansMaster(driver,file);
        createSecuredLoansMaster.createSecuredLoansMasterTrade();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
