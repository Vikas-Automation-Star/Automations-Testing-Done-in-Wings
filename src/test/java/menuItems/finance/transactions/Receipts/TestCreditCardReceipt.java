package menuItems.finance.transactions.Receipts;

import io.appium.java_client.windows.WindowsDriver;

import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.finance.transactions.Receipts.CreditCardReceipts;

import java.awt.*;
import java.io.IOException;

public class TestCreditCardReceipt {

    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String dataFile="./src/main/resources/menuItems/finance/transaction/456088 - Credit Card Receipts-AC_CCR_1.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.login();
    }

    @Test
    public void receiptFromParty() throws IOException, ParseException, InterruptedException, AWTException {
        CreditCardReceipts creditCardReceipts = new CreditCardReceipts(driver, dataFile);
        creditCardReceipts.creditCardReceipt();
    }

    @AfterTest
    public void afterTest() throws IOException {
//        appLogin.logout();
    }
}