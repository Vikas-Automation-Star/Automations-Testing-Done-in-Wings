package menuItems.finance.transactions.Receipts;

import com.wings.pages.sales.transactions.SalesInvoice;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.finance.transactions.Receipts.CashReceipts;

import java.io.IOException;

public class TestCashReceipts {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    private static final String TEMP_API_BODY_SALES_INVOICES="./output/temp_api_request_bodies/SalesInvoices.json";
    private static final String API_RESPONSE_SALES_SALES_INVOICES="./output/api_responses/SalesInvoices.json";
    private static final String OUTPUT_FILE="./src/main/resources/menuItems/Sales/Transactions/480460 - Sales Invoices-AC_SI1_1_Output.xls";

    private static final String TEMP_API_BODY_CASH_RECEIPTS="./output/temp_api_request_bodies/cashReceipts.json";
    private static final String API_RESPONSE_CASH_RECEIPTS="./output/api_responses/cashReceipts.json";
    private static final String OUTPUT_FILE1="./src/main/resources/menuItems/finance/transaction/458632 - Cash Receipts-AC_CR_2_Output.xls";


    String dataFile1="./src/main/resources/menuItems/finance/transaction/458632 - Cash Receipts-AC_CR_2.xls";
    String dataFile = "./src/main/resources/menuItems/Sales/Transactions/480460 - Sales Invoices-AC_SI1_1.xls";


    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.login();
    }

    @Test
    public void receiptFromParty() throws Exception {

//        SalesInvoice invoice = new SalesInvoice(driver, dataFile);
//        String salesInvoice =invoice.salesInvoice(TEMP_API_BODY_SALES_INVOICES,API_RESPONSE_SALES_SALES_INVOICES,OUTPUT_FILE);
//
//        appLogin.logout();
//        driver= appLogin.login();

        CashReceipts cashReceipts = new CashReceipts(driver, dataFile1);
        cashReceipts.cashReceipt("SI 16",TEMP_API_BODY_CASH_RECEIPTS,API_RESPONSE_CASH_RECEIPTS,OUTPUT_FILE1);
    }

    @AfterTest
    public void afterTest() throws IOException {
//        appLogin.logout();
    }
}