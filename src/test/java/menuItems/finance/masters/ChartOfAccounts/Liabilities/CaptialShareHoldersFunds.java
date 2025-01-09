package menuItems.finance.masters.ChartOfAccounts.Liabilities;

import com.wings.pages.AppLogin;
import com.wings.pages.finance.masters.ChartOfAccounts.Liabilities.CapitalShareHoldersFundsMaster;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class CaptialShareHoldersFunds {

    WindowsDriver driver;
    AppLogin login = new AppLogin();
    String dataFile = "./src/main/resources/menuItems/finance/masters/captialShareHolders.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = login.launchSingleUserApp();
        login.singleUserLogin();
    }

    @Test
    public void shareHolders() throws InterruptedException, IOException, ParseException, AWTException {
        CapitalShareHoldersFundsMaster shareHoldersFundsMaster = new CapitalShareHoldersFundsMaster(driver, dataFile);
        shareHoldersFundsMaster.shareHolders();

    }

    @AfterTest
    public void afterTest() throws IOException {
        login.logout();
    }
}
