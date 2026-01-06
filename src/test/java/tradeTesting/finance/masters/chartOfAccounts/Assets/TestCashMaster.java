package tradeTesting.finance.masters.chartOfAccounts.Assets;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import tradeTesting.finance.masters.ChartOfAccounts.Assets.CreateCashMaster;
import java.awt.*;
import java.io.IOException;

public class TestCashMaster {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String file="./src/main/resources/tradeAutomation/finance/masters/cashMaster.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void testCashMaster() throws InterruptedException, IOException, ParseException, AWTException {
        CreateCashMaster createCashMaster=new CreateCashMaster(driver,file);
        createCashMaster.createCashMasterTrade();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}