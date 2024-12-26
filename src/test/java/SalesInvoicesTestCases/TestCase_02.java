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

public class TestCase_02 {

        WindowsDriver driver;
        AppLogin appLogin = new AppLogin();
        String dataFile = "./src/main/resources/TestCasesData/testCase_02.json";

        @BeforeTest
        public void beforeTest() throws IOException, InterruptedException, ParseException {
            driver = appLogin.launchSingleUserApp();
            appLogin.singleUserLogin();
            Allure.step("Before Test TestCase02");
        }

        @Test
        public void testcase2() throws IOException, ParseException, InterruptedException, AWTException {
           InterStateIncludingGST_02 tc02=new InterStateIncludingGST_02(driver,dataFile);
           tc02.testCase2();
        }

        @AfterTest
        public void afterTest(){
            appLogin.logout();
            Allure.step("After Test TestCase 02");
        }
    }

