package menuItems.sales.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.SalesOrders;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class SalesOrderTransaction {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String dataFile="./src/main/resources/menuItems/Sales/Transactions/salesEnquiry.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.login();
    }

    @Test
    public void salesOrder() throws IOException, ParseException, InterruptedException, AWTException {
//        SalesOrder salesOrder = new SalesOrder(driver, file);
//        salesOrder.salesOrder();
        SalesOrders salesOrders=new SalesOrders(driver,dataFile);
        salesOrders.salesOrders();
    }

    @AfterTest
    public void afterTest() throws IOException {
//        appLogin.logout();
    }
}
