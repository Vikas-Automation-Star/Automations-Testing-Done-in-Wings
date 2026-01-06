package tradeTesting.finance.masters.chartOfAccounts.Assets;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import tradeTesting.finance.masters.ChartOfAccounts.Assets.CreateSundryDebtorsMaster;
import java.awt.*;
import java.io.IOException;

public class TestSundryDebtorsMaster {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String file="./src/main/resources/tradeAutomation/finance/masters/sundryDebtorsMaster.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void testSundryMaster() throws InterruptedException, IOException, ParseException, AWTException {
        CreateSundryDebtorsMaster createSundryDebtorsMaster=new CreateSundryDebtorsMaster(driver,file);
        createSundryDebtorsMaster.createSundryDebtorsMasterTrade();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}