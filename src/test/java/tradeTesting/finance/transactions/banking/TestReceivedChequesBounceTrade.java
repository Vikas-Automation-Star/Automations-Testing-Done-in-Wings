package tradeTesting.finance.transactions.banking;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;

public class TestReceivedChequesBounceTrade {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String dataFile="./src/main/resources/tradeAutomation/finance/transactions/460820 - Received Cheques Bounce-AC_CBR_2_SRWR_2.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void testReceivedChequesBounce() throws Exception {
        ReceivedChequesBounceTrade chequesBounceTrade=new ReceivedChequesBounceTrade(driver,dataFile);
        chequesBounceTrade.receivedCheckBounce("SRWR 4","","","");
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}