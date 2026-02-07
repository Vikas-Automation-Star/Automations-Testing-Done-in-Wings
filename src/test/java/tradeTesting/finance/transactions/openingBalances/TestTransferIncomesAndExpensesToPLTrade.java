package tradeTesting.finance.transactions.openingBalances;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class TestTransferIncomesAndExpensesToPLTrade {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String dataFile="./src/main/resources/tradeAutomation/finance/transactions/439851 - Transfer Incomes and Expenses to PL-AC_TIE_1.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void testTransferIncomeTrade() throws IOException, ParseException, InterruptedException, AWTException {
        TransferIncomesAndExpensesToPLTrade expensesToPLTrade=new TransferIncomesAndExpensesToPLTrade(driver,dataFile);
        expensesToPLTrade.transferIncomesAndExpensesToPLTrade();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}