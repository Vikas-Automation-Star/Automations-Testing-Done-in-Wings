package SalesInvoicesTestCases;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class TestCase_13 {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String dataFile="./src/main/resources/TestCasesData/testCase13.json";

    @BeforeTest
    public void beforeTest() throws IOException, ParseException, InterruptedException {
        driver=appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void testCase13() throws IOException, ParseException, InterruptedException, AWTException {
        IntraStateExcludingGST_13 tc13=new IntraStateExcludingGST_13(driver,dataFile);
        tc13.testCase13();
    }

    @AfterTest
    public void afterTest(){
//        appLogin.logout();
    }
}
