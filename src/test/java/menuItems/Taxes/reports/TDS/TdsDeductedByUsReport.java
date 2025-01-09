package menuItems.Taxes.reports.TDS;

import com.wings.pages.taxes.reports.TDS.TdsDeductedByUs;
import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TdsDeductedByUsReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void tdsDeductedByUsReport() throws InterruptedException, IOException, ParseException {
        TdsDeductedByUs tdbu = new TdsDeductedByUs(driver);
        tdbu.tdsDeductedByUs();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
