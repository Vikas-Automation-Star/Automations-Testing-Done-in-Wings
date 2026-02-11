package tradeTesting.finance.transactions.receipts;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestCreditCardReceipts {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    private static final String TEMP_API_BODY_SALES_INVOICES="./output/temp_api_request_bodies/SalesInvoices.json";
    private static final String API_RESPONSE_SALES_SALES_INVOICES="./output/api_responses/SalesInvoices.json";
    private static final String OUTPUT_FILE="./";

    private static final String TEMP_API_BODY_CREDIT_CARD_RECEIPTS="./output/temp_api_request_bodies/CreditCardReceipts.json";
    private static final String API_RESPONSE_CREDIT_CARD_RECEIPTS="./output/api_responses/CreditCardReceipts.json";
    private static final String OUTPUT_FILE1="./src/main/resources/tradeAutomation/finance/transactions/491896 - Credit Card Receipts-AC_CCR_2_PRWIR_1_Output.xls";


    String dataFile = "./";
    String dataFile1="./src/main/resources/tradeAutomation/finance/transactions/491896 - Credit Card Receipts-AC_CCR_2_PRWIR_1.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void creditCardReceipts() throws Exception {
//        SalesInvoice invoice = new SalesInvoice(driver, dataFile);
//        String salesInvoice =invoice.salesInvoice(TEMP_API_BODY_SALES_INVOICES,API_RESPONSE_SALES_SALES_INVOICES,OUTPUT_FILE);
//
//        appLogin.logout();
//        driver= appLogin.login();

        CreditCardReceipts creditCardReceipts=new CreditCardReceipts(driver,dataFile1);
        creditCardReceipts.creditCardReceipts("PRWIR 1","","","");
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
