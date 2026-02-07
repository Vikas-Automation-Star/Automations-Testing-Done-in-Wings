package tradeTesting.finance.transactions.openingBalances;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;

public class TestPartyOpeningBalancesTrade {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String dataFile="./src/main/resources/tradeAutomation/finance/transactions/489944 - Party Opening Balances Trade-Trd_POBT_4.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void testPartyOpeningBalancesTrade() throws Exception {
        PartyOpeningBalancesTrade partyOpeningBalancesTrade=new PartyOpeningBalancesTrade(driver,dataFile);
        partyOpeningBalancesTrade.partyOpeningBalancesTrade("","","");
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}