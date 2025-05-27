package menuItems.inventory.reports.masterInfo;

import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.inventory.reports.masterInfo.ProductWithGSTDetails;

import java.awt.*;
import java.io.IOException;

public class TestProductWithGSTDetail {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    Common common;
    String file = "./src/main/resources/menuItems/inventory/reports/productWithGstDetails.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        common=new Common(driver);
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin(common.getData(file,"productWithGST","userName"),common.getData(file,"productWithGST","password"));
    }

    @Test
    public void productGSTDetails() throws InterruptedException, AWTException, IOException, ParseException {
        ProductWithGSTDetails gstDetails = new ProductWithGSTDetails(driver,file);
        gstDetails.productWithGST();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
