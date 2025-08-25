package menuItems.Production.reports;

import com.wings.pages.AppLogin;
import com.wings.pages.production.reports.MaterialIssuesToProduction;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class MaterialIssuesToProductionReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void MaterialIssuesToProductionReport() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void materialIssuesToProductionReport() throws Exception {
        MaterialIssuesToProduction mitp = new MaterialIssuesToProduction(driver);
        mitp.MaterialIssuesToProduction();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
