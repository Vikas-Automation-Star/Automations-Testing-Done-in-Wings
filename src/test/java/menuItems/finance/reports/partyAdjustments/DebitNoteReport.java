package menuItems.finance.reports.partyAdjustments;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.finance.reports.partyAdjustments.DebitNoteReportCode;

import java.awt.*;
import java.io.IOException;

public class DebitNoteReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void debitNoteReport() throws  InterruptedException, AWTException {
        DebitNoteReportCode noteReportCode=new DebitNoteReportCode(driver);
        noteReportCode.debitNoteReport();

    }

    @AfterTest
    public void afterTest(){
        appLogin.logout();
    }
}
