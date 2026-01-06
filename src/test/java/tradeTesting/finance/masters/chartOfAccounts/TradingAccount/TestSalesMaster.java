package tradeTesting.finance.masters.chartOfAccounts.TradingAccount;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import tradeTesting.finance.masters.ChartOfAccounts.TradingAccount.CreateSalesMaster;
import java.awt.*;
import java.io.IOException;

public class TestSalesMaster {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String dataFile="./src/main/resources/tradeAutomation/finance/masters/salesMaster.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void testSalesMaster() throws IOException, ParseException, InterruptedException, AWTException {
        CreateSalesMaster salesMaster=new CreateSalesMaster(driver,dataFile);
        salesMaster.createSalesMasterTrade();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}