package SalesInvoicesTestCases;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class TestCase_09 {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String dataFile="./src/main/resources/TestCasesData/testCase09.json";

    @BeforeTest
    public void befortest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void testCase09() throws IOException, ParseException, InterruptedException, AWTException {
        SalesInvoiceBatchDetailsFetching_09 si=new SalesInvoiceBatchDetailsFetching_09(driver,dataFile);
        si.SalesInvoiceBatchDetailsFetching_09();
    }

    @AfterTest
    public void afterTest() throws IOException, InterruptedException {
//        appLogin.logout();
    }
}
