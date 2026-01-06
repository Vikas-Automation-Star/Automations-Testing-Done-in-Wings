package tradeTesting.finance.masters.chartOfAccounts.ProfitAndLossAccount;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import tradeTesting.finance.masters.ChartOfAccounts.ProfitAndLossAccount.CreateOtherIncomesMaster;
import java.awt.*;
import java.io.IOException;

public class TestOtherIncomesMaster {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String dataFile="./src/main/resources/tradeAutomation/finance/masters/otherIncomes.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void testOtherIncomesMaster() throws IOException, ParseException, InterruptedException, AWTException {
        CreateOtherIncomesMaster otherIncomesMaster=new CreateOtherIncomesMaster(driver,dataFile);
        otherIncomesMaster.createOtherIncomesMaster();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}