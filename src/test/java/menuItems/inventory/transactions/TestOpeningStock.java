package menuItems.inventory.transactions;

import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.inventory.transactions.OpeningStock;

import java.awt.*;
import java.io.IOException;

public class TestOpeningStock {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    Common common;
    String file = "./src/main/resources/menuItems/inventory/transactions/openingStock.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        common=new Common(driver);
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin(common.getData(file,"openingStock","userName"),common.getData(file,"openingStock","password"));
    }

    @Test
    public void openingStock() throws InterruptedException, AWTException, IOException, ParseException {
        OpeningStock stockTrans = new OpeningStock(driver, file);
        stockTrans.openingStock();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}