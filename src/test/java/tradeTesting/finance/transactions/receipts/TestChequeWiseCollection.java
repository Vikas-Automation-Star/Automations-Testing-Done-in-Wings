package tradeTesting.finance.transactions.receipts;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestChequeWiseCollection {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    private static final String TEMP_API_BODY_ChequeWiseCollection="./output/temp_api_request_bodies/ChequeWiseCollection.json";
    private static final String API_RESPONSE_ChequeWiseCollection="./output/api_responses/ChequeWiseCollection.json";
    private static final String OUTPUT_FILE="./src/main/resources/tradeAutomation/finance/transactions/491902 - Cheque Wise Collection_CQWC_3_SIAO_3_Output.xls";

    String dataFile = "./src/main/resources/tradeAutomation/finance/transactions/491902 - Cheque Wise Collection_CQWC_3_SIAO_3.xls";


    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void journalEntry() throws Exception {
        ChequeWiseCollection chequeWiseCollection=new ChequeWiseCollection(driver,dataFile);
        chequeWiseCollection.chequeWiseCollections("SIAO 3","","","");
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
