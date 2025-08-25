package menuItems.Production.reports;

import com.wings.pages.production.reports.PendingMaterialIssuesToProduction;
import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class PendingMaterialIssuesToProductionReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void pendingMaterialIssuesToProductionReport() throws Exception {
        PendingMaterialIssuesToProduction pmitp = new PendingMaterialIssuesToProduction(driver);
        pmitp.pendingMaterialIssuesToProduction();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
