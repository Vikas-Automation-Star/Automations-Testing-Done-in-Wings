package menuItems.purchase.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.purchase.transactions.PurchaseVoucher_UnRegIntraRCMExclusive;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class TestPurchaseVoucher_UnRegIntraRCMExclusivePrice {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file = "./src/main/resources/menuItems/purchase/transactions/PurchaseVoucher_UnRegIntraRCMExclusive.json";
    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void unReg_PVRCM_ExclusivePrice() throws IOException, ParseException, InterruptedException, NoSuchMethodException, AWTException {
        PurchaseVoucher_UnRegIntraRCMExclusive unRegRCMExc=new PurchaseVoucher_UnRegIntraRCMExclusive(driver,file);
        unRegRCMExc.purchaseVoucher_UnRegIntraRCMExclusive();
    }

    @BeforeTest
    public void afterTest() throws IOException, InterruptedException {
        appLogin.logout();
    }

}
