package menuItems.finance.transactions.Journals;

import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.finance.transactions.Journals.BookExpensesOrPayables;

import java.awt.*;
import java.io.IOException;

public class BookExpensesOrPayablesTransaction {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String dataFile = "./src/main/resources/menuItems/finance/transaction/bookExpenses.json";

    @BeforeTest
    public void beforeTest() throws IOException, ParseException, InterruptedException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
        Allure.step("Before Test - Book Expenses or Payables");
    }

    @Test
    public void payables() throws IOException, ParseException, InterruptedException, AWTException {
        BookExpensesOrPayables expensesOrPayables = new BookExpensesOrPayables(driver, dataFile);
        expensesOrPayables.payables();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
        Allure.step("After Test - Book Expenses or Payables");
    }
}