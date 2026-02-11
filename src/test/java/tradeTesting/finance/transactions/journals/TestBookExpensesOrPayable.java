package tradeTesting.finance.transactions.journals;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestBookExpensesOrPayable {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    private static final String TEMP_API_BODY_BOOK_EXPENSES_OR_PAYABLE="./output/temp_api_request_bodies/BookExpensesOrPayable.json";
    private static final String API_RESPONSE_BOOK_EXPENSES_OR_PAYABLE="./output/api_responses/BookExpensesOrPayable.json";
    private static final String OUTPUT_FILE="./src/main/resources/tradeAutomation/finance/transactions/462279 - Book Expenses or Payables-AC_BEP_1_PRWIR_1_Output.xls";

    String dataFile="./src/main/resources/tradeAutomation/finance/transactions/462279 - Book Expenses or Payables-AC_BEP_1_PRWIR_1.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void bookExpensesOrPayable() throws Exception {
        BookExpensesOrPayable payable=new BookExpensesOrPayable(driver,dataFile);
        payable.bookExpensesOrPayable("PRWIR 1","","","");
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }

}
