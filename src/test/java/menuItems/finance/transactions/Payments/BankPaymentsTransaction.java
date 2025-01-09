package menuItems.finance.transactions.Payments;

import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.finance.transactions.Payments.BankPayment;

import java.awt.*;
import java.io.IOException;

public class BankPaymentsTransaction {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String dataFile = "./src/main/resources/menuItems/finance/transaction/bankPayment.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
        Allure.step("Before Test - Bank Payments");
    }

    @Test
    public void bankPayment() throws InterruptedException, AWTException, IOException, ParseException {
        BankPayment bankPayment = new BankPayment(driver, dataFile);
        bankPayment.bankPayment();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
        Allure.step("After Test - Bank Payments");
    }
}