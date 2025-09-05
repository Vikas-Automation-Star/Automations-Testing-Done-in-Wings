package menuItems.finance.transactions.Receipts;

import com.wings.pages.sales.transactions.SalesInvoice;
import io.appium.java_client.windows.WindowsDriver;

import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.finance.transactions.Receipts.CreditCardReceipts;

import java.io.IOException;

public class TestCreditCardReceipt {

    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    private static final String TEMP_API_BODY_SALES_INVOICES="./output/temp_api_request_bodies/SalesInvoices.json";
    private static final String API_RESPONSE_SALES_SALES_INVOICES="./output/api_responses/SalesInvoices.json";
    private static final String OUTPUT_FILE="./";

    private static final String TEMP_API_BODY_CREDIT_CARD_RECEIPTS="./output/temp_api_request_bodies/creditCardReceipts.json";
    private static final String API_RESPONSE_CREDIT_CARD_RECEIPTS="./output/api_responses/creditCardReceipts.json";
    private static final String OUTPUT_FILE1="./src/main/resources/menuItems/finance/transaction/456088 - Credit Card Receipts-AC_CCR_2_Output.xls";


    String dataFile = "./src/main/resources/menuItems/Sales/Transactions/480460 - Sales Invoices-AC.xlsx";
    String dataFile1="./src/main/resources/menuItems/finance/transaction/456088 - Credit Card Receipts-AC_CCR_2.xls";

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

        CreditCardReceipts creditCardReceipts = new CreditCardReceipts(driver, dataFile1);
        creditCardReceipts.creditCardReceipt("SI 16",TEMP_API_BODY_CREDIT_CARD_RECEIPTS,API_RESPONSE_CREDIT_CARD_RECEIPTS,OUTPUT_FILE1);
    }

    @AfterTest
    public void afterTest() throws IOException {
//        appLogin.logout();
    }
}