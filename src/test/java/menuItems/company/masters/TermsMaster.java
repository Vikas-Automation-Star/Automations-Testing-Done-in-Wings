package menuItems.company.masters;

import com.wings.pages.company.masters.Terms;
import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class TermsMaster {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file = "./src/main/resources/MenuItems/Company/Masters/TermsData.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void terms() throws Exception, AWTException {
        Terms terms = new Terms(driver, file);
        terms.terms();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
