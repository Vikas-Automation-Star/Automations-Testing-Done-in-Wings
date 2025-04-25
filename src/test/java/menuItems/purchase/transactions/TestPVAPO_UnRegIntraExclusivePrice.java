package menuItems.purchase.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.purchase.transactions.PVAPO_UnRegIntraExclusive;
import com.wings.pages.purchase.transactions.PurchaseOrder_UnRegIntraExclusive;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;


import java.io.IOException;

public class TestPVAPO_UnRegIntraExclusivePrice {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file = "./src/main/resources/menuItems/purchase/transactions/PO_MReceiptsAPO_PVAPO_UnRegIntraExclusive.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void UnReg_PO_ExclusivePrice() throws IOException, ParseException, InterruptedException, NoSuchMethodException, AWTException {
        PurchaseOrder_UnRegIntraExclusive orders=new PurchaseOrder_UnRegIntraExclusive(driver,file);
        PVAPO_UnRegIntraExclusive UnRegExc=new PVAPO_UnRegIntraExclusive(driver,file);
        UnRegExc.pvapo_UnRegIntraExclusive(orders.unReg_PO_GSTExclusive()[1]);
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
