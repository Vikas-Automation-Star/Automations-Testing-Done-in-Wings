package menuItems.sales.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.SalesInvoice;
import com.wings.pages.sales.transactions.SalesReturnWithInvoiceReference;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class TestSalesReturnsWithInvoiceReference {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    private static final String TEMP_API_BODY_SALES_INVOICES="./output/temp_api_request_bodies/SalesInvoices.json";
    private static final String API_RESPONSE_SALES_SALES_INVOICES="./output/api_responses/SalesInvoices.json";
    private static final String OUTPUT_FILE1="./src/main/resources/menuItems/Sales/Transactions/480460 - Sales Invoices-AC_SI1_1_Output.xls";

    private static final String TEMP_API_BODY_SRWIR="./output/temp_api_request_bodies/salesReturnsWithInvoiceReference.json";
    private static final String API_RESPONSE_SRWIR="./output/api_responses/salesReturnsWithInvoiceReference.json";
    private static final String OUTPUT_FILE2="./src/main/resources/menuItems/Sales/Transactions/480464 - Sales Return with Invoice Reference-AC_Output.xlsx";


    String dataFile = "./src/main/resources/menuItems/Sales/Transactions/480460 - Sales Invoices-AC_SI1_1.xls";
    String dataFile1 = "./src/main/resources/menuItems/Sales/Transactions/480464 - Sales Return with Invoice Reference-AC.xlsx";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver= appLogin.login();
    }

    @Test
    public void salesReturnsWithInvoiceReference() throws Exception {
        SalesInvoice invoice = new SalesInvoice(driver, dataFile);
        String salesI= invoice.salesInvoice(TEMP_API_BODY_SALES_INVOICES,API_RESPONSE_SALES_SALES_INVOICES,OUTPUT_FILE1);

        appLogin.logout();
        driver= appLogin.login();

        SalesReturnWithInvoiceReference salesRWIR =new SalesReturnWithInvoiceReference(driver,dataFile1);
        salesRWIR.salesReturnWithInvoiceReference(salesI,TEMP_API_BODY_SRWIR,API_RESPONSE_SRWIR,OUTPUT_FILE2);
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}