package tradeTesting.sales.transactions;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;

public class TestSalesOrderCancellationTrade {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String orderFile="./src/main/resources/tradeAutomation/sales/transactions/491020 - Sales Orders-Trd_SO_4.xls";
    String dataFile="./src/main/resources/tradeAutomation/sales/transactions/489196 - Sales Orders Cancellation-Trd_SOC_2_SO_4.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void testSalesOrderCancellation() throws Exception {
        SalesOrdersTrade salesOrdersTrade=new SalesOrdersTrade(driver,orderFile);
        String salesOrdervoucher=salesOrdersTrade.salesOrderTrade();

        appLogin.logout();
        driver=appLogin.tradeLogin();

        SalesOrderCancellationTrade salesOrderCancellationTrade=new SalesOrderCancellationTrade(driver,dataFile);
        salesOrderCancellationTrade.salesOrderCancellationTrade(salesOrdervoucher);
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}