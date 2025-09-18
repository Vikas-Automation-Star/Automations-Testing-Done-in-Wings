package menuItems.finance.transactions.Journals;

import io.appium.java_client.windows.WindowsDriver;

import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.finance.transactions.Journals.BookExpensesOrPayables;

import java.io.IOException;

public class TestBookExpensesOrPayable {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    private static final String TEMP_API_BODY_BOOK_INCOMES_OR_PAYABLE="./output/temp_api_request_bodies/bookIncomesOrPayable.json";
    private static final String API_RESPONSE_BOOK_INCOMES_OR_PAYABLE="./output/api_responses/bookIncomesOrPayable.json";
    private static final String OUTPUT_FILE="./src/main/resources/menuItems/finance/transaction/462279 - Book Expenses or Payables-AC_BEP_1_Output.xls";

    String dataFile="./src/main/resources/menuItems/finance/transaction/462279 - Book Expenses or Payables-AC_BEP_1.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.login();
    }

    @Test
    public void bookIncomesOrPayable() throws Exception {
        BookExpensesOrPayables expensesOrPayable = new BookExpensesOrPayables(driver, dataFile);
        expensesOrPayable.Payable(TEMP_API_BODY_BOOK_INCOMES_OR_PAYABLE,API_RESPONSE_BOOK_INCOMES_OR_PAYABLE,OUTPUT_FILE);
    }

    @AfterTest
    public void afterTest() throws IOException {
//        appLogin.logout();
    }

}