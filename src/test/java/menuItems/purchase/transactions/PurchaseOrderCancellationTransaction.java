package menuItems.purchase.transactions;

import com.wings.pages.purchase.transactions.PurchaseOrderCancellation;
import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class PurchaseOrderCancellationTransaction {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file = "./src/main/resources/MenuItems/purchase/transactions/PurchaseOrderCancellations.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
        Allure.step("Before Test PurchaseOrderCancellation Transaction");
    }

    @Test
    public void purchaseOrderCancellations() throws IOException, ParseException, InterruptedException {
        PurchaseOrderCancellation poc=new PurchaseOrderCancellation(driver,file);
        poc.purchaseOrderCancellation();

    }

    @AfterTest
    public void afterTest(){
        appLogin.logout();
        Allure.step("After Test PurchaseOrderCancellation Transaction");

    }
}
