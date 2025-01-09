package menuItems.finance.masters.ChartOfAccounts.Assests;

import com.wings.pages.AppLogin;
import com.wings.pages.finance.masters.ChartOfAccounts.Assests.CashMaster;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class Cash {
    WindowsDriver driver;
    AppLogin login = new AppLogin();
    String dataFile = "./src/main/resources/menuItems/finance/masters/cash.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = login.launchSingleUserApp();
        login.singleUserLogin();
    }

    @Test
    public void cashMaster() throws InterruptedException, IOException, ParseException, AWTException {
        CashMaster cashMaster = new CashMaster(driver, dataFile);
        cashMaster.cash();
    }

    @AfterTest
    public void afterTest() throws IOException {
        login.logout();
    }
}
