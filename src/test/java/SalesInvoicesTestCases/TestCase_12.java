package SalesInvoicesTestCases;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class TestCase_12 {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String dataFile="./src/main/resources/TestCasesData/testCase_12.json";

    @BeforeTest
    public void beforeTest() throws IOException, ParseException, InterruptedException {
        driver=appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void testCase13() throws IOException, ParseException, InterruptedException, AWTException {
       InterStateInclusiveSalesPriceLIstGSTC_OCalculations_12 inclusive=new InterStateInclusiveSalesPriceLIstGSTC_OCalculations_12(driver,dataFile);
       inclusive.interStateInclusiveSalesPriceLIstGSTC_OCalculations_12();
    }

    @AfterTest
    public void afterTest(){
//        appLogin.logout();
    }

}
