package menuItems.finance.masters.ChartOfAccounts.Liabilities;

import com.wings.pages.AppLogin;
import com.wings.pages.finance.masters.ChartOfAccounts.Liabilities.ProvisionsMaster;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class Provisions {

    WindowsDriver driver;
    AppLogin login = new AppLogin();
    String dataFile = "./src/main/resources/menuItems/finance/masters/provisions.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = login.launchSingleUserApp();
        login.singleUserLogin();
    }

    @Test
    public void provisions() throws InterruptedException, IOException, ParseException, AWTException {
        ProvisionsMaster provisionsMaster = new ProvisionsMaster(driver, dataFile);
        provisionsMaster.provisions();
    }

    @AfterTest
    public void afterTest() throws IOException {
        login.logout();
    }
}
