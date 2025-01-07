package util;

import com.wings.pages.AppLogin;
import com.wings.pages.finance.reports.Balances.AccountBalances;
import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class SampleTest1 {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
        Allure.step("Before Test Sample Test 1");
    }

    @Test
    public void accountBalance() throws  InterruptedException, AWTException {
        AccountBalances accountBalances=new AccountBalances(driver);
        accountBalances.accountBalanceReport();
        Assert.assertEquals("hi","bye");
    }

    @AfterTest
    public void afterTest() throws IOException{
        appLogin.logout();
        Allure.step("After test Sample Test 1");
    }
}
