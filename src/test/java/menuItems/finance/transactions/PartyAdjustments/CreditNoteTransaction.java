package menuItems.finance.transactions.PartyAdjustments;

import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.finance.transactions.PartyAdjustments.CreditNote;
import java.awt.*;
import java.io.IOException;

public class CreditNoteTransaction {
        WindowsDriver driver;
        AppLogin appLogin = new AppLogin();
        String dataFile="./src/main/resources/menuItems/finance/transaction/creditNote.json";

        @BeforeTest
        public void beforeTest() throws IOException, InterruptedException, ParseException, IOException, ParseException {
            driver = appLogin.launchSingleUserApp();
            appLogin.singleUserLogin();
            Allure.step("Before Test - Credit Note");
        }

        @Test
        public void creditNote() throws InterruptedException, AWTException, IOException, ParseException {
            CreditNote creditNote=new CreditNote(driver,dataFile);
            creditNote.creditNote();
        }

        @AfterTest
        public void afterTest() throws IOException{
            appLogin.logout();
            Allure.step("After Test - Credit Note");
        }
}