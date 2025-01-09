package menuItems.Taxes.transactions.TCS;

import com.wings.pages.taxes.transactions.TCS.TcsOpeningBalances;
import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class TcsOpeningBalancesTransaction {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file = "./src/main/resources/MenuItems/Taxes/transactions/TCS/TcsOpeningBalances.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void tcsOpeningBalancesTransactions() throws InterruptedException, IOException, ParseException, AWTException {
        TcsOpeningBalances tob = new TcsOpeningBalances(driver, file);
        tob.tcsOpeningBalances();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
