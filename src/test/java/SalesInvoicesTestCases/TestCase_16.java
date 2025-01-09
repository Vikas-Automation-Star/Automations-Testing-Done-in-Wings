package SalesInvoicesTestCases;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class TestCase_16 {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String dataFile = "./src/main/resources/TestCasesData/testCase16.json";

    @BeforeTest
    public void beforeTest() throws IOException, ParseException, InterruptedException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void testCase16() throws IOException, ParseException, InterruptedException, AWTException {
        IntraStateIncludingGSTIncludingTCS_16 includingGSTIncludingTCS16 = new IntraStateIncludingGSTIncludingTCS_16(driver, dataFile);
        includingGSTIncludingTCS16.testCase16();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}