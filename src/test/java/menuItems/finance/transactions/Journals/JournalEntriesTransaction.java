package menuItems.finance.transactions.Journals;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.finance.transactions.Journals.JournalEntries;

import java.awt.*;
import java.io.IOException;

public class JournalEntriesTransaction {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String dataFile="./src/main/resources/menuItems/finance/transaction/journalEntry.json";

    @BeforeTest
    public void beforeTest() throws IOException, ParseException, InterruptedException {
        driver=appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void journalEntry() throws IOException, ParseException, InterruptedException, AWTException {
        JournalEntries journalEntries=new JournalEntries(driver,dataFile);
        journalEntries.journalEntires();
    }

//    @AfterTest
//    public void atferTest(){
//        appLogin.logout();
//    }
}
