package tradeTesting.inventory.transctions;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tradeTesting.inventory.transactions.OpeningStockTrade;

import java.io.IOException;

public class TestOpeningStock {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();

    String dataFile="./src/main/resources/tradeAutomation/inventory/transactions/486404 - Opening Stock-Trd_OP_1.xls";
    @BeforeMethod
    public void beforeTest() throws ParseException, InterruptedException, IOException {
        driver = appLogin.tradeLogin();
    }

    @Test
    public void testOpeningStock() throws Exception {
        OpeningStockTrade openingStockTrade=new OpeningStockTrade(driver,dataFile);
        openingStockTrade.openingStockTrade("","","");
    }

    @AfterMethod
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}