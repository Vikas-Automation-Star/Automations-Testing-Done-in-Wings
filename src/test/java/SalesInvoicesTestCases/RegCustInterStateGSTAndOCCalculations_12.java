package SalesInvoicesTestCases;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class RegCustInterStateGSTAndOCCalculations_12 {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String dataFile = "./src/main/resources/TestCasesData/testCase12.json";

    @BeforeTest
    public void beforeTest() throws IOException, ParseException, InterruptedException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void testCase12() throws IOException, ParseException, InterruptedException, AWTException {
        InterStateInclusiveSalesPriceListGSTC_OCalculations_12 inclusive = new InterStateInclusiveSalesPriceListGSTC_OCalculations_12(driver, dataFile);
        inclusive.interStateInclusiveSalesPriceListGSTC_OCalculations_12();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }

}
