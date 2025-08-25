package menuItems.finance.transactions.Journals;

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
    String dataFile="./src/main/resources/menuItems/finance/transaction/475835 - Book Incomes or Receivables-AC_BIR_1.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.login();
    }

    @Test
    public void receiptFromParty() throws Exception, AWTException {
        BookIncomesOrReceivables incomesOrReceivables = new BookIncomesOrReceivables(driver, dataFile);
        incomesOrReceivables.receivables();
    }

    @AfterTest
    public void afterTest() throws IOException {
//        appLogin.logout();
    }
}