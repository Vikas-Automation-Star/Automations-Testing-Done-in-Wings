package SalesInvoicesTestCases;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class UnRegCustIncludingGSTIncludingTCSIncludingCD_27 {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String dataFile="./src/main/resources/TestCasesData/testCase27.json";

    @BeforeTest
    public void beforeTest() throws IOException, ParseException, InterruptedException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void testCase27() throws IOException, ParseException, InterruptedException, AWTException {
        InterStateUnRegCustIncludingGSTIncludingTCSIncludingCD_27 includingCD_27=new InterStateUnRegCustIncludingGSTIncludingTCSIncludingCD_27(driver,dataFile);
        includingCD_27.testCase27();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
