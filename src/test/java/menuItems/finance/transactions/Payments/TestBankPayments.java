package menuItems.finance.transactions.Payments;

import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.finance.transactions.Payments.BankPayment;

import java.awt.*;
import java.io.IOException;

public class TestBankPayments {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    Common common;
    String file = "./src/main/resources/menuItems/finance/transaction/bankPayment.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        common=new Common(driver);
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin(common.getData(file,"bankPayments","userName"),common.getData(file,"bankPayments","password"));
    }

    @Test
    public void bankPayment() throws InterruptedException, AWTException, IOException, ParseException {
        BankPayment bankPayment = new BankPayment(driver, file);
        bankPayment.bankPayment();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}