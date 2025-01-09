package menuItems.inventory.reports.masterInfo;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.inventory.reports.masterInfo.MasterDetails;

import java.awt.*;
import java.io.IOException;

public class MasterDetail {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void masterDetail() throws InterruptedException, AWTException {
        MasterDetails details = new MasterDetails(driver);
        details.masterDetails();

    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
