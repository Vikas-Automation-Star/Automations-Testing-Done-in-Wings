package menuItems.inventory.masters.product;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.inventory.masters.product.UnitsOfMeasure;

import java.awt.*;
import java.io.IOException;

public class UnitOfMeasure {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file="./src/main/resources/menuItems/inventory/masters/unitsOfMeasure.json";

    @BeforeTest
    public void beforeTest() throws  InterruptedException, IOException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void unitsOfMeasure() throws IOException, ParseException, InterruptedException, AWTException {
        UnitsOfMeasure units=new UnitsOfMeasure(driver,file);
        units.unitsOfMeasure();
    }

    @AfterTest
    public void afterTest() {
        appLogin.logout();
    }
}
