package menuItems.finance.transactions.PartyAdjustments;

import com.wings.pages.AppLogin;
import com.wings.pages.finance.transactions.PartyAdjustments.DebitNote;
import io.appium.java_client.windows.WindowsDriver;

import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class TestDebitNote {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String dataFile="./src/main/resources/menuItems/finance/transaction/475827 - Debit Note-AC_DN_1.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.login();
    }

    @Test
    public void receiptFromParty() throws IOException, ParseException, InterruptedException, AWTException {
        DebitNote debitNote = new DebitNote(driver, dataFile);
        debitNote.debitNote();
    }

    @AfterTest
    public void afterTest() throws IOException {
//        appLogin.logout();
    }
}