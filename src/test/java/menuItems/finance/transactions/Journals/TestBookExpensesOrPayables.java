package menuItems.finance.transactions.Journals;

import io.appium.java_client.windows.WindowsDriver;

import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.finance.transactions.Journals.BookExpensesOrPayables;

import java.awt.*;
import java.io.IOException;

public class TestBookExpensesOrPayables {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String dataFile="./src/main/resources/menuItems/finance/transaction/462279 - Book Expenses or Payables-AC_BEP_1.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.login();
    }

    @Test
    public void receiptFromParty() throws Exception, AWTException {
        BookExpensesOrPayables expensesOrPayables = new BookExpensesOrPayables(driver, dataFile);
        expensesOrPayables.Payables();
    }

    @AfterTest
    public void afterTest() throws IOException {
//        appLogin.logout();
    }

}