package SalesInvoicesTestCases;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class UnRegCustExcludingGST_23 {

    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String dataFile = "./src/main/resources/TestCasesData/testCase23.json";

    @BeforeTest
    public void beforeTest() throws IOException, ParseException, InterruptedException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void testCase23() throws IOException, ParseException, InterruptedException, AWTException {
        InterStateUnRegCustExcludingGST_23 excludingGST23 = new InterStateUnRegCustExcludingGST_23(driver, dataFile);
        excludingGST23.testCase23();
    }

    @AfterTest
    public void afterTest() throws IOException {
            appLogin.logout();
    }
}