package menuItems.purchase.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.purchase.transactions.Reg_PurchaseOrder_GSTInclusive;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class Reg_PurchaseOrder_GSTInclusivePrice {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file = "./src/main/resources/menuItems/purchase/transactions/Reg_PurchaseOrder_GSTInclusive.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void UnReg_purchaseOrdersGST_ExclusivePrice() throws IOException, ParseException, InterruptedException, NoSuchMethodException {
        Reg_PurchaseOrder_GSTInclusive orderGstInclusive=new Reg_PurchaseOrder_GSTInclusive(driver,file);
        orderGstInclusive.unReg_PO_GSTInclusive();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
