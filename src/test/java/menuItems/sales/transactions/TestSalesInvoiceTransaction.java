package menuItems.sales.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.SalesInvoice;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class TestSalesInvoiceTransaction {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    private static final String TEMP_API_BODY_SALES_INVOICES="./output/temp_api_request_bodies/SalesInvoices.json";
    private static final String API_RESPONSE_SALES_SALES_INVOICES="./output/api_responses/SalesInvoices.json";
    private static final String OUTPUT_FILE="./src/main/resources/menuItems/Sales/Transactions/480460 - Sales Invoices-AC_Output.xlsx";

    String dataFile = "./src/main/resources/menuItems/Sales/Transactions/480460 - Sales Invoices-AC.xlsx";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver= appLogin.login();
    }

    @Test
    public void salesInvoiceTransaction() throws IOException, InterruptedException, ParseException, AWTException {
        SalesInvoice invoice = new SalesInvoice(driver, dataFile);
        invoice.salesInvoice(TEMP_API_BODY_SALES_INVOICES,API_RESPONSE_SALES_SALES_INVOICES,OUTPUT_FILE);
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }

}