package menuItems.company.masters;

import com.wings.pages.company.masters.ProfitCentres;
import com.wings.pages.company.masters.Reasons;
import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class TestReasonsMaster {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    ProfitCentres profitCentres;
    String file = "./src/main/resources/MenuItems/Company/Masters/ReasonsData.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void createReasons() throws Exception, AWTException {
        Reasons reasons = new Reasons(driver, file);
        reasons.createTransporters();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
