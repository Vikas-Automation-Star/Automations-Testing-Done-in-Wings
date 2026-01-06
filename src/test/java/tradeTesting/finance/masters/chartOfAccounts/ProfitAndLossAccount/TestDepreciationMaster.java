package tradeTesting.finance.masters.chartOfAccounts.ProfitAndLossAccount;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import tradeTesting.finance.masters.ChartOfAccounts.ProfitAndLossAccount.CreateDepreciationMaster;

import java.awt.*;
import java.io.IOException;

public class TestDepreciationMaster {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String dataFile="./src/main/resources/tradeAutomation/finance/masters/depreciationMaster.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void testDepreciationMaster() throws IOException, ParseException, InterruptedException, AWTException {
        CreateDepreciationMaster createDepreciationMaster=new CreateDepreciationMaster(driver,dataFile);
        createDepreciationMaster.createDepreciationMaster();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}