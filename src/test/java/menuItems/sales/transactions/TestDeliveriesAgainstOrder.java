package menuItems.sales.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.DeliveriesAgainstOrdersTransaction;
import com.wings.pages.sales.transactions.SalesOrders;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class TestDeliveriesAgainstOrder {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String dataFile1="./src/main/resources/menuItems/Sales/Transactions/478887 - Sales Orders-AC.xls";
    String dataFile2="./src/main/resources/menuItems/Sales/Transactions/479587 - Deliveries against Orders-AC.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.login();
    }

    @Test
    public void deliveriesAgainstOrders() throws IOException, ParseException, InterruptedException, AWTException {
//        SalesOrders salesOrders=new SalesOrders(driver,dataFile1);
//        String salesOrderVoucher=salesOrders.salesOrder();

        appLogin.logout();
        driver = appLogin.login();

//        DeliveriesAgainstOrdersTransaction deliveriesAgainstOrdersTransaction=new DeliveriesAgainstOrdersTransaction(driver,dataFile2);
//        String deliveriesAgainstOrdersVoucher=deliveriesAgainstOrdersTransaction.deliveriesAgainstOrders(salesOrderVoucher);

    }

    @AfterTest
    public void afterTest() throws IOException {
            appLogin.logout();
    }
}