package menuItems.purchase.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.purchase.transactions.PurchaseOrderCancellation;
import com.wings.pages.purchase.transactions.PurchaseOrders;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class TestPurchaseOrderCancellation {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file = "./src/main/resources/menuItems/purchase/transactions/476937 - Purchase Orders-AC_PO_6.xls";
    String file1 = "./src/main/resources/menuItems/purchase/transactions/458834 - Purchase Orders Cancellation-AC_POC_2.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.login();
    }

    @Test
    public void purchaseOrderCancellations() throws Exception, AWTException {
        PurchaseOrders purchaseOrders=new PurchaseOrders(driver,file);
        String purchaseOrderVoucher= purchaseOrders.purchaseOrders();

        appLogin.logout();
        driver=appLogin.login();

        PurchaseOrderCancellation poc = new PurchaseOrderCancellation(driver, file1);
        poc.purchaseOrderCancellation(purchaseOrderVoucher);
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}