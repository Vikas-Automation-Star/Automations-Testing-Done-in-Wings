package tradeTesting.finance.transactions.openingBalances;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;

public class TestPartyOpeningBalances_Trade {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String dataFile="./src/main/resources/tradeAutomation/finance/transactions/471552 - Party Opening Balances-AC_POB_1.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void testPartyOpeningBalancesTrade() throws Exception {
        PartyOpeningBalances_Trade partyOpeningBalancesTrade=new PartyOpeningBalances_Trade(driver,dataFile);
        partyOpeningBalancesTrade.partyOpeningBalancesTrade("","","");
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
