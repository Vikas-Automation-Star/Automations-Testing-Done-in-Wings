package menuItems.finance.reports.Journals;

import com.wings.pages.AppLogin;
import com.wings.pages.finance.reports.Journals.JournalEntriesReportCode;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class JournalEntriesReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, ParseException, InterruptedException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void journalEntry() throws IOException, ParseException, InterruptedException, AWTException {
        JournalEntriesReportCode entriesReportCode = new JournalEntriesReportCode(driver);
        entriesReportCode.journalEntry();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }

}
