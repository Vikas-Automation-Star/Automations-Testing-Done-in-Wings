package menuItems.purchase.reports;

import com.wings.pages.purchase.reports.PurchaseOrderAgainstQuotations;
import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class PurchaseOrdersAgainstQuotationsReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void purchaseOrdersAgainstQuotations() throws InterruptedException, AWTException {
        PurchaseOrderAgainstQuotations poaq=new PurchaseOrderAgainstQuotations(driver);
        poaq.purchaseOrderAgainstQuotation();
    }

    @AfterTest
    public void afterTest(){
        appLogin.logout();
    }
}
