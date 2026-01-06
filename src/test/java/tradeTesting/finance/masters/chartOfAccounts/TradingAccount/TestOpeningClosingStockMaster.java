package tradeTesting.finance.masters.chartOfAccounts.TradingAccount;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import tradeTesting.finance.masters.ChartOfAccounts.TradingAccount.CreateOpeningClosingStocksMaster;
import java.awt.*;
import java.io.IOException;

public class TestOpeningClosingStockMaster {
    WindowsDriver driver;
    String file = "./src/main/resources/tradeAutomation/finance/masters/openingClosingStockMaster.json";
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void testOpeningClosingStockMaster() throws IOException, ParseException, InterruptedException, AWTException {
        CreateOpeningClosingStocksMaster openingClosingStocksMaster=new CreateOpeningClosingStocksMaster(driver,file);
        openingClosingStocksMaster.createOpeningClosingStockMasterTrade();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}