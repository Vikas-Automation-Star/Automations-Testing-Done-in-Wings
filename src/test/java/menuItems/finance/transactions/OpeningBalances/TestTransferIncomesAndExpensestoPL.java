package menuItems.finance.transactions.OpeningBalances;

import com.wings.pages.AppLogin;
import com.wings.pages.finance.transactions.OpeningBalances.TransferIncomesAndExpensestoPL;
import io.appium.java_client.windows.WindowsDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;

public class TestTransferIncomesAndExpensestoPL {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String dataFile = "./src/main/resources/menuItems/finance/transaction/439851 - Transfer Incomes and Expenses to PL-AC_TIE_1.xls";

    @BeforeTest
    public void beforeTest() throws Exception {
        driver = appLogin.login();
    }

    @Test
    public void transferIncomeandExpenses() throws Exception {
        TransferIncomesAndExpensestoPL incomesandExpensestoPL = new TransferIncomesAndExpensestoPL(driver, dataFile);
        incomesandExpensestoPL.incomeAndExpenses();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}