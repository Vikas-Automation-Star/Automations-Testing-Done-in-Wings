package menuItems.finance.masters.ChartOfAccounts.ProfitAndLoss;

import com.wings.pages.AppLogin;
import com.wings.pages.finance.masters.ChartOfAccounts.ProfitAndLoss.PurchasesMaster;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class Purchases {
    WindowsDriver driver;
    AppLogin login = new AppLogin();
    String dataFile = "./src/main/resources/menuItems/finance/masters/purchases.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = login.launchSingleUserApp();
        login.singleUserLogin();
    }

    @Test
    public void purchases() throws InterruptedException, IOException, ParseException, AWTException {
        PurchasesMaster purchasesMaster = new PurchasesMaster(driver, dataFile);
        purchasesMaster.purchases();
    }

    @AfterTest
    public void afterTest() throws IOException {
        login.logout();
    }
}
