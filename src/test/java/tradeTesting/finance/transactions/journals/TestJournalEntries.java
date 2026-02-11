package tradeTesting.finance.transactions.journals;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestJournalEntries {

    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    private static final String TEMP_API_BODY_JOURNAL_ENTRIES="./output/temp_api_request_bodies/JournalEntries.json";
    private static final String API_RESPONSE_JOURNAL_ENTRIES="./output/api_responses/JournalEntries.json";
    private static final String OUTPUT_FILE="./src/main/resources/tradeAutomation/finance/transactions/464159 - Journal Entries-AC_JE_1_Output.xls";

    String dataFile = "./src/main/resources/tradeAutomation/finance/transactions/464159 - Journal Entries-AC_JE_1.xls";


    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void journalEntry() throws Exception {
        JournalEntries journalEntries=new JournalEntries(driver,dataFile);
        journalEntries.journalEntries("","","");
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
