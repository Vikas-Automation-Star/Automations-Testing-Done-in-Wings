package tradeTesting.sales.transactions;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;

public class TestSalesOrders {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String dataFile="./src/main/resources/tradeAutomation/sales/transactions/491020 - Sales Orders-Trd_SO_3.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void salesOrderTrade() throws Exception {
        SalesOrdersTrade salesOrdersTrade=new SalesOrdersTrade(driver,dataFile);
        salesOrdersTrade.salesOrderTrade();
    }

    @AfterTest
    public void afterTest() throws IOException {
//        appLogin.logout();
    }
}
