package menuItems.finance.reports.Analysis;

import com.wings.pages.AppLogin;
import com.wings.pages.finance.reports.Analysis.TransactionAnalysis;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class TransactionAnalysisReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void transactionAnalys() throws InterruptedException, AWTException {
        TransactionAnalysis analysis = new TransactionAnalysis(driver);
        analysis.transactionAnalysis();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }

}
