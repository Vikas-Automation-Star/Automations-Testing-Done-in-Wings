package menuItems.Taxes.reports.GST;

import com.wings.pages.AppLogin;
import com.wings.pages.taxes.reports.GST.Gstr1MonthEnd;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

;

public class Gstr1MonthEndTransactionReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void gstr1MonthEndTransactionReport() throws Exception {
        Gstr1MonthEnd report = new Gstr1MonthEnd(driver);
        report.gstr1MonthEnd();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
