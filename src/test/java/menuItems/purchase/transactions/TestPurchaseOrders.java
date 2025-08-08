package menuItems.purchase.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.purchase.transactions.*;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class TestPurchaseOrders {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file = "./src/main/resources/menuItems/purchase/transactions/476937 - Purchase Orders-AC_PO_6.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.login();
    }

    @Test
    public void purchaseOrders() throws IOException, ParseException, InterruptedException, AWTException {
        PurchaseOrders purchaseOrders=new PurchaseOrders(driver,file);
        purchaseOrders.purchaseOrders();
    }

    @AfterTest
    public void afterTest() throws IOException {
//        appLogin.logout();
    }
}
