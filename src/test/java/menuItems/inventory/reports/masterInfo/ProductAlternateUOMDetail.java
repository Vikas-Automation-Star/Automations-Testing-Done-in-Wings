package menuItems.inventory.reports.masterInfo;

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

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void productUOMDetails() throws  InterruptedException, AWTException {
        ProductAlternateUOMDetails uomDetails=new ProductAlternateUOMDetails(driver);
        uomDetails.masterDetails();
    }

    @AfterTest
    public void afterTest(){
        appLogin.logout();
    }
}
