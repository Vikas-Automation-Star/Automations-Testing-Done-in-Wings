package menuItems.finance.reports.Balances;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.finance.reports.Balances.SupplierBalances;

import java.awt.*;
import java.io.IOException;

public class SupplierBalancesReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void supplierBalance() throws  InterruptedException, AWTException {
        SupplierBalances supplierBalances=new SupplierBalances(driver);
        supplierBalances.supplierBalanceReport();

    }

    @AfterTest
    public void afterTest(){
        appLogin.logout();
    }
}
