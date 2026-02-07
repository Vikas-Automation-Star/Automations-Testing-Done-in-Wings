package tradeTesting.finance.transactions.banking;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;

public class TestDepositPostDatedChequesTrade {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String dataFile="./src/main/resources/tradeAutomation/finance/transactions/458330 - Deposit Post Dated Cheques-AC_DPDC_1_SIAO_1.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void testDepositPostDatedChequesTrade() throws Exception {
        DepositPostDatedChequesTrade postDatedChequesTrade=new DepositPostDatedChequesTrade(driver,dataFile);
        postDatedChequesTrade.depositPostDatedChequesTrade("SIAO 2","","","");
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}