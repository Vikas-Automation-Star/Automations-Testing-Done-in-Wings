package SalesInvoicesTestCases;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class UnRegCustExludingGSTIncludingTCS_25 {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String dataFile = "./src/main/resources/TestCasesData/testCase25.json";

    @BeforeTest
    public void beforeTest() throws IOException, ParseException, InterruptedException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void testCase25() throws IOException, ParseException, InterruptedException, AWTException {
        InterStateUnRegCustExludingGSTIncludingTCS_25 includingTCS25=new InterStateUnRegCustExludingGSTIncludingTCS_25(driver,dataFile);
        includingTCS25.testCase25();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
