package SalesInvoicesTestCases;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class TestCase_10 {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String dataFile="./src/main/resources/TestCasesData/testCase_10.json";

    @BeforeTest
    public void beforeTest() throws IOException, ParseException, InterruptedException {
        driver=appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void tesCase10() throws IOException, ParseException, InterruptedException, AWTException {
       InterStateExclusiveSalesPriceLIstGSTCalculations_10 exclusive=new InterStateExclusiveSalesPriceLIstGSTCalculations_10(driver,dataFile);
       exclusive.interStateExclusiveSalesPriceLIstGSTCalculations_10();
    }

    @AfterTest
    public void afterTest(){
//        appLogin.logout();
    }
}
