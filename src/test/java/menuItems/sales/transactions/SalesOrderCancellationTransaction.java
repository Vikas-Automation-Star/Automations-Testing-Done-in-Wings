package menuItems.sales.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.SalesOrderCancellation;
import com.wings.pages.sales.transactions.SalesOrders;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class SalesOrderCancellationTransaction {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String dataFile="./src/main/resources/menuItems/Sales/Transactions/478887 - Sales Orders-AC.xls";
    String dataFile1 = "./src/main/resources/menuItems/Sales/Transactions/458823 - Sales Orders Cancellation-AC.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.login();
    }

    @Test
    public void salesOrderCancellation() throws IOException, InterruptedException, ParseException, AWTException {
        SalesOrders salesOrders=new SalesOrders(driver, dataFile);
        String salesOrderVoucher= salesOrders.salesOrder();
        appLogin.logout();
        driver=appLogin.login();
        SalesOrderCancellation orderCancellation = new SalesOrderCancellation(driver, dataFile1);
        orderCancellation.salesOrderCancellations(salesOrderVoucher);
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}