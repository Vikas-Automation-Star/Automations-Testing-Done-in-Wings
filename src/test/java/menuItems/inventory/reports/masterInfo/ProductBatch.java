package menuItems.inventory.reports.masterInfo;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.inventory.reports.masterInfo.ProductBatches;

import java.awt.*;
import java.io.IOException;

public class ProductBatch {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void productBatchMasterinfo() throws InterruptedException, AWTException {
        ProductBatches batches = new ProductBatches(driver);
        batches.productBatch();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
