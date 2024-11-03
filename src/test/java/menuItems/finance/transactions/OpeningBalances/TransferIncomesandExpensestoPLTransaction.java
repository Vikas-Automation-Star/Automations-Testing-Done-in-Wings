package menuItems.finance.transactions.OpeningBalances;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.finance.transactions.OpeningBalances.TransferIncomesandExpensestoPL;
import java.awt.*;
import java.io.IOException;

public class TransferIncomesandExpensestoPLTransaction {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String dataFile="./src/main/resources/menuItems/finance/transaction/incomesAndExpenses.json";

    @BeforeTest
    public void beforeTest() throws IOException, ParseException, InterruptedException {
        driver=appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void transferIncomeandExpenses() throws IOException, ParseException, InterruptedException, AWTException {
        TransferIncomesandExpensestoPL incomesandExpensestoPL=new TransferIncomesandExpensestoPL(driver,dataFile);
        incomesandExpensestoPL.incomeAndExpenses();
    }

    @AfterTest
    public void afterTest(){
        appLogin.logout();
    }
}
