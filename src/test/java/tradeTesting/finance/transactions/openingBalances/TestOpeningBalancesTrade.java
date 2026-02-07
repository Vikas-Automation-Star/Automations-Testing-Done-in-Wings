package tradeTesting.finance.transactions.openingBalances;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;

public class TestOpeningBalancesTrade {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String dataFile="./src/main/resources/tradeAutomation/finance/transactions/446452 - Opening Balances-AC_OB_1.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void testOpeningBalancesTrade() throws Exception {
        OpeningBalancesTrade openingBalancesTrade=new OpeningBalancesTrade(driver,dataFile);
        openingBalancesTrade.openingBalancesTrade("","","");
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}