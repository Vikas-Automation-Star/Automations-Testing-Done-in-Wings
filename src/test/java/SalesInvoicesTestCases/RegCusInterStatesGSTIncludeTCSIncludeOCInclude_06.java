package SalesInvoicesTestCases;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class RegCusInterStatesGSTIncludeTCSIncludeOCInclude_06 {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String dataFile = "./src/main/resources/TestCasesData/testCase06.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void testCase06() throws IOException, ParseException, InterruptedException, AWTException {
        InterStatesGSTIncludingTCSIncludingOCIncluding_06  si=new InterStatesGSTIncludingTCSIncludingOCIncluding_06(driver,dataFile);
        si.interStatesGSTIncludingTCSIncludingOC_06();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
