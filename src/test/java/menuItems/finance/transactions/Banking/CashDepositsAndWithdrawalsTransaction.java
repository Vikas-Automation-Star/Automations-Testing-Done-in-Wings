package menuItems.finance.transactions.Banking;

import io.appium.java_client.windows.WindowsDriver;

import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.finance.transactions.Banking.CashDepositsAndWithdrawls;

import java.awt.*;
import java.io.IOException;

public class CashDepositsAndWithdrawalsTransaction {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String dataFile = "./src/main/resources/menuItems/finance/transaction/cashDepositAndWithdrawl.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();

    }

    @Test
    public void cashDepositandWithdrawl() throws InterruptedException, AWTException, IOException, ParseException {
        CashDepositsAndWithdrawls depositsAndWithdrawls = new CashDepositsAndWithdrawls(driver, dataFile);
        depositsAndWithdrawls.depositAndWithdrawal();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();

    }
}