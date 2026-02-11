package tradeTesting.finance.transactions.receipts;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestTradeReceiptsFromParties {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    private static final String TEMP_API_BODY_JOURNAL_ENTRIES="./output/temp_api_request_bodies/TradeReceiptsFromParties.json";
    private static final String API_RESPONSE_JOURNAL_ENTRIES="./output/api_responses/TradeReceiptsFromParties.json";
    private static final String OUTPUT_FILE="./src/main/resources/tradeAutomation/finance/transactions/490664 - Trade Receipts from Parties-Trd_TRECP_1_Output.xls";

    String dataFile = "./src/main/resources/tradeAutomation/finance/transactions/490664 - Trade Receipts from Parties-Trd_TRECP_1.xls";


    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void journalEntry() throws Exception {
        TradeReceiptsFromParties receiptsFromParties=new TradeReceiptsFromParties(driver,dataFile);
        receiptsFromParties.tradeReceiptsFromParties("","","");
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
