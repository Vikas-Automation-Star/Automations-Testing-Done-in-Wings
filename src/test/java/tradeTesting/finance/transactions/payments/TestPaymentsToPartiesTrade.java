package tradeTesting.finance.transactions.payments;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;

public class TestPaymentsToPartiesTrade {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String dataFile="./src/main/resources/tradeAutomation/finance/transactions/461060 - Payments to Parties-AC_PPAY_1.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void testPaymentsToPartiesTrade() throws Exception {
        PaymentToPartyTrade paymentToPartyTrade=new PaymentToPartyTrade(driver,dataFile);
        paymentToPartyTrade.paymentToPartyTrade("","","");

    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}