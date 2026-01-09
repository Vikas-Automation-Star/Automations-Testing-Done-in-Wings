package tradeTesting.inventory.transctions;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import tradeTesting.inventory.transactions.StockConsumptionTrade;
import java.io.IOException;

public class TestStockConsumption {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String dataFile="./src/main/resources/tradeAutomation/inventory/transactions/490448 - Stock Consumption-Trd_CS 2.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void testStockConsumption() throws Exception {
        StockConsumptionTrade stockConsumptionTrade = new StockConsumptionTrade(driver,dataFile);
        stockConsumptionTrade.stockConsumptionTrade("","","");
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}