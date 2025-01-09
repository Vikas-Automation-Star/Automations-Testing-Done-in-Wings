package menuItems.finance.masters.ChartOfAccounts.Assests;

import com.wings.pages.AppLogin;
import com.wings.pages.finance.masters.ChartOfAccounts.Assests.FixedAssestsMaster;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class FixedAssests {
    WindowsDriver driver;
    AppLogin login = new AppLogin();
    String dataFile = "./src/main/resources/menuItems/finance/masters/fixedAssests.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = login.launchSingleUserApp();
        login.singleUserLogin();
    }

    @Test
    public void fixedAssetMaster() throws InterruptedException, IOException, ParseException, AWTException {
        FixedAssestsMaster assestsMaster = new FixedAssestsMaster(driver, dataFile);
        assestsMaster.fixedAsset();
    }

    @AfterTest
    public void afterTest() throws IOException {
        login.logout();
    }
}
