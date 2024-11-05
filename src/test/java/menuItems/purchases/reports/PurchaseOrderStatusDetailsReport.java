package menuItems.purchases.reports;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.purchase.reports.PurchaseOrderStatusDetails;
import java.io.IOException;

public class PurchaseOrderStatusDetailsReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void pendingPurchaseOrderReport() throws IOException, ParseException, InterruptedException {
        PurchaseOrderStatusDetails purchaseOrder=new PurchaseOrderStatusDetails(driver);
        purchaseOrder.purchaseOrderStatusReport();

    }

    @AfterTest
    public void afterTest(){
        appLogin.logout();
    }
}
