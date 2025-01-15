package SalesInvoicesTestCases;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class UnRegCustIncludingGSTIncludingTCSIncludingOC_29 {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String dataFile="./src/main/resources/TestCasesData/testCase29.json";

    @BeforeTest
    public void beforeTest() throws IOException, ParseException, InterruptedException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void testCase29() throws IOException, ParseException, InterruptedException, AWTException {
        InterStateUnRegCustIncludingGSTIncludingTCSIncludingOC_29 includingOC_29=new InterStateUnRegCustIncludingGSTIncludingTCSIncludingOC_29(driver,dataFile);
        includingOC_29.testCase29();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}