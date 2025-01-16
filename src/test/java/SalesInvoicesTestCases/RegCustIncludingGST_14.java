package SalesInvoicesTestCases;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class RegCustIncludingGST_14 {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String dataFile = "./src/main/resources/TestCasesData/testCase14.json";

    @BeforeTest
    public void beforeTest() throws IOException, ParseException, InterruptedException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void testCase14() throws IOException, ParseException, InterruptedException, AWTException {
        IntraStateIncludingGST_14 tc = new IntraStateIncludingGST_14(driver, dataFile);
        tc.testCase14();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
