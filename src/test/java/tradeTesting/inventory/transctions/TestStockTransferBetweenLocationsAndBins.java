package tradeTesting.inventory.transctions;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import tradeTesting.inventory.transactions.StockTransferBetweenLocationsAndBinsTrade;
import java.io.IOException;

public class TestStockTransferBetweenLocationsAndBins {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String dataFile="./src/main/resources/tradeAutomation/inventory/transactions/486588 - Stock Transfer Between Locations And Bins-Trd_STBLB_1.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void testStockTransferBetweenLocationsAndBins() throws Exception {
        StockTransferBetweenLocationsAndBinsTrade stockTransferBetweenLocationsAndBins=new StockTransferBetweenLocationsAndBinsTrade(driver,dataFile);
        stockTransferBetweenLocationsAndBins.stockTransferBetweenLocationsAndBinsTrade("","","");
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
