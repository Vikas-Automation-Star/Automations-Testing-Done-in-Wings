package tradeTesting.inventory.transctions;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import tradeTesting.inventory.transactions.StockReplacementTrade;

import java.io.IOException;

public class TestStockReplacement {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String dataFile="./src/main/resources/tradeAutomation/inventory/transactions/487805 - Stock Replacement-Trd_SCRM_1.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void testStockReplacement() throws Exception {
        StockReplacementTrade stockReplacementTrade=new StockReplacementTrade(driver,dataFile);
        stockReplacementTrade.stockReplacementTrade("","","");
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
