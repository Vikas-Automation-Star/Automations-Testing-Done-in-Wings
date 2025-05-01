package menuItems.purchase.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.purchase.transactions.MaterialReturn;
import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class MaterialReturnsTransaction {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file = "./src/main/resources/MenuItems/purchase/transactions/MaterialReturns.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void materialReturns() throws IOException, ParseException, InterruptedException {
        MaterialReturn mr = new MaterialReturn(driver, file);
        mr.materialReturn();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
