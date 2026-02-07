package tradeTesting.finance.transactions.banking;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;

public class TestIssuedChequesBounceTrade {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String dataFile="./src/main/resources/tradeAutomation/finance/transactions/459050 - Issued Cheques Bounce-AC_CBI_1.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void testIssuedChequesBounce() throws Exception {
        IssuedChequesBounceTrade issuedChequesBounceTrade=new IssuedChequesBounceTrade(driver,dataFile);
        issuedChequesBounceTrade.issuedChequesBounceTrade("","","","");

    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}