package menuItems.finance.transactions.PartyAdjustments;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.finance.transactions.PartyAdjustments.AdjustPartyBills;
import com.wings.pages.finance.transactions.PartyAdjustments.CreditNote;
import com.wings.pages.finance.transactions.PartyAdjustments.CreditNoteAdjustPartyBills;
import com.wings.pages.sales.transactions.SalesInvoice;
import java.awt.*;
import java.io.IOException;

public class AdjustPartyBillsTransaction {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String partyBillFile="./src/main/resources/menuItems/finance/transaction/adjustPartyBills.json";
    String salesInvoiceFile="./src/main/resources/menuItems/Sales/Transactions/salesInvoice.json";
    String creditNoteFile="./src/main/resources/menuItems/finance/transaction/creditNote.json";

    @BeforeTest
    public void beforeTest() throws InterruptedException, IOException, ParseException, AWTException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
        SalesInvoice salesInvoice=new SalesInvoice(driver,salesInvoiceFile);
        salesInvoice.salesInvoice();
        CreditNoteAdjustPartyBills noteAdjustPartyBills=new CreditNoteAdjustPartyBills(driver,creditNoteFile);
        noteAdjustPartyBills.creditNoteAdjustBills();
    }

    @Test
    public void partyBills() throws InterruptedException, IOException, ParseException {
        AdjustPartyBills partyBills=new AdjustPartyBills(driver,partyBillFile);
        partyBills.executeAdjustPartyBills();
    }

//    @AfterTest
//    public void afterTest(){
//        appLogin.logout();
//    }
}
