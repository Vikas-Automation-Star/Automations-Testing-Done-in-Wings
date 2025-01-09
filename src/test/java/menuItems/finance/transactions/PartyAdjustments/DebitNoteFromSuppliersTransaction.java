package menuItems.finance.transactions.PartyAdjustments;

import com.wings.pages.AppLogin;
import com.wings.pages.finance.transactions.PartyAdjustments.DebitNoteFromSuppliers;
import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class DebitNoteFromSuppliersTransaction {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String dataFile = "./src/main/resources/menuItems/finance/transaction/debitNoteFromSupplier.json";

    @BeforeTest
    public void beforeTest() throws InterruptedException, IOException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
        Allure.step("Before Test - Debit Note From Supplier");
    }

    @Test
    public void debitNoteFromSupplier() throws InterruptedException, AWTException, IOException, ParseException {
        DebitNoteFromSuppliers noteFromSuppliers = new DebitNoteFromSuppliers(driver, dataFile);
        noteFromSuppliers.debitNoteFromSupplier();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
        Allure.step("After Test - Debit Note From Supplier");
    }
}
