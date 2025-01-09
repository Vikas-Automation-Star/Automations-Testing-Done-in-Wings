package menuItems.finance.transactions.OpeningBalances;

import com.wings.pages.AppLogin;
import com.wings.pages.finance.transactions.OpeningBalances.TransferIncomesandExpensestoPL;
import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class TransferIncomesandExpensestoPLTransaction {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String dataFile = "./src/main/resources/menuItems/finance/transaction/incomesAndExpenses.json";

    @BeforeTest
    public void beforeTest() throws IOException, ParseException, InterruptedException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
        Allure.step("Before Test - Transfer Incomes and Expenses to PL");
    }

    @Test
    public void transferIncomeandExpenses() throws IOException, ParseException, InterruptedException, AWTException {
        TransferIncomesandExpensestoPL incomesandExpensestoPL = new TransferIncomesandExpensestoPL(driver, dataFile);
        incomesandExpensestoPL.incomeAndExpenses();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
        Allure.step("After Test - Transfer Incomes and Expenses to PL");
    }
}