package SalesInvoicesTestCases;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class TestCase_17 {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String dataFile="./src/main/resources/TestCasesData/testCase17.json";

    @BeforeTest
    public void beforeTest() throws IOException, ParseException, InterruptedException {
        driver=appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void testCase17() throws IOException, ParseException, InterruptedException, AWTException {
        IntraStateIncludingGSTIncludingTCSIncludingCD_17 tc17=new IntraStateIncludingGSTIncludingTCSIncludingCD_17(driver,dataFile);
        tc17.testCase17();
    }

    @AfterTest
    public void afterTest(){
//        appLogin.logout();
    }
}