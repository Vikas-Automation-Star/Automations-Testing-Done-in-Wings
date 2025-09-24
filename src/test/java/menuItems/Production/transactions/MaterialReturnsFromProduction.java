package menuItems.Production.transactions;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class MaterialReturnsFromProduction {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file = "src/main/resources/MenuItems/production/transactions/MaterialReturnsFromProduction.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void materialReturnsFromProductionTransaction() throws Exception {
        com.wings.pages.production.transactions.MaterialReturnsFromProduction mrfp = new com.wings.pages.production.transactions.MaterialReturnsFromProduction(driver, file);
        mrfp.materialReturnsFromProduction();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
