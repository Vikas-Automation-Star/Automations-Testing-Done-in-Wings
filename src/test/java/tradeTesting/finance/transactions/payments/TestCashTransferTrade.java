package tradeTesting.finance.transactions.payments;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;

public class TestCashTransferTrade {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String dataFile="./src/main/resources/tradeAutomation/finance/transactions/454215 - Cash Transfers-AC_CT_1.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void testCashTransferTrade() throws Exception {
        CashTransferTrade cashTransferTrade=new CashTransferTrade(driver,dataFile);
        cashTransferTrade.cashTransferTrade("","","");
    }

    @AfterTest
    public void afterTest() throws IOException {
//        appLogin.logout();
    }
}
