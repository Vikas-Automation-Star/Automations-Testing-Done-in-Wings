package util;

import com.wings.pages.AppLogin;
import com.wings.pages.finance.reports.Balances.CustomerBalances;
import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class SampleTest2 {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
        Allure.step("Before Test Sample Test 2");
    }

    @Test
    public void customerBalance() throws InterruptedException, AWTException {
        CustomerBalances customerBalances = new CustomerBalances(driver);
        customerBalances.customerBalanceReport();
        Assert.assertEquals("hia", "bfye");
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
        Allure.step("After test Sample Test 2");
    }
}
