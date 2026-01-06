package tradeTesting.finance.masters.chartOfAccounts.ProfitAndLossAccount;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import tradeTesting.finance.masters.ChartOfAccounts.ProfitAndLossAccount.CreateAdministrativeExpensesMaster;
import java.awt.*;
import java.io.IOException;

public class TestAdministrativeExpensesMaster {
    WindowsDriver driver;
    String file = "./src/main/resources/tradeAutomation/finance/masters/administrativeExpenses.json";
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void testAdministrativeExpensesMaster() throws IOException, ParseException, InterruptedException, AWTException {
        CreateAdministrativeExpensesMaster administrativeExpensesMaster=new CreateAdministrativeExpensesMaster(driver,file);
        administrativeExpensesMaster.createAdministrativeExpensesMaster();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}