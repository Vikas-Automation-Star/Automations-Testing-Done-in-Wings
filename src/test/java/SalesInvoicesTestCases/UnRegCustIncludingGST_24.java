package SalesInvoicesTestCases;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class UnRegCustIncludingGST_24 {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String dataFile = "./src/main/resources/TestCasesData/testCase24.json";

    @BeforeTest
    public void beforeTest() throws IOException, ParseException, InterruptedException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void testCase24() throws IOException, ParseException, InterruptedException, AWTException {
        InterStateUnRegCustIncludingGST_24 includingGST24=new InterStateUnRegCustIncludingGST_24(driver,dataFile);
        includingGST24.testCase24();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}