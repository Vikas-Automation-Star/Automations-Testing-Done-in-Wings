package menuItems.Production.reports;

import com.wings.pages.production.reports.PendingMaterialReceiptsFromProduction;
import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class PendingMaterialReceiptsFromProductionReport {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.launchSingleUserApp();
        Thread.sleep(1000);
        appLogin.singleUserLogin();
    }

    @Test
    public void pendingMaterialReceiptsFromProductionReport() throws  InterruptedException {
        PendingMaterialReceiptsFromProduction pmrfp=new PendingMaterialReceiptsFromProduction(driver);
        pmrfp.pendingMaterialReceiptsFromProduction();
    }

    @AfterTest
    public void afterTest(){
        appLogin.logout();
    }
}
