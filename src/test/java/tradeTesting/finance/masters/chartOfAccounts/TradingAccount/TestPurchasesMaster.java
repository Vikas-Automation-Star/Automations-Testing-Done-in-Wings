package tradeTesting.finance.masters.chartOfAccounts.TradingAccount;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import tradeTesting.finance.masters.ChartOfAccounts.TradingAccount.CreatePurchasesMaster;

import java.awt.*;
import java.io.IOException;

public class TestPurchasesMaster {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String dataFile="./src/main/resources/tradeAutomation/finance/masters/purchasesMaster.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void testPurchasesMaster() throws IOException, ParseException, InterruptedException, AWTException {
        CreatePurchasesMaster createPurchasesMaster=new CreatePurchasesMaster(driver,dataFile);
        createPurchasesMaster.createPurchasesMasterTrade();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}