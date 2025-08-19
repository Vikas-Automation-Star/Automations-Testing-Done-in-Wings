package menuItems.sales.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.SalesInvoiceAgainstOrders;
import com.wings.pages.sales.transactions.SalesOrders;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class TestSalesInvoiceAgainstOrder {

    private static final Logger log = LoggerFactory.getLogger(TestSalesInvoiceAgainstOrder.class);
    WindowsDriver driver;
    AppLogin login = new AppLogin();
    String dataFile = "./src/main/resources/menuItems/Sales/Transactions/480461 - Sales Invoices against Orders-AC.xls";
    String dataFile1="./src/main/resources/menuItems/Sales/Transactions/478887 - Sales Orders-AC.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = login.login();
    }

    @Test
    public void salesInvoiceAgainstOrders() throws IOException, ParseException, InterruptedException, AWTException {
        SalesOrders salesOrders=new SalesOrders(driver,dataFile1);
        String salesOrderVoucher=salesOrders.salesOrder();
        login.logout();
        driver=login.login();
        SalesInvoiceAgainstOrders invoiceAgainstOrders=new SalesInvoiceAgainstOrders(driver,dataFile);
        invoiceAgainstOrders.invoiceAgainstOrders(salesOrderVoucher);
    }

    @AfterTest
    public void afterTest() throws IOException {
            login.logout();
    }
}