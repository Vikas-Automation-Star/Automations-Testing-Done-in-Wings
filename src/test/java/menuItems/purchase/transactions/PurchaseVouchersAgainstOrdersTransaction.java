package menuItems.purchase.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.purchase.transactions.PurchaseVouchersAgainstOrder;
import io.appium.java_client.windows.WindowsDriver;

import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class PurchaseVouchersAgainstOrdersTransaction {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file = "./src/main/resources/MenuItems/purchase/transactions/PurchaseVouchersAgainstOrders.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void purchaseVouchersAgainstOrders() throws IOException, ParseException, InterruptedException {
        PurchaseVouchersAgainstOrder poao = new PurchaseVouchersAgainstOrder(driver, file);
        poao.purchaseVouchersAgainstOrder();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
