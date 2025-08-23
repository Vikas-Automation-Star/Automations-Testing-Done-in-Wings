package menuItems.finance.transactions.PartyAdjustments;

import com.wings.pages.AppLogin;
import com.wings.pages.finance.transactions.PartyAdjustments.CreditNoteOnCustomers;
import io.appium.java_client.windows.WindowsDriver;

import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class TestCreditNoteOnCustomer {

    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String dataFile="./src/main/resources/menuItems/finance/transaction/475832 - Credit Note on Customers-AC_CNOC_1.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.login();
    }

    @Test
    public void receiptFromParty() throws IOException, ParseException, InterruptedException, AWTException {
        CreditNoteOnCustomers noteOnCustomers = new CreditNoteOnCustomers(driver, dataFile);
        noteOnCustomers.creditNoteOnCustomer();
    }

    @AfterTest
    public void afterTest() throws IOException {
//        appLogin.logout();
    }
}
