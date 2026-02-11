package tradeTesting.finance.transactions.receipts;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class TestBankReceipts {

    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    private static final String TEMP_API_BODY_SALES_INVOICES="./output/temp_api_request_bodies/SalesInvoices.json";
    private static final String API_RESPONSE_SALES_SALES_INVOICES="./output/api_responses/SalesInvoices.json";
    private static final String OUTPUT_FILE="./";

    private static final String TEMP_API_BODY_BANK_RECEIPTS="./output/temp_api_request_bodies/BankReceipts.json";
    private static final String API_RESPONSE_BANK_RECEIPTS="./output/api_responses/BankReceipts.json";
    private static final String OUTPUT_FILE1="./src/main/resources/tradeAutomation/finance/transactions/491895 - Bank Receipts-AC_BR_2_PRWIR_2_Output.xls";

    String dataFile = "./";
    String dataFile1="./src/main/resources/tradeAutomation/finance/transactions/491895 - Bank Receipts-AC_BR_2_PRWIR_2.xls";


    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void bankReceipts() throws Exception, AWTException {
//        SalesInvoice invoice = new SalesInvoice(driver, dataFile);
//        String salesInvoice =invoice.salesInvoice(TEMP_API_BODY_SALES_INVOICES,API_RESPONSE_SALES_SALES_INVOICES,OUTPUT_FILE);
//
//        appLogin.logout();
//        driver= appLogin.login();

        BankReceipts bankReceipts=new BankReceipts(driver,dataFile1);
        bankReceipts.bankReceipts("PRWIR 2","","","");
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
