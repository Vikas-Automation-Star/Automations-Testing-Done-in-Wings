package menuItems.purchase.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.purchase.transactions.MaterialReceiptsAgainstOrder;
import com.wings.pages.purchase.transactions.PurchaseOrders;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class TestMaterialReceiptsAgainstOrders {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file = "./src/main/resources/menuItems/purchase/transactions/purchaseOrders.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.login();
    }

    @Test
    public void materialReceiptsAgainstOrders() throws IOException, ParseException, InterruptedException, AWTException {
        PurchaseOrders purchaseOrders=new PurchaseOrders(driver,file);
        String po=purchaseOrders.purchaseOrders();
        appLogin.logout();
        driver=appLogin.login();
        MaterialReceiptsAgainstOrder mrao = new MaterialReceiptsAgainstOrder(driver, file);
        mrao.materialReceiptsAgainstOrder(po);
    }

    @AfterTest
    public void afterTest() throws IOException {
//        appLogin.logout();
    }
}
