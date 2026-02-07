package tradeTesting.finance.transactions.banking;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;

public class TestBankReconciliationTrade {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String dataFile="./src/main/resources/tradeAutomation/finance/transactions/439444 - Bank Reconciliation-AC_BNKRECO_1_SO_2.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void testBankReconciliationTrade() throws Exception {
        BankReconciliationTrade bankReconciliationTrade=new BankReconciliationTrade(driver,dataFile);
        bankReconciliationTrade.bankReconciliationTrade("","","");
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}