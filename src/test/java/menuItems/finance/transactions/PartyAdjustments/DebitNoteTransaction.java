package menuItems.finance.transactions.PartyAdjustments;

import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.finance.transactions.PartyAdjustments.DebitNote;
import java.awt.*;
import java.io.IOException;

public class DebitNoteTransaction {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String dataFile="./src/main/resources/menuItems/finance/transaction/debitNote.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException, IOException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
        Allure.step("Before Test - Debit Note");
    }

    @Test
    public void debitNote() throws InterruptedException, AWTException, IOException, ParseException {
        DebitNote debitNote=new DebitNote(driver,dataFile);
        debitNote.debitNote();
    }

    @AfterTest
    public void afterTest(){
//        appLogin.logout();
        Allure.step("After Test - Debit Note");
    }
}