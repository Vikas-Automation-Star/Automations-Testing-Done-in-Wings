package menuItems.Production.masters;

import com.wings.pages.production.masters.ProductionBatchPolicie;
import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class ProductionBatchPoliciesMaster {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String file="./src/main/resources/MenuItems/production/masters/ProductionBatchPolicies.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void ProductionBatchPolicies() throws IOException, ParseException, InterruptedException, AWTException {
        ProductionBatchPolicie pbp=new ProductionBatchPolicie(driver,file);
        pbp.productionBatchPolicie();

    }

    @AfterTest
    public void afterTest() throws IOException{
        appLogin.logout();
    }
}
