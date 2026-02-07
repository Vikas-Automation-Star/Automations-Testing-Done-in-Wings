package tradeTesting.finance.transactions.payments;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;

public class TestCashPaymentsTrade {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String dataFile="./src/main/resources/tradeAutomation/finance/transactions/459470 - Cash Payments-AC_CP_2.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void cashPaymentsTrade() throws Exception {
        CashPaymentsTrade cashPaymentsTrade=new CashPaymentsTrade(driver,dataFile);
        cashPaymentsTrade.cashPaymentsTrade("","","");
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
