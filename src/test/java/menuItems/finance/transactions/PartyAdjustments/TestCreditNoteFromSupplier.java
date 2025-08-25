package menuItems.finance.transactions.PartyAdjustments;

import com.wings.pages.AppLogin;
import com.wings.pages.finance.transactions.PartyAdjustments.CreditNoteFromSupplier;
import io.appium.java_client.windows.WindowsDriver;

import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class TestCreditNoteFromSupplier {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String dataFile="./src/main/resources/menuItems/finance/transaction/475833 - Credit Note from Suppliers-AC_CNFS_1.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.login();
    }

    @Test
    public void receiptFromParty() throws Exception, AWTException {
        CreditNoteFromSupplier noteFromSupplier = new CreditNoteFromSupplier(driver, dataFile);
        noteFromSupplier.creditNoteFromSupplier();
    }

    @AfterTest
    public void afterTest() throws IOException {
//        appLogin.logout();
    }
}