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

public class SalesInvoiceIncludeGSt_TCS_OtherChargesTransaction {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String dataFile = "./src/main/resources/TestCasesData/SalesInvoiceIncludeGST_TC_OtherCharges.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void testcase1() throws IOException, ParseException, InterruptedException, AWTException {
//        SalesInvoiceIncludingGST_TCS_OtherCharges sioc=new SalesInvoiceIncludingGST_TCS_OtherCharges(driver,dataFile);
//        sioc.salesInvovoiceIcludeGST_TCS_OtherCharges();
    }
    @AfterTest
    public void afterTest(){
//        appLogin.logout();
    }
}
