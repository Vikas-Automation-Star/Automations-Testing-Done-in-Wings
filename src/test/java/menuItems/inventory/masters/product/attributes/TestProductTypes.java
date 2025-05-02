package menuItems.inventory.masters.product.attributes;

import com.wings.pages.AppLogin;
import com.wings.pages.inventory.masters.product.attributes.ProductTypes;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class TestProductTypes {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file = "./src/main/resources/menuItems/inventory/masters/productTypes.json";

    @BeforeTest
    public void beforeTest() throws InterruptedException, IOException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void productTypes() throws IOException, ParseException, InterruptedException, AWTException {
        ProductTypes productTypes=new ProductTypes(driver,file);
        productTypes.productTypes();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
