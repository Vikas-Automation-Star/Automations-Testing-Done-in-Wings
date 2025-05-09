package menuItems.sales.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.SalesEnquiry;
import com.wings.pages.sales.transactions.SalesInvoiceAgainstOrders;
import com.wings.pages.sales.transactions.SalesOrdersAgainstQuotations;
import com.wings.pages.sales.transactions.SalesQuotationAgainstEnquiry;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class TestSalesInvoiceAgainstOrder {

    WindowsDriver driver;
    AppLogin login = new AppLogin();
    String dataFile = "./src/main/resources/menuItems/Sales/Transactions/salesEnquiry.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = login.launchSingleUserApp();
        login.singleUserLogin();
    }

    @Test
    public void salesOrderAgainstQuotations() throws IOException, ParseException, InterruptedException, AWTException {
        SalesEnquiry salesEnquiry=new SalesEnquiry(driver,dataFile);

        SalesQuotationAgainstEnquiry agnstEnquiry = new SalesQuotationAgainstEnquiry(driver, dataFile);
        String orderAgainstQuotation=agnstEnquiry.quotationAgainstEnquiry(salesEnquiry.salesEnquiry());

        SalesOrdersAgainstQuotations quotations = new SalesOrdersAgainstQuotations(driver, dataFile);
        String ordersVoucherNum= quotations.salesOrderAgainstQuotation(orderAgainstQuotation);

        SalesInvoiceAgainstOrders invoiceAgainstOrders=new SalesInvoiceAgainstOrders(driver,dataFile);
        invoiceAgainstOrders.invoiceAgainstOrders(ordersVoucherNum);
//            invoiceAgainstOrders.invoiceAgainstOrders("SOAQ 2");

    }

    @AfterTest
    public void afterTest() throws IOException {
//            login.logout();
    }
}