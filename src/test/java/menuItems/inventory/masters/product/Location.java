package menuItems.inventory.masters.product;

import com.wings.pages.AppLogin;
import com.wings.pages.inventory.masters.product.Locations;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class Location {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file = "./src/main/resources/menuItems/inventory/masters/locations.json";

    @BeforeTest
    public void beforeTest() throws InterruptedException, IOException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void locationMaster() throws IOException, ParseException, InterruptedException, AWTException {
        Locations locations = new Locations(driver, file);
        locations.locationMastercreation();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
