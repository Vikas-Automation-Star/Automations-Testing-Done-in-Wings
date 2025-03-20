package menuItems.Taxes.masters.TDS;


import com.wings.pages.AppLogin;
import com.wings.pages.taxes.masters.TDS.TdsAssesseeTypes;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class TdsAssesseeTypesMaster {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file = "./src/main/resources/menuItems/Taxes/masters/TDS/TdsAssesseeTypes.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void TdsAssesseeTypesMaster() throws InterruptedException, IOException, ParseException, AWTException {
        TdsAssesseeTypes tat = new TdsAssesseeTypes(driver, file);
        tat.tdsAssesseeTypes();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
