package tradeTesting.finance.transactions.receipts;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestCashReceiptsByMultiParty {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    private static final String TEMP_API_BODY_CashReceiptsByMultiParty="./output/temp_api_request_bodies/CashReceiptsByMultiParty.json";
    private static final String API_RESPONSE_CashReceiptsByMultiParty="./output/api_responses/CashReceiptsByMultiParty.json";
    private static final String OUTPUT_FILE="./src/main/resources/tradeAutomation/finance/transactions/488250 - Cash Receipts By Multi Party(SalesExecutive)_CRBMPS_1_SI_2_Output.xls";

    String dataFile = "./src/main/resources/tradeAutomation/finance/transactions/488250 - Cash Receipts By Multi Party(SalesExecutive)_CRBMPS_1_SI_2.xls";


    @BeforeTest
    public void beforeTest() throws  InterruptedException, IOException, ParseException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void journalEntry() throws Exception {
        CashReceiptsByMultiParty cashReceiptsByMultiParty=new CashReceiptsByMultiParty(driver,dataFile);
        cashReceiptsByMultiParty.cashReceiptsByMultiParty("","","");

    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
