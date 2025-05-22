package menuItems.inventory.transactions;

import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.inventory.transactions.StockConsumption;

import java.awt.*;
import java.io.IOException;

public class TestStockConsumption {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    Common common;
    String file = "./src/main/resources/menuItems/inventory/transactions/stockConsumption.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        common=new Common(driver);
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin(common.getData(file,"stockConsumption","userName"),common.getData(file,"stockConsumption","password"));
    }

    @Test
    public void stockConsumption() throws InterruptedException, AWTException, IOException, ParseException {
        StockConsumption consumptionTrans = new StockConsumption(driver, file);
        consumptionTrans.stockConsumption();
    }

    @AfterTest
    public void afterTest() throws IOException {
//        appLogin.logout();
    }
}