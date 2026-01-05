package tradeTesting.sales.transactions;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestSalesOrders {

    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void journalEntry() throws Exception {
//        JournalEntries journalEntries = new JournalEntries(driver, dataFile);
//        journalEntries.journalEntries(TEMP_API_BODY_JOURNAL_ENTRIES,API_RESPONSE_JOURNAL_ENTRIES,OUTPUT_FILE);
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
