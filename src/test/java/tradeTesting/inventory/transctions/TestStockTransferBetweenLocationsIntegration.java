package tradeTesting.inventory.transctions;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import tradeTesting.inventory.transactions.StockTransferBetweenLocationsIntegrationTrade;
import java.io.IOException;

public class TestStockTransferBetweenLocationsIntegration {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String dataFile="./src/main/resources/tradeAutomation/inventory/transactions/487769 - Stock Transfer Between Locations Integration-Trd_STBLI_1.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void testStockTransferBetweenLocationsIntegration() throws Exception {
        StockTransferBetweenLocationsIntegrationTrade betweenLocationsIntegrationTrade=new StockTransferBetweenLocationsIntegrationTrade(driver,dataFile);
        betweenLocationsIntegrationTrade.stockTransferBetweenLocationsIntegrationTrade("","","");
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
