package menuItems.purchases.reports;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.purchase.reports.PurchaseOrderReport;

import java.io.IOException;

public class PurchaseOrderReprt {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void purchaseOrderReport() throws IOException, ParseException, InterruptedException {
        PurchaseOrderReport orderReport=new PurchaseOrderReport(driver);
        orderReport.orderReport();
    }

    @AfterTest
    public void afterTest(){
        appLogin.logout();
    }
}
