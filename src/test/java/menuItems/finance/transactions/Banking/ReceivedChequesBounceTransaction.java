package menuItems.finance.transactions.Banking;

import com.wings.pages.finance.transactions.Banking.ReceivedChequesBounce;
import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;

import java.awt.*;
import java.io.IOException;

public class ReceivedChequesBounceTransaction {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String dataFile = "./src/main/resources/menuItems/finance/transaction/chequesBounce.json";

    @BeforeTest
    public void beforeTest() throws IOException, ParseException, InterruptedException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
        Allure.step("Before Test - Received Cheques Bounce");
    }

    @Test
    public void chequesBounce() throws IOException, ParseException, InterruptedException, AWTException {
        ReceivedChequesBounce chequesBounce = new ReceivedChequesBounce(driver, dataFile);
        chequesBounce.receivedCheckBounce();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
        Allure.step("After Test - Received Cheques Bounce");
    }
}