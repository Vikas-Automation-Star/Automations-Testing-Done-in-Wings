package menuItems.company.masters;

import com.wings.pages.AppLogin;
import com.wings.pages.company.masters.CostCentres;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class CostCentresMaster {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    CostCentres costCentres;
    String file = "./src/main/resources/MenuItems/Company/Masters/CostCentresData.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void costCentres() throws Exception, AWTException {
        costCentres = new CostCentres(driver, file);
        costCentres.costCentres();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
