package menuItems.finance.transactions.Journals;

import com.wings.pages.Transaction;
import com.wings.pages.TransactionsBaseClass;
import com.wings.pages.finance.transactions.PartyAdjustments.DebitNoteFromSuppliers;
import io.appium.java_client.windows.WindowsDriver;

import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.finance.transactions.Journals.BookIncomesOrReceivables;

import java.awt.*;
import java.io.IOException;

public class TestBookIncomeOrReceivables {

    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    private static final String TEMP_API_BODY_DEBIT_NOTE_FROM_CUSTOMERS="./output/temp_api_request_bodies/DebitNoteFromCustomer.json";
    private static final String API_RESPONSE_DEBIT_NOTE_FROM_CUSTOMERS="./output/api_responses/DebitNoteFromCustomer.json";
    private static final String OUTPUT_FILE1="./src/main/resources/menuItems/finance/transaction/460743 - Debit Note from Suppliers-AC_DNFS_1_Output.xls";

    private static final String TEMP_API_BODY_BOOK_INCOMES_OR_RECEIVABLE="./output/temp_api_request_bodies/BookIncomesOrReceivable.json";
    private static final String API_RESPONSE_BOOK_INCOMES_OR_RECEIVABLE="./output/api_responses/BookIncomesOrReceivable.json";
    private static final String OUTPUT_FILE2="./src/main/resources/menuItems/finance/transaction/475835 - Book Incomes or Receivables-AC_BIR_1_Output.xls";

    String dataFile="./src/main/resources/menuItems/finance/transaction/460743 - Debit Note from Suppliers-AC_DNFS_1.xls";
    String dataFile1="./src/main/resources/menuItems/finance/transaction/475835 - Book Incomes or Receivables-AC_BIR_1.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.login();
    }

    @Test
    public void bookIncomesOrReceivable() throws Exception {
        DebitNoteFromSuppliers noteFromSuppliers = new DebitNoteFromSuppliers(driver, dataFile);
        String DNFS=noteFromSuppliers.debitNoteFromSupplier("PV 14",TEMP_API_BODY_DEBIT_NOTE_FROM_CUSTOMERS,API_RESPONSE_DEBIT_NOTE_FROM_CUSTOMERS,OUTPUT_FILE1);

        appLogin.logout();
        driver= appLogin.login();

        BookIncomesOrReceivables incomesOrReceivables = new BookIncomesOrReceivables(driver, dataFile1);
        incomesOrReceivables.receivables(DNFS,TEMP_API_BODY_BOOK_INCOMES_OR_RECEIVABLE,API_RESPONSE_BOOK_INCOMES_OR_RECEIVABLE,OUTPUT_FILE2);
    }

    @AfterTest
    public void afterTest() throws IOException, InterruptedException {
//        TransactionsBaseClass transactionsBaseClass=new TransactionsBaseClass(driver);
//        Transaction transaction=new Transaction(driver);
//        transaction.navigateToMastersWhen3Steps("Finance","Party Adjustments","Debit Note from Suppliers");
//        Thread.sleep(1000);
//        transactionsBaseClass.deleteRecentTransaction();
        appLogin.logout();
    }
}