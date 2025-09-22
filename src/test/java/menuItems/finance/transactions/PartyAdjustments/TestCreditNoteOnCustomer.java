package menuItems.finance.transactions.PartyAdjustments;

import com.wings.pages.AppLogin;
import com.wings.pages.finance.transactions.PartyAdjustments.CreditNoteOnCustomers;
import com.wings.pages.sales.transactions.SalesInvoice;
import io.appium.java_client.windows.WindowsDriver;

import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class TestCreditNoteOnCustomer {

    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    private static final String TEMP_API_BODY_SALES_INVOICES="./output/temp_api_request_bodies/SalesInvoices.json";
    private static final String API_RESPONSE_SALES_SALES_INVOICES="./output/api_responses/SalesInvoices.json";
    private static final String OUTPUT_FILE="./src/main/resources/menuItems/Sales/Transactions/480460 - Sales Invoices-AC_SI1_1_Output.xls";

    private static final String TEMP_API_BODY_CREDIT_NOTE_ON_CUSTOMERS="./output/temp_api_request_bodies/CreditNoteOnCustomer.json";
    private static final String API_RESPONSE_CREDIT_NOTE_ON_CUSTOMERS="./output/api_responses/CreditNoteOnCustomer.json";
    private static final String OUTPUT_FILE1="./src/main/resources/menuItems/finance/transaction/475832 - Credit Note on Customers-AC_CNOC_1_Output.xls";


    String dataFile = "./src/main/resources/menuItems/Sales/Transactions/480460 - Sales Invoices-AC_SI1_1.xls";
    String dataFile1="./src/main/resources/menuItems/finance/transaction/475832 - Credit Note on Customers-AC_CNOC_1.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.login();
    }

    @Test
    public void creditNoteOnCustomer() throws Exception {
        SalesInvoice invoice = new SalesInvoice(driver, dataFile);
        String salesI= invoice.salesInvoice(TEMP_API_BODY_SALES_INVOICES,API_RESPONSE_SALES_SALES_INVOICES,OUTPUT_FILE);

        appLogin.logout();
        driver= appLogin.login();

        CreditNoteOnCustomers noteOnCustomers = new CreditNoteOnCustomers(driver, dataFile1);
        noteOnCustomers.creditNoteOnCustomer(salesI,TEMP_API_BODY_CREDIT_NOTE_ON_CUSTOMERS,API_RESPONSE_CREDIT_NOTE_ON_CUSTOMERS,OUTPUT_FILE1);
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
