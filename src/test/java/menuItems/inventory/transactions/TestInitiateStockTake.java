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
    String file = "./src/main/resources/menuItems/inventory/transactions/450889 - Initiate Stock Take-AC_IST_1.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.login();
    }
    @Test
    public void initiateStockTake() throws InterruptedException, IOException, ParseException {
        InitiateStockTake initiateStockTake=new InitiateStockTake(driver,file);
        initiateStockTake.initiateStockTake();
    }

    @AfterTest
    public void afterTest() throws IOException {
//        appLogin.logout();
    }
}
