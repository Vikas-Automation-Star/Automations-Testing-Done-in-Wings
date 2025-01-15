package SalesInvoicesTestCases;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class UnRegCustIncludingGSTIncludingTCS_26 {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String dataFile="./src/main/resources/TestCasesData/testCase26.json";

    @BeforeTest
    public void beforeTest() throws IOException, ParseException, InterruptedException {
        driver=appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void testCase26() throws IOException, ParseException, InterruptedException, AWTException {
        InterStateUnRegCustIncludingGSTIncludingTCS_26 includingTCS26=new InterStateUnRegCustIncludingGSTIncludingTCS_26(driver,dataFile);
        includingTCS26.testCase26();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}