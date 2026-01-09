package tradeTesting.inventory.transctions;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tradeTesting.inventory.transactions.StockCreationTrade;
import java.io.IOException;

public class TestStockCreation {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    String dataFile = "./src/main/resources/tradeAutomation/inventory/transactions/486590 - Stock Creation-Trd_SCR_1.xls";

    @BeforeMethod
    public void beforeTest() throws ParseException, InterruptedException, IOException {
        driver = appLogin.tradeLogin();
    }

    @Test
    public void testStockCreation() throws Exception {
        StockCreationTrade stockCreationTrade = new StockCreationTrade(driver, dataFile);
        stockCreationTrade.stockCreationtrade("", "", "");
    }

    @AfterMethod
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}