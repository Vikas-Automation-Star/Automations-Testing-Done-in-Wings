package menuItems.Taxes.reports.TDS;

import com.wings.pages.taxes.reports.TDS.TdsDeductedByOthers;
import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TdsDeductedByOthersReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void tdsDeductedByOthersReport() throws InterruptedException, IOException, ParseException {
        TdsDeductedByOthers tdbo = new TdsDeductedByOthers(driver);
        tdbo.tdsDeductedByOthers();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
