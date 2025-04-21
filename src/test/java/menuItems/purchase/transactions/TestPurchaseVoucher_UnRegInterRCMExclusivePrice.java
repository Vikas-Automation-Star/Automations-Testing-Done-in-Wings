package menuItems.purchase.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.purchase.transactions.PurchaseVoucher_UnRegInterRCMExclusive;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class TestPurchaseVoucher_UnRegInterRCMExclusivePrice {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String dataFile = "./src/main/resources/menuItems/purchase/transactions/PurchaseVoucher_UnRegInterRCMExclusive.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void UnReg_InterPV_RCMGST_Exclusive() throws IOException, ParseException, InterruptedException, AWTException {
        PurchaseVoucher_UnRegInterRCMExclusive unRegRCMInc=new PurchaseVoucher_UnRegInterRCMExclusive(driver,dataFile);
        unRegRCMInc.purchaseVoucher_UnRegInterRCMExclusive();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
