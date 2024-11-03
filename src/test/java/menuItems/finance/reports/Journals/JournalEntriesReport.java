package menuItems.finance.reports.Journals;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.finance.reports.Journals.JournalEntriesReportCode;
import java.awt.*;
import java.io.IOException;

public class JournalEntriesReport {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, ParseException, InterruptedException {
        driver=appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void journalEntry() throws IOException, ParseException, InterruptedException, AWTException {
        JournalEntriesReportCode entriesReportCode=new JournalEntriesReportCode(driver);
        entriesReportCode.journalEntry();
    }

    @AfterTest
    public void atferTest(){
        appLogin.logout();
    }

}
