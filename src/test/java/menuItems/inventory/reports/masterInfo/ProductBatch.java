package menuItems.inventory.reports.masterInfo;

import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.inventory.reports.masterInfo.ProductBatches;

import java.io.IOException;

public class ProductBatch {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    Common common;
    String file = "./src/main/resources/menuItems/inventory/reports/productBatches.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        common=new Common(driver);
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin(common.getData(file,"productBatches","userName"),common.getData(file,"productBatches","password"));
    }

    @Test
    public void ProductBatches() throws IOException, ParseException, InterruptedException {
        ProductBatches batches = new ProductBatches(driver,file);
        batches.productBatches();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
