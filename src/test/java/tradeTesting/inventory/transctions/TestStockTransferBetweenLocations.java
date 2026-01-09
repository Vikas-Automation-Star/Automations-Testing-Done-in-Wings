package tradeTesting.inventory.transctions;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import tradeTesting.inventory.transactions.StockTransferBetweenLocationsTrade;
import java.io.IOException;

public class TestStockTransferBetweenLocations {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String dataFile="./src/main/resources/tradeAutomation/inventory/transactions/456248 - Stock Transfer Between Locations-Trd_STBL_1.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void testStockTransferBetweenLocations() throws Exception {
        StockTransferBetweenLocationsTrade stockTransferBetweenLocationsTrade=new StockTransferBetweenLocationsTrade(driver,dataFile);
        stockTransferBetweenLocationsTrade.stockTransferBetweenLocationsTrade("","","");
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}