package menuItems.Taxes.reports.GST;

import com.wings.pages.AppLogin;
import com.wings.pages.taxes.reports.GST.Gstr1B2B;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

;

public class Gstr1B2BReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void Gstr1B2BReport() throws InterruptedException {
        Gstr1B2B report = new Gstr1B2B(driver);
        report.Gstr1B2B();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }

}
