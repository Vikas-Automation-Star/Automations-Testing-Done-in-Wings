package menuItems.purchase.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.purchase.transactions.PRWIR_RegInterstateRCMInclusive;
import com.wings.pages.purchase.transactions.PurchaseVoucher_RegInterRCMInclusive;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class TestPurchaseVoucher_RegInterRCMInclusivePrice {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String dataFile = "./src/main/resources/menuItems/purchase/transactions/Reg_PV_InterState_RCMGST_TCS_Inclusive.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void reg_InterState_PV_RCMGST_TCS_Inclusive() throws IOException, ParseException, InterruptedException, AWTException {
        PurchaseVoucher_RegInterRCMInclusive inclusiveRCM=new PurchaseVoucher_RegInterRCMInclusive(driver,dataFile);
        String purchaseVoucher=inclusiveRCM.InterState_PV_GST_TCS_Inclusive();
        PRWIR_RegInterstateRCMInclusive invoiceReturns=new PRWIR_RegInterstateRCMInclusive(driver,dataFile);
        invoiceReturns.regPrwirInclusive(purchaseVoucher);
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
