package SalesInvoicesTestCases;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class UnRegCustIncludingGSTIncludingTCSIncludingCDIncludingOC_28 {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String dataFile="./src/main/resources/TestCasesData/testCase28.json";

    @BeforeTest
    public void beforeTest() throws IOException, ParseException, InterruptedException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void testCase28() throws IOException, ParseException, InterruptedException, AWTException {
       InterStateUnRegCustIncludingGSTIncludingTCSIncludingCDIncludingOC_28 includingOC28=new InterStateUnRegCustIncludingGSTIncludingTCSIncludingCDIncludingOC_28(driver,dataFile);
       includingOC28.testCase28();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
