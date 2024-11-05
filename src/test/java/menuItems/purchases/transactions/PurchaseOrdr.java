package menuItems.purchases.transactions;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.purchase.transactions.PurchaseOrder;

import java.io.IOException;

public class PurchaseOrdr {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file="./src/main/resources/menuItems/purchases/transactions/purchaseOrder.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void newPurchaseOrder() throws IOException, ParseException, InterruptedException {
        PurchaseOrder order=new PurchaseOrder(driver,file);
        order.purchaseOrder();
    }

    @AfterTest
    public void afterTest(){
        appLogin.logout();
    }
}
