package menuItems.Production.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.production.transactions.CloseProductionOrder;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class CloseProductionOrderTransaction {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file = "./src/main/resources/MenuItems/production/transactions/CloseProductionOrderTransaction.json";

    @BeforeTest
    public void CloseProductionOrderTransaction() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void closeProductionOrderTransaction() throws Exception {
        CloseProductionOrder cpo = new CloseProductionOrder(driver, file);
        cpo.closeProductionOrder();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
