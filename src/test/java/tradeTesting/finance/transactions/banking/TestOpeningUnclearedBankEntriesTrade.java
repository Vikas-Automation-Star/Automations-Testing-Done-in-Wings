package tradeTesting.finance.transactions.banking;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;

public class TestOpeningUnclearedBankEntriesTrade {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String dataFile="./src/main/resources/tradeAutomation/finance/transactions/458165 - Opening Uncleared Bank Entries-AC_OUCBE_1.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void testUnclearedEntriesTrade() throws Exception {
        OpeningUnclearedBankEntriesTrade bankEntriesTrade=new OpeningUnclearedBankEntriesTrade(driver,dataFile);
        bankEntriesTrade.openingUnclearedBankEntriesTrade("","","");
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}