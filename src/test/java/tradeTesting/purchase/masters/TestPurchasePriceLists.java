package tradeTesting.purchase.masters;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestPurchasePriceLists {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file = "./src/main/resources/tradeAutomation/purchase/masters/tradePurchasePriceLists.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void purchasePriceLists() throws Exception {
        CreatePurchasePriceLists priceLists =new CreatePurchasePriceLists(driver,file);
        priceLists.purchasePriceLists();

    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
