package menuItems.purchase.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.purchase.transactions.PVOPO_Reg_IntraExclusive;
import com.wings.pages.purchase.transactions.PurchaseOrder_RegIntraExclusive;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class TestPVOPO_Reg_IntraExclusivePrice {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file = "./src/main/resources/MenuItems/purchase/transactions/PO_MReceiptsAPO_PVAPO_RegIntraExclusive.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException,ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void Reg_purchaseOrders_GST_ExclusivePrice() throws IOException, ParseException, InterruptedException, AWTException {
        PurchaseOrder_RegIntraExclusive pr = new PurchaseOrder_RegIntraExclusive(driver, file);
        PVOPO_Reg_IntraExclusive pvopo=new PVOPO_Reg_IntraExclusive(driver,file);
        pvopo.pvopo_Reg_IntraExclusive(pr.reg_PV_GSTExclusive()[1]);
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
