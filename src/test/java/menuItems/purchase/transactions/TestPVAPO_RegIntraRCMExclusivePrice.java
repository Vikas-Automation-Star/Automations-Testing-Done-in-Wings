package menuItems.purchase.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.purchase.transactions.PurchaseOrder_RegIntraExclusive;
import com.wings.pages.purchase.transactions.PVAPO_RegIntraRCMGSTExclusive;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class TestPVAPO_RegIntraRCMExclusivePrice {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file = "./src/main/resources/MenuItems/purchase/transactions/PO_MReceiptsAPO_PVAPO_RegIntraExclusive.json";
    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void reg_PVAO_RCM_GST_ExclusivePrice() throws IOException, ParseException, InterruptedException, NoSuchMethodException, AWTException {
        PurchaseOrder_RegIntraExclusive pr = new PurchaseOrder_RegIntraExclusive(driver, file);
        String [] exclusivePVRCM= pr.reg_PV_GSTExclusive();
        PVAPO_RegIntraRCMGSTExclusive pvoa=new PVAPO_RegIntraRCMGSTExclusive(driver,file);
        pvoa.reg_PVAO_Exclusive(exclusivePVRCM[1]);
    }

    @BeforeTest
    public void afterTest() throws IOException, InterruptedException {
                appLogin.logout();
    }
}
