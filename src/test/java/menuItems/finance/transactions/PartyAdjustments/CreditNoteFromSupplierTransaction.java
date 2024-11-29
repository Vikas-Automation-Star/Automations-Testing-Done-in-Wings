package menuItems.finance.transactions.PartyAdjustments;

import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.finance.transactions.PartyAdjustments.CreditNoteFromSupplier;
import java.awt.*;
import java.io.IOException;

public class CreditNoteFromSupplierTransaction {
        WindowsDriver driver;
        AppLogin appLogin = new AppLogin();
        String dataFile="./src/main/resources/menuItems/finance/transaction/debitNoteFromSupplier.json";

        @BeforeTest
        public void beforeTest() throws InterruptedException, IOException, ParseException {
            driver = appLogin.launchSingleUserApp();
            appLogin.singleUserLogin();
            Allure.step("Before Test - Credit note From Supplier");
        }

        @Test
        public void creditNoteFromSupplier() throws InterruptedException, AWTException, IOException, ParseException {
            CreditNoteFromSupplier noteFromSupplier=new CreditNoteFromSupplier(driver,dataFile);
            noteFromSupplier.creditNoteFromSupplier();
        }

        @AfterTest
        public void afterTest(){
            appLogin.logout();
            Allure.step("After Test - Credit note From Supplier");
        }
    }