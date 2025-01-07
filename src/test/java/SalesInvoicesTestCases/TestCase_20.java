package SalesInvoicesTestCases;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class TestCase_20 {

        WindowsDriver driver;
        AppLogin appLogin=new AppLogin();
        String dataFile="./src/main/resources/TestCasesData/testCase20.json";

        @BeforeTest
        public void beforeTest() throws IOException, ParseException, InterruptedException {
            driver=appLogin.launchSingleUserApp();
            appLogin.singleUserLogin();
        }
        @Test
        public void testCase20() throws IOException, ParseException, InterruptedException, AWTException {
            IntraStateExcludingGSTIncludingCDIncludingOC_20 tc20=new IntraStateExcludingGSTIncludingCDIncludingOC_20(driver,dataFile);
            tc20.testCase20();
        }

        @AfterTest
        public void afterTest() throws IOException{
//            appLogin.logout();
        }
    }