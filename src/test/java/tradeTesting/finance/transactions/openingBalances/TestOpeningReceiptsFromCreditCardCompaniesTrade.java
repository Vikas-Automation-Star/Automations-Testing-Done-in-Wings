package tradeTesting.finance.transactions.openingBalances;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;

public class TestOpeningReceiptsFromCreditCardCompaniesTrade {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String dataFile="./src/main/resources/tradeAutomation/finance/transactions/460910 - Opening Receipts from Credit Card Companies-AC_ORFCC_1.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void testOpeningReceiptsTrade() throws Exception {
        OpeningReceiptsFromCreditCardCompaniesTrade creditCardCompaniesTrade=new OpeningReceiptsFromCreditCardCompaniesTrade(driver,dataFile);
        creditCardCompaniesTrade.openingReceiptsFromCreditCardCompaniesTrade("","","");
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}