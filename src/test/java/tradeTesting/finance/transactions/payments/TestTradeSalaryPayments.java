package tradeTesting.finance.transactions.payments;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;

public class TestTradeSalaryPayments {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String dataFile="./src/main/resources/tradeAutomation/finance/transactions/481638 - Trade Salary Payments-Trd_SP_2.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void tradeSalaryPayments() throws Exception {
        TradeSalaryPayments tradeSalaryPayments=new TradeSalaryPayments(driver,dataFile);
        tradeSalaryPayments.tradeSalaryPayments("","","");
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}