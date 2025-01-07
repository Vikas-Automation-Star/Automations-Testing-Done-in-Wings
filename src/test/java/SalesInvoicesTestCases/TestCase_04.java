package SalesInvoicesTestCases;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class TestCase_04 {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String dataFile = "./src/main/resources/TestCasesData/testCase04.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
        Allure.step("Before Test TestCase01");
    }

    @Test
    public void testCase04() throws IOException, ParseException, InterruptedException, AWTException {
      InterStatesGSTIncludingTCSIncluding_04 si =new InterStatesGSTIncludingTCSIncluding_04(driver,dataFile);
      si.interStatesGSTIncludingTCSIncluding_04();
    }

    @AfterTest
    public void afterTest() throws IOException{
//        appLogin.logout();
        Allure.step("After Test TestCase 01");
    }
}
