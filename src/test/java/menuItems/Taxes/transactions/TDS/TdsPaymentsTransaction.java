package menuItems.Taxes.transactions.TDS;

import com.wings.pages.AppLogin;
import com.wings.pages.taxes.transactions.TDS.TdsPayments;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class TdsPaymentsTransaction {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file = "./src/main/resources/MenuItems/Taxes/transactions/TDS/TdsPayments.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void tdsPaymentsTransaction() throws InterruptedException, IOException, ParseException, AWTException {
        TdsPayments tp = new TdsPayments(driver, file);
        tp.tdsPayments();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
