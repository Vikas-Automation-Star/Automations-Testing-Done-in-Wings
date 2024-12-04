package util;

import com.wings.pages.AppLogin;
import com.wings.pages.finance.reports.Balances.SupplierBalances;
import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class SampleTest3 {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
        Allure.step("Before Test Sample Test 3");
    }

    @Test
    public void supplierBalance() throws  InterruptedException, AWTException {
        SupplierBalances supplierBalances=new SupplierBalances(driver);
        supplierBalances.supplierBalanceReport();
        Assert.assertEquals("hi","bye");
    }

    @AfterTest
    public void afterTest(){
        appLogin.logout();
        Allure.step("After test Sample Test 3");

    }
}
