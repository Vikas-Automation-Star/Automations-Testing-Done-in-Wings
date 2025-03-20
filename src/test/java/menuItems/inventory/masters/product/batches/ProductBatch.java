package menuItems.inventory.masters.product.batches;

import com.wings.pages.AppLogin;
import com.wings.pages.inventory.masters.product.batches.ProductBatches;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class ProductBatch {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file = "./src/main/resources/menuItems/inventory/masters/productBatch.json";

    @BeforeTest
    public void beforeTest() throws InterruptedException, IOException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void productBatch() throws IOException, ParseException, InterruptedException, AWTException {
        ProductBatches productBatches = new ProductBatches(driver, file);
        productBatches.productBatch();

    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
