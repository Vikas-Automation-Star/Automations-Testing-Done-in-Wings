package tradeTesting.finance.transactions.receipts;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestReceiptsFromParties {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    private static final String TEMP_API_BODY_SALES_INVOICES="./output/temp_api_request_bodies/SalesInvoices.json";
    private static final String API_RESPONSE_SALES_SALES_INVOICES="./output/api_responses/SalesInvoices.json";
    private static final String OUTPUT_FILE="./";

    private static final String TEMP_API_BODY_RECEIPTS_FROM_PARTIES="./output/temp_api_request_bodies/TestReceiptsFromPartiesMobile.json";
    private static final String API_RESPONSE_RECEIPTS_FROM_PARTIES="./output/api_responses/TestReceiptsFromPartiesMobile.json";
    private static final String OUTPUT_FILE1="./src/main/resources/tradeAutomation/finance/transactions/465649 - Receipts from Parties-AC_PREC_2_SIAO_3_Output.xls";

    String dataFile = "./";
    String dataFile1="./src/main/resources/tradeAutomation/finance/transactions/465649 - Receipts from Parties-AC_PREC_2_SIAO_3.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver= appLogin.tradeLogin();
    }

    @Test
    public void receiptFromParty() throws Exception {
//        SalesInvoice invoice = new SalesInvoice(driver, dataFile);
//        String salesInvoice =invoice.salesInvoice(TEMP_API_BODY_SALES_INVOICES,API_RESPONSE_SALES_SALES_INVOICES,OUTPUT_FILE);
//
//        appLogin.logout();
//        driver= appLogin.tradeLogin();

        ReceiptsFromParties parties=new ReceiptsFromParties(driver,dataFile1);
        parties.receiptFromParties("SIAO 3","","","");
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }

}
