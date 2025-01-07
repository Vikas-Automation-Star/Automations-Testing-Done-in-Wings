package menuItems.purchase.masters;;

import com.wings.pages.purchase.masters.PurchaseBatchPolicies;
import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class PurchaseBatchPoliciesMaster {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String file="./src/main/resources/MenuItems/purchase/masters/PurchaseBatchPolicies.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
        Allure.step("Before Test Purchase Batch policies");
    }

    @Test
    public void purchaseBatchPolicies() throws IOException, ParseException, InterruptedException, AWTException {
        PurchaseBatchPolicies pbp=new PurchaseBatchPolicies(driver,file);
        pbp.batchPolicies();

    }

    @AfterTest
    public void afterTest() throws IOException{
        appLogin.logout();
        Allure.step("After Test Purchase Batch policies");
    }
}
