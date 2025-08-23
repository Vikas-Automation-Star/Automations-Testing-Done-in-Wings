package menuItems.finance.transactions.Journals;

import io.appium.java_client.windows.WindowsDriver;

import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.finance.transactions.Journals.JournalEntries;

import java.io.IOException;

public class TestJournalEntries {

    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    private static final String TEMP_API_BODY_JOURNAL_ENTRIES="./output/temp_api_request_bodies/journalEntries.json";
    private static final String API_RESPONSE_JOURNAL_ENTRIES="./output/api_responses/journalEntries.json";
    private static final String OUTPUT_FILE="./src/main/resources/menuItems/finance/transaction/464159 - Journal Entries-AC_JE_3_Output.xls";

    String dataFile = "./src/main/resources/menuItems/finance/transaction/464159 - Journal Entries-AC_JE_3.xls";


    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.login();
    }

    @Test
    public void journalEntry() throws Exception {
        JournalEntries journalEntries = new JournalEntries(driver, dataFile);
        journalEntries.journalEntries(TEMP_API_BODY_JOURNAL_ENTRIES,API_RESPONSE_JOURNAL_ENTRIES,OUTPUT_FILE);
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}