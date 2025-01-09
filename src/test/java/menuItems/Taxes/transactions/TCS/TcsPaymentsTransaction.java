package menuItems.Taxes.transactions.TCS;

import com.wings.pages.AppLogin;
import com.wings.pages.taxes.transactions.TCS.TcsPayments;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class TcsPaymentsTransaction {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file = "./src/main/resources/MenuItems/Taxes/transactions/TCS/TcsPayments.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void tcsPaymentsTransaction() throws InterruptedException, IOException, ParseException, AWTException {
        TcsPayments tp = new TcsPayments(driver, file);
        tp.tcsPayments();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
