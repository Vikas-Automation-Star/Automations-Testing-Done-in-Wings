package TestCases;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class SalesInvoiceIncludeGSTIncludeTCSTransaction {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String dataFile = "./src/main/resources/TestCasesData/salesInvoicesIncludeGSTAndExcludeTCS.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
        Allure.step("Before Test TestCase01");
    }

    @Test
    public void testcase1() throws IOException, ParseException, InterruptedException, AWTException {
      SalesInvoiceIncludeGSTIncludeTCS siigst=new SalesInvoiceIncludeGSTIncludeTCS(driver,dataFile);
      siigst.salesInvoiceIncludeGSTAndIncludeTCS();
    }
    @AfterTest
    public void afterTest(){
        appLogin.logout();
        Allure.step("After Test TestCase 01");
    }
}
