package tradeTesting.finance.transactions.journals;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestBookIncomesOrReceivables {

    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    private static final String TEMP_API_BODY_DEBIT_NOTE_FROM_CUSTOMERS="./output/temp_api_request_bodies/DebitNoteFromCustomer.json";
    private static final String API_RESPONSE_DEBIT_NOTE_FROM_CUSTOMERS="./output/api_responses/DebitNoteFromCustomer.json";
    private static final String OUTPUT_FILE1="./";

    private static final String TEMP_API_BODY_BOOK_INCOMES_OR_RECEIVABLE="./output/temp_api_request_bodies/BookIncomesOrReceivable.json";
    private static final String API_RESPONSE_BOOK_INCOMES_OR_RECEIVABLE="./output/api_responses/BookIncomesOrReceivable.json";
    private static final String OUTPUT_FILE2="./src/main/resources/tradeAutomation/finance/transactions/475835 - Book Incomes or Receivables-AC_BIR_1_Output.xls";

    String dataFile="./";
    String dataFile1="./src/main/resources/tradeAutomation/finance/transactions/475835 - Book Incomes or Receivables-AC_BIR_1.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void bookIncomesOrReceivable() throws Exception {
//        DebitNoteFromSuppliers debitNoteFromSuppliers = new DebitNoteFromSuppliers(driver, dataFile);
//        String debitNoteFromSupplierVoucher=debitNoteFromSuppliers.debitNoteFromSupplier("PV 14",TEMP_API_BODY_DEBIT_NOTE_FROM_CUSTOMERS,API_RESPONSE_DEBIT_NOTE_FROM_CUSTOMERS,OUTPUT_FILE1);
//
//        appLogin.logout();
//        driver= appLogin.login();

        BookIncomesOrReceivables receivables=new BookIncomesOrReceivables(driver,dataFile1);
        receivables.bookIncomesOrReceivables("SR 1","","","");
    }

    @AfterTest
    public void afterTest() throws IOException, InterruptedException {
        appLogin.logout();
    }

}
