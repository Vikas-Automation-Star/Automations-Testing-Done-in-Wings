package SalesInvoicesTestCases;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class TestCase_08 {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String dataFile = "src/main/resources/TestCasesData/testCase08.json";

    @BeforeTest
    public void beforeTest() throws IOException, ParseException, InterruptedException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();

    }

    @Test
    public void testCase08() throws IOException, ParseException, InterruptedException, AWTException {
        InterStatesGSTExcludingCDIncludingOCIncluding_08 tc8 = new InterStatesGSTExcludingCDIncludingOCIncluding_08(driver, dataFile);
        tc8.interStatesGSTExcludingCDIncludingOCIncluding_08();
    }

    @AfterTest
    public void afterTest() throws IOException {
//        appLogin.logout();
    }
}
