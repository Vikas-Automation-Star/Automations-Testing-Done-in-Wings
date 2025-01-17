package SalesInvoicesTestCases;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class RegCusInterStatesGSTExcludeTCSInclude_03 {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String dataFile = "./src/main/resources/TestCasesData/testCase03.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void testCase03() throws IOException, ParseException, InterruptedException, AWTException {
        InterStatesGSTExcludeTCSInclude_03 si = new InterStatesGSTExcludeTCSInclude_03(driver, dataFile);
        si.interStatesGSTExcludeTCSInclude_03();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
