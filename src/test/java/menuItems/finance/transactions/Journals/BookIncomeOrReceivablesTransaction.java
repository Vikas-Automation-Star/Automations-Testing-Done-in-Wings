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

public class BookIncomeOrReceivablesTransaction {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String dataFile="./src/main/resources/menuItems/finance/transaction/bookIncome.json";

    @BeforeTest
    public void beforeTest() throws IOException, ParseException, InterruptedException {
        driver=appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void receivables() throws IOException, ParseException, InterruptedException, AWTException {
        BookIncomesOrReceivables incomesOrReceivables=new BookIncomesOrReceivables(driver,dataFile);
        incomesOrReceivables.receivables();
    }

//    @AfterTest
//    public void afterTest(){
//        appLogin.logout();
//    }
}
