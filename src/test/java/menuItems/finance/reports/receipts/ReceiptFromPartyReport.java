package menuItems.finance.reports.receipts;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.finance.reports.receipts.ReceiptFromPartyReportCode;
import java.awt.*;
import java.io.IOException;

public class ReceiptFromPartyReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void receiptFromParty() throws  InterruptedException, AWTException {
        ReceiptFromPartyReportCode partyReportCode=new ReceiptFromPartyReportCode(driver);
        partyReportCode.partyReport();
    }

    @AfterTest
    public void afterTest() throws IOException{
        appLogin.logout();
    }
}
