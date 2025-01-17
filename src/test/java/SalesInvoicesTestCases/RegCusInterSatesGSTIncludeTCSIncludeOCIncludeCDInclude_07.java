package SalesInvoicesTestCases;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class RegCusInterSatesGSTIncludeTCSIncludeOCIncludeCDInclude_07 {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String dataFile = "./src/main/resources/TestCasesData/testCase07.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void testCase07() throws IOException, ParseException, InterruptedException, AWTException {
        InterSatesGSTIncludingTCSIncludingOCIncludingCDIncluding_07 tc = new InterSatesGSTIncludingTCSIncludingOCIncludingCDIncluding_07(driver, dataFile);
        tc.interSatesGSTIncludingTCSIncludingOCIncludingCDIncluding_07();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
