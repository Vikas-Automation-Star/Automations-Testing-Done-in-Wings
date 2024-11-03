package menuItems.finance.reports.Balances;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.finance.reports.Balances.AccountBalances;
import java.awt.*;
import java.io.IOException;

public class AccountBalancesReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void accountBalance() throws  InterruptedException, AWTException {
        AccountBalances accountBalances=new AccountBalances(driver);
        accountBalances.accountBalanceReport();
    }

    @AfterTest
    public void afterTest(){
        appLogin.logout();
    }
}
