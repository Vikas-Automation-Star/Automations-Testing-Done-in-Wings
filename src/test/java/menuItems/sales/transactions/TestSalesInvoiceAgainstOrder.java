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

    private static final String TEMP_API_SALES_ORDER="./output/temp_api_request_bodies/salesOrder.json";
    private static final String API_RESPONSE_SALES_ORDER="./output/api_responses/salesOrder.json";
    private static final String OUTPUT_FILE_SALES_ORDER="./src/main/resources/menuItems/Sales/Transactions/478887 - Sales Orders-AC_Output.xls";

    private static final String TEMP_API_SALES_INVOICE_AGAINST_ORDER="./output/temp_api_request_bodies/salesInvoiceAgainstOrder.json";
    private static final String API_RESPONSE_SALES_INVOICE_AGAINST_ORDER="./output/api_responses/salesInvoiceAgainstOrder.json";
    private static final String OUTPUT_FILE_SALES_INVOICE_AGAINST_ORDER="./src/main/resources/menuItems/Sales/Transactions/480461 - Sales Invoices against Orders-AC_SIAO_3_Output.xls";

    String dataFile = "./src/main/resources/menuItems/Sales/Transactions/480461 - Sales Invoices against Orders-AC_SIAO_3.xls";
    String dataFile1="./src/main/resources/menuItems/Sales/Transactions/478887 - Sales Orders-AC.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = login.login();
    }

    @Test
    public void salesInvoiceAgainstOrders() throws Exception, AWTException {
        SalesOrders salesOrders=new SalesOrders(driver,dataFile1);
        String salesOrderVoucher=salesOrders.salesOrder(TEMP_API_SALES_ORDER,API_RESPONSE_SALES_ORDER,OUTPUT_FILE_SALES_ORDER);
        login.logout();
        driver=login.login();
        SalesInvoiceAgainstOrders invoiceAgainstOrders=new SalesInvoiceAgainstOrders(driver,dataFile);
        invoiceAgainstOrders.invoiceAgainstOrders(salesOrderVoucher,TEMP_API_SALES_INVOICE_AGAINST_ORDER,API_RESPONSE_SALES_INVOICE_AGAINST_ORDER,OUTPUT_FILE_SALES_INVOICE_AGAINST_ORDER);
    }

    @AfterTest
    public void afterTest() throws IOException {
            login.logout();
    }
}