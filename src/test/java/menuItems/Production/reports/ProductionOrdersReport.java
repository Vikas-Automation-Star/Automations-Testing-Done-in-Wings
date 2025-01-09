package menuItems.Production.reports;

import com.wings.pages.production.reports.ProductionOrders;
import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class ProductionOrdersReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void ProductionOrdersReport() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        Thread.sleep(1500);
        appLogin.singleUserLogin();
    }

    @Test
    public void productionOrdersReport() throws InterruptedException {
        ProductionOrders po = new ProductionOrders(driver);
        po.productionOrders();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
