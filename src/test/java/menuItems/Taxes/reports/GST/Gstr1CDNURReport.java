package menuItems.Taxes.reports.GST;

import com.wings.pages.AppLogin;
import com.wings.pages.taxes.reports.GST.Gstr1CDNUR;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

;

public class Gstr1CDNURReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void gstr1CDNURReport() throws InterruptedException {
        Gstr1CDNUR repot = new Gstr1CDNUR(driver);
        repot.gstr1CDNUR();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
