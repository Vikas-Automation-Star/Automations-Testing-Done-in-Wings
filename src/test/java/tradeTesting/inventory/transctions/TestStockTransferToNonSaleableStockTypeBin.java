package tradeTesting.inventory.transctions;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import tradeTesting.inventory.transactions.StockTransferToNonSaleableStockTypeBinTrade;
import java.io.IOException;

public class TestStockTransferToNonSaleableStockTypeBin {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String dataFile="./src/main/resources/tradeAutomation/inventory/transactions/487785 - Stock Transfer To Non-Saleable Stock Type Bin-V19Trade_STNSSTB_1.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void testStockTransferToNonSaleableStockTypeBin() throws Exception {
        StockTransferToNonSaleableStockTypeBinTrade nonSaleableStockTypeBinTrade=new StockTransferToNonSaleableStockTypeBinTrade(driver,dataFile);
        nonSaleableStockTypeBinTrade.stockTransferToNonSaleableStockTypeBinTrade("","","");
    }

    @AfterTest
    public void afterTest() throws IOException {
//        appLogin.logout();
    }
}
