package tradeTesting.finance.transactions.partyAdjustments;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestCreditNoteOnCustomer {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    private static final String TEMP_API_BODY_SALES_INVOICES="./output/temp_api_request_bodies/SalesInvoices.json";
    private static final String API_RESPONSE_SALES_SALES_INVOICES="./output/api_responses/SalesInvoices.json";
    private static final String OUTPUT_FILE="./src/main/resources/menuItems/Sales/Transactions/480460 - Sales Invoices-AC_SI1_1_Output.xls";

    private static final String TEMP_API_BODY_CREDIT_NOTE_ON_CUSTOMERS="./output/temp_api_request_bodies/CreditNoteOnCustomer.json";
    private static final String API_RESPONSE_CREDIT_NOTE_ON_CUSTOMERS="./output/api_responses/CreditNoteOnCustomer.json";
    private static final String OUTPUT_FILE1="./src/main/resources/tradeAutomation/finance/transactions/497451 - Credit Note on Customers-AC_CNOC_2_SIAO_1_Output.xls";

    String dataFile = "./src/main/resources/menuItems/Sales/Transactions/480460 - Sales Invoices-AC_SI1_1.xls";
    String dataFile1="./src/main/resources/tradeAutomation/finance/transactions/497451 - Credit Note on Customers-AC_CNOC_2_SIAO_1.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void creditNoteOnCustomer() throws Exception {
//        SalesInvoice invoice = new SalesInvoice(driver, dataFile);
//        String salesVoucher= invoice.salesInvoice(TEMP_API_BODY_SALES_INVOICES,API_RESPONSE_SALES_SALES_INVOICES,OUTPUT_FILE);

//        appLogin.logout();
//        driver= appLogin.tradeLogin();

        CreditNoteOnCustomer noteOnCustomer=new CreditNoteOnCustomer(driver,dataFile1);
        noteOnCustomer.creditNoteOnCustomer("SIAO 1","","","");
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
