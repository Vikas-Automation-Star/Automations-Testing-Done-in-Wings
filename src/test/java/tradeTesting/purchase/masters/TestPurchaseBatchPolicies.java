package tradeTesting.purchase.masters;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestPurchaseBatchPolicies {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file = "./src/main/resources/tradeAutomation/purchase/masters/tradePurchaseBatchPolicies.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void PurchaseBatchPolicies() throws Exception {
        CreatePurchaseBatchPolicies batchPolicies=new CreatePurchaseBatchPolicies(driver,file);
        batchPolicies.purchaseBatchPolicies();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }

}
