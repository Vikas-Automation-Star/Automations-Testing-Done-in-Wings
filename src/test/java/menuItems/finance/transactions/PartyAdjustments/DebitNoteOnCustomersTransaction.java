package menuItems.finance.transactions.PartyAdjustments;

import com.wings.pages.AppLogin;
import com.wings.pages.finance.transactions.PartyAdjustments.DebitNoteOnCustomer;
import io.appium.java_client.windows.WindowsDriver;

import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class DebitNoteOnCustomersTransaction {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String dataFile = "./src/main/resources/menuItems/finance/transaction/debitNoteOnCustomer.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException, IOException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();

    }

    @Test
    public void debitNote() throws InterruptedException, AWTException, IOException, ParseException {
        DebitNoteOnCustomer noteOnCustomer = new DebitNoteOnCustomer(driver, dataFile);
        noteOnCustomer.debitNoteOnCustomer();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();

    }
}