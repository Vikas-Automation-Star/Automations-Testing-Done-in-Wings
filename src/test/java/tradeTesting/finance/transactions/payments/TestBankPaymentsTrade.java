package tradeTesting.finance.transactions.payments;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;

public class TestBankPaymentsTrade {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String dataFile="./src/main/resources/tradeAutomation/finance/transactions/458284 - Bank Payments-AC_BP_1.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void testBankPaymentsTrade() throws Exception {
        BankPaymentsTrade bankPaymentsTrade=new BankPaymentsTrade(driver,dataFile);
        bankPaymentsTrade.bankPaymentsTrade("","","","");
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
