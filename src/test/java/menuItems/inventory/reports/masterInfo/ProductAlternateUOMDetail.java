package menuItems.inventory.reports.masterInfo;

import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.inventory.reports.masterInfo.ProductAlternateUOMDetails;

import java.awt.*;
import java.io.IOException;

public class ProductAlternateUOMDetail {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    Common common;
    String file = "./src/main/resources/menuItems/inventory/reports/productUOMDetails.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        common=new Common(driver);
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin(common.getData(file,"productUOMDetails","userName"),common.getData(file,"productUOMDetails","password"));
    }

    @Test
    public void productUOMDetails() throws InterruptedException, AWTException, IOException, ParseException {
        ProductAlternateUOMDetails uomDetails = new ProductAlternateUOMDetails(driver,file);
        uomDetails.productAlternateUOMDetails();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
