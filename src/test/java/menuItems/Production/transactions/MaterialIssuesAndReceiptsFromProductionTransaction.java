package menuItems.Production.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.production.transactions.MaterialIssuesAndReceiptsFromProduction;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class MaterialIssuesAndReceiptsFromProductionTransaction {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file = "./src/main/resources/MenuItems/production/transactions/MaterialIssuesAndReceiptsFromProductionTransaction.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void MaterialIssuesAndReceiptsFromProductionTransaction() throws Exception {
        MaterialIssuesAndReceiptsFromProduction mirp = new MaterialIssuesAndReceiptsFromProduction(driver, file);
        mirp.materialIssuesAndReceiptsFromProduction();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
