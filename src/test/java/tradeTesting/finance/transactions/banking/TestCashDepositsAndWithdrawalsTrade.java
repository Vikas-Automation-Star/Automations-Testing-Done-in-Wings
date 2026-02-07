package tradeTesting.finance.transactions.banking;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;

public class TestCashDepositsAndWithdrawalsTrade {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String dataFile="./src/main/resources/tradeAutomation/finance/transactions/458329 - Cash Deposits and withdrawals-AC_CE_1.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void testCashDepositsTrade() throws Exception {
        CashDepositsAndWithdrawalsTrade withdrawalsTrade=new CashDepositsAndWithdrawalsTrade(driver,dataFile);
        withdrawalsTrade.cashDepositsAndWithdrawalsTrade("","","");
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}