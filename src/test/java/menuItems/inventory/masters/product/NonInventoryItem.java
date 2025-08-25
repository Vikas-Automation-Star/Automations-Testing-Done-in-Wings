package menuItems.inventory.masters.product;

import com.wings.pages.AppLogin;
import com.wings.pages.inventory.masters.product.NonInventoryItems;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class NonInventoryItem {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file = "./src/main/resources/menuItems/inventory/masters/nonInventoryItems.json";

    @BeforeTest
    public void beforeTest() throws InterruptedException, IOException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void nonInventoryItems() throws Exception, AWTException {
        NonInventoryItems inventoryItems = new NonInventoryItems(driver, file);
        inventoryItems.nonInventoryItemcreation();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
