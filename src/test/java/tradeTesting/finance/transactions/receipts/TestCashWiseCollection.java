package tradeTesting.finance.transactions.receipts;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestCashWiseCollection {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    private static final String TEMP_API_BODY_CashWiseCollection="./output/temp_api_request_bodies/CashWiseCollection.json";
    private static final String API_RESPONSE_CashWiseCollection="./output/api_responses/CashWiseCollection.json";
    private static final String OUTPUT_FILE="./src/main/resources/tradeAutomation/finance/transactions/491893 - Cash Wise Collection_CWC_2_SIAO_3_Output.xls";

    String dataFile = "./src/main/resources/tradeAutomation/finance/transactions/491893 - Cash Wise Collection_CWC_2_SIAO_3.xls";


    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void journalEntry() throws Exception {
        CashWiseCollection cashWiseCollection=new CashWiseCollection(driver,dataFile);
        cashWiseCollection.cashWiseCollection("SIAO 3","","","");
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
