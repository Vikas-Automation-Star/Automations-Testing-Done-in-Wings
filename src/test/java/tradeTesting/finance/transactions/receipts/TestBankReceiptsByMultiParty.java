package tradeTesting.finance.transactions.receipts;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestBankReceiptsByMultiParty {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    private static final String TEMP_API_BODY_BankReceiptsByMultiParty="./output/temp_api_request_bodies/BankReceiptsByMultiParty.json";
    private static final String API_RESPONSE_BankReceiptsByMultiParty="./output/api_responses/BankReceiptsByMultiParty.json";
    private static final String OUTPUT_FILE="./src/main/resources/tradeAutomation/finance/transactions/488349 - Bank Receipts By Multi Party(SalesExecutive)_BRBMPS_1_SI_2_Output.xls";

    String dataFile = "./src/main/resources/tradeAutomation/finance/transactions/488349 - Bank Receipts By Multi Party(SalesExecutive)_BRBMPS_1_SI_2.xls";


    @BeforeTest
    public void beforeTest() throws  InterruptedException,IOException, ParseException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void journalEntry() throws Exception {
        BankReceiptsByMultiParty receiptsByMultiParty=new BankReceiptsByMultiParty(driver,dataFile);
        receiptsByMultiParty.bankReceiptsByMultiParty("","","");
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
