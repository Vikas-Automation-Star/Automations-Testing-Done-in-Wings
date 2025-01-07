package menuItems.finance.reports.Balances;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.finance.reports.Balances.CustomerBalances;
import java.awt.*;
import java.io.IOException;

public class CustomerBalancesReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void customerBalance() throws  InterruptedException, AWTException {
        CustomerBalances customerBalances=new CustomerBalances(driver);
        customerBalances.customerBalanceReport();
    }

    @AfterTest
    public void afterTest() throws IOException{
        appLogin.logout();
    }

}
