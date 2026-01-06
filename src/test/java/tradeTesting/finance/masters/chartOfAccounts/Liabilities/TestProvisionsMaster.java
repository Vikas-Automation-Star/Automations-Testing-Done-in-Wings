package tradeTesting.finance.masters.chartOfAccounts.Liabilities;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import tradeTesting.finance.masters.ChartOfAccounts.Liabilities.CreateProvisionsMaster;
import java.awt.*;
import java.io.IOException;

public class TestProvisionsMaster {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String file="./src/main/resources/tradeAutomation/finance/masters/provisionsMaster.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void testProvisionsMaster() throws InterruptedException, IOException, ParseException, AWTException {
        CreateProvisionsMaster createProvisionsMaster=new CreateProvisionsMaster(driver,file);
        createProvisionsMaster.createProvisionsMasterTrade();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}