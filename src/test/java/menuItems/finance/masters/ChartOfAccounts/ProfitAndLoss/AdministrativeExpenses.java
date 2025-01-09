package menuItems.finance.masters.ChartOfAccounts.ProfitAndLoss;

import com.wings.pages.AppLogin;
import com.wings.pages.finance.masters.ChartOfAccounts.ProfitAndLoss.AdministrativeExpensesMaster;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class AdministrativeExpenses {
    WindowsDriver driver;
    AppLogin login = new AppLogin();
    String dataFile = "./src/main/resources/menuItems/finance/masters/administrativeExpenses.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = login.launchSingleUserApp();
        login.singleUserLogin();
    }

    @Test
    public void administrativeExpenses() throws InterruptedException, IOException, ParseException, AWTException {
        AdministrativeExpensesMaster administrativeExpensesMaster = new AdministrativeExpensesMaster(driver, dataFile);
        administrativeExpensesMaster.administrativeExpenses();
    }

    @AfterTest
    public void afterTest() throws IOException {
        login.logout();
    }
}
