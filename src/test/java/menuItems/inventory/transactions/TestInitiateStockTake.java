package menuItems.inventory.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.inventory.transactions.InitiateStockTake;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;


public class TestInitiateStockTake {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    Common common;
    String file = "./src/main/resources/menuItems/inventory/transactions/initiateStockTake.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        common=new Common(driver);
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin(common.getData(file,"initiateStockTake","userName"),common.getData(file,"initiateStockTake","password"));
    }
    @Test
    public void initiateStockTake() throws InterruptedException, AWTException, IOException, ParseException {
        InitiateStockTake initiateStockTake=new InitiateStockTake(driver,file);
        initiateStockTake.initiateStockTake();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
