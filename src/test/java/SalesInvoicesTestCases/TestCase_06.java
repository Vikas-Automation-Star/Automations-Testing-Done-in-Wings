package SalesInvoicesTestCases;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class TestCase_06 {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String dataFile = "./src/main/resources/TestCasesData/testCase_06.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void testcase1() throws IOException, ParseException, InterruptedException, AWTException {
    InterStatesGSTIncludingTCSIncludingOC_06 tc=new InterStatesGSTIncludingTCSIncludingOC_06(driver,dataFile);
    }
    @AfterTest
    public void afterTest(){
//        appLogin.logout();
    }
}
