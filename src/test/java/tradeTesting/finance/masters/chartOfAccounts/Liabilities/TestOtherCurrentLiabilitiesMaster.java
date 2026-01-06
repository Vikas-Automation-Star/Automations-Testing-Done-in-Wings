package tradeTesting.finance.masters.chartOfAccounts.Liabilities;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import tradeTesting.finance.masters.ChartOfAccounts.Liabilities.CreateOtherCurrentLiabilitiesMaster;
import java.awt.*;
import java.io.IOException;

public class TestOtherCurrentLiabilitiesMaster {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String file="./src/main/resources/tradeAutomation/finance/masters/otherCurrentLiabilitiesMaster.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void testOtherCurrentLiabilitiesMaster() throws InterruptedException, IOException, ParseException, AWTException {
        CreateOtherCurrentLiabilitiesMaster createOtherCurrentLiabilitiesMaster=new CreateOtherCurrentLiabilitiesMaster(driver,file);
        createOtherCurrentLiabilitiesMaster.createOtherCurrentLiabilitiesMasterTrade();

    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}