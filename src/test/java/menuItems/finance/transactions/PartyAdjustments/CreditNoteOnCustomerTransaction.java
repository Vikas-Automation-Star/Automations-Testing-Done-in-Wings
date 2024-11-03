package menuItems.finance.transactions.PartyAdjustments;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.finance.transactions.PartyAdjustments.CreditNoteOnCustomers;
import java.awt.*;
import java.io.IOException;

public class CreditNoteOnCustomerTransaction {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String dataFile="./src/main/resources/menuItems/finance/transaction/creditNoteOnCustomer.json";

    @BeforeTest
    public void beforeTest() throws InterruptedException, IOException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void creditNoteOnCustomer() throws InterruptedException, AWTException, IOException, ParseException {
        CreditNoteOnCustomers noteOnCustomers=new CreditNoteOnCustomers(driver,dataFile);
        noteOnCustomers.creditNoteOnCustomer();
    }

    @AfterTest
    public void afterTest(){
        appLogin.logout();
    }
}
