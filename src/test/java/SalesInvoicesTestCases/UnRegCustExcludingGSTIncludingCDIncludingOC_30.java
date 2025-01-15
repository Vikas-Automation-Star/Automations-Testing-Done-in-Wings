package SalesInvoicesTestCases;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class UnRegCustExcludingGSTIncludingCDIncludingOC_30 {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String dataFile = "./src/main/resources/TestCasesData/testCase30.json";

    @BeforeTest
    public void beforeTest() throws IOException, ParseException, InterruptedException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void testCase30() throws IOException, ParseException, InterruptedException, AWTException {
       InterStateUnRegCustExcludingGSTIncludingOCIncludingCD_30 includingCD30=new InterStateUnRegCustExcludingGSTIncludingOCIncludingCD_30(driver,dataFile);
       includingCD30.testCase30();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
