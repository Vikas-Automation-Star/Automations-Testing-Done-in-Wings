package menuItems.finance.transactions.Journals;

import com.wings.pages.AppLogin;
import com.wings.pages.finance.transactions.Journals.JournalEntriesComplex;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestJournalEntriesComplex {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    private static final String TEMP_API_BODY_COMPLEX_JOURNAL_ENTRIES="./output/temp_api_request_bodies/ComplexJournalEntries.json";
    private static final String API_RESPONSE_COMPLEX_JOURNAL_ENTRIES="./output/api_responses/ComplexJournalEntries.json";
    private static final String OUTPUT_FILE="./src/main/resources/menuItems/finance/transaction/485215 - Complex Journal Entries -AC_CJE_1_Output.xls";

    String dataFile = "./src/main/resources/menuItems/finance/transaction/485215 - Complex Journal Entries -AC_CJE_1.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.login();
    }

    @Test
    public void journalEntry() throws Exception {
        JournalEntriesComplex journalEntriesComplex= new JournalEntriesComplex(driver, dataFile);
        journalEntriesComplex.journalEntriesComplex(TEMP_API_BODY_COMPLEX_JOURNAL_ENTRIES,API_RESPONSE_COMPLEX_JOURNAL_ENTRIES,OUTPUT_FILE);
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
