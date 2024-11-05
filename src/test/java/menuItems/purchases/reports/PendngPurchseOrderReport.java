package menuItems.purchases.reports;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.purchase.reports.PendingPurchaseOrder;
import java.io.IOException;

public class PendngPurchseOrderReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void pendingPurchaseOrderReport() throws InterruptedException {
        PendingPurchaseOrder purchaseOrder=new PendingPurchaseOrder(driver);
        purchaseOrder.pendingOrderReport();

    }

    @AfterTest
    public void afterTest(){
        appLogin.logout();
    }
}
