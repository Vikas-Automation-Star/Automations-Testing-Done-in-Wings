package tradeTesting.sales.transactions;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.IOException;

public class TestBatchSalesPrice {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();

    String dataFile="./src/main/resources/tradeAutomation/sales/transactions/472728 - Batch Sales Prices-Trd_SPR_1.xls";

    @BeforeMethod
    public void beforeTest() throws ParseException, InterruptedException, IOException {
        driver = appLogin.tradeLogin();
    }

    @Test
    public void testBatchSalesPrice() throws Exception {
        BatchSalesPriceTrade batchSalesPriceTrade=new BatchSalesPriceTrade(driver,dataFile);
        batchSalesPriceTrade.batchSalesPriceTrade();
    }

    @AfterMethod
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}