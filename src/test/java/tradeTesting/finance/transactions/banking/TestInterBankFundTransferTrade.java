package tradeTesting.finance.transactions.banking;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;

public class TestInterBankFundTransferTrade {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String dataFile="./src/main/resources/tradeAutomation/finance/transactions/482403 - Inter Bank Fund Transfers-AC_BFT_1.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void testInterBankFundtransfer() throws Exception {
        InterBankFundTransferTrade bankFundTransferTrade=new InterBankFundTransferTrade(driver,dataFile);
        bankFundTransferTrade.interBankFundTransferTrade("","","");
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}