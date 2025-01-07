package menuItems.finance.transactions.Banking;

import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.finance.transactions.Banking.CashDepositsAndWithdrawls;
import java.awt.*;
import java.io.IOException;

public class CashDepositsAndWithdrawlsTransaction {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String dataFile="./src/main/resources/menuItems/finance/transaction/cashDepositAndWithdrawl.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
        Allure.step("Before Test - Cash Deposit and Withdrawals");
    }

    @Test
    public void cashDepositandWithdrawl() throws InterruptedException, AWTException, IOException, ParseException {
        CashDepositsAndWithdrawls depositsAndWithdrawls=new CashDepositsAndWithdrawls(driver,dataFile);
        depositsAndWithdrawls.depositAndWithdrawal();
    }

    @AfterTest
    public void afterTest() throws IOException{
        appLogin.logout();
        Allure.step("After Test - Cash Deposit and Withdrawals");
    }
}