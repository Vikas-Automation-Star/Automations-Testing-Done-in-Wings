package menuItems.finance.reports.Banking;

import com.wings.pages.AppLogin;
import com.wings.pages.finance.reports.Banking.ReceivedChequesBounceReportCode;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class ReceivedChequesBounceReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void chequeBounce() throws InterruptedException, AWTException {
        ReceivedChequesBounceReportCode chequesBounceReportCode = new ReceivedChequesBounceReportCode(driver);
        chequesBounceReportCode.chequeBounce();

    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
