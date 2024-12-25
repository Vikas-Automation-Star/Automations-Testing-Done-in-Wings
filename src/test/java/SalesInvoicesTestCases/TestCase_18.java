package SalesInvoicesTestCases;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class TestCase_18 {
        WindowsDriver driver;
        AppLogin appLogin=new AppLogin();
        String dataFile="./src/main/resources/TestCasesData/testCase18.json";

        @BeforeTest
        public void beforeTest() throws IOException, ParseException, InterruptedException {
            driver=appLogin.launchSingleUserApp();
            appLogin.singleUserLogin();
        }

        @Test
        public void testCase18() throws IOException, ParseException, InterruptedException, AWTException {
            IntraStateIncludingGSTIncludingTCSIncludingOtherCharges_18 tcsIncludingOtherCharges18 = new IntraStateIncludingGSTIncludingTCSIncludingOtherCharges_18(driver,dataFile);
            tcsIncludingOtherCharges18.testCase18();
        }

        @AfterTest
        public void afterTest(){
//        appLogin.logout();
        }
}