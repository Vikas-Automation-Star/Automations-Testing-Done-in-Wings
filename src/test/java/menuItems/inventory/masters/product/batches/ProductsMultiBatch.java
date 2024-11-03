package menuItems.inventory.masters.product.batches;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.inventory.masters.product.batches.ProductMultiBatch;

import java.awt.*;
import java.io.IOException;

public class ProductsMultiBatch {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file="./src/main/resources/menuItems/inventory/masters/multiBatch.json";

    @BeforeTest
    public void beforeTest() throws  InterruptedException, IOException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void productMultiBatch() throws IOException, ParseException, InterruptedException, AWTException {
        ProductMultiBatch multiBatch=new ProductMultiBatch(driver,file);
        multiBatch.productMultiBatch();
    }

    @AfterTest
    public void afterTest() {
        appLogin.logout();
    }
}
