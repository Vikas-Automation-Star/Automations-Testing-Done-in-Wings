package SalesInvoicesTestCases;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class TestCase_07 {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String dataFile = "./src/main/resources/TestCasesData/SalesInvoiceIncludeGST_TC_OtherCharges.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void testCase_07() throws IOException, ParseException, InterruptedException, AWTException {
     TC_07 tc=new TC_07(driver,dataFile);
     tc.testCase_07();
    }

    @AfterTest
    public void afterTest(){
//        appLogin.logout();
    }
}
