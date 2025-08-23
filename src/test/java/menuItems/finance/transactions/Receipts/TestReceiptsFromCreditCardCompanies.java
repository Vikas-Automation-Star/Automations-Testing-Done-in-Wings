package menuItems.finance.transactions.Receipts;

import com.wings.pages.AppLogin;
import com.wings.pages.finance.transactions.Receipts.ReceiptsFromCreditCardCompanies;
import com.wings.pages.finance.transactions.Receipts.ReceiptsFromParties;
import io.appium.java_client.windows.WindowsDriver;

import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class TestReceiptsFromCreditCardCompanies {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String dataFile="./src/main/resources/menuItems/finance/transaction/458629 - Receipts from Credit Card Companies-AC_RFCCC_1.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.login();
    }

    @Test
    public void receiptFromParty() throws IOException, ParseException, InterruptedException, AWTException {
        ReceiptsFromCreditCardCompanies creditCardCompanies = new ReceiptsFromCreditCardCompanies(driver, dataFile);
        creditCardCompanies.creditCardCompanyReceipt("SI 2");
    }

    @AfterTest
    public void afterTest() throws IOException {
//        appLogin.logout();
    }
}