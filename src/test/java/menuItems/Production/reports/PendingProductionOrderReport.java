package menuItems.Production.reports;

import com.wings.pages.production.reports.PendingProductionOrder;
import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class PendingProductionOrderReport {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();

    @BeforeTest
    public void PendingProductionOrderReport() throws IOException, InterruptedException, ParseException {
        driver=appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void pendingProductionOrderReport() throws  InterruptedException {
        PendingProductionOrder ppo=new PendingProductionOrder(driver);
        ppo.pendingProductionOrder();
    }

    @AfterTest
    public void afterTest() throws IOException{
        appLogin.logout();
    }
}
