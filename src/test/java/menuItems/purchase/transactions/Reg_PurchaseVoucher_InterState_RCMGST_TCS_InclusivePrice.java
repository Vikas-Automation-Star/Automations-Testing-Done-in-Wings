package menuItems.purchase.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.purchase.transactions.Reg_PRWIR_Interstate_RCMGST_TCS_InclusivePrice;
import com.wings.pages.purchase.transactions.UnReg_PurchaseVoucher_InterState_RCMGST_TCS_Inclusive;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class Reg_PurchaseVoucher_InterState_RCMGST_TCS_InclusivePrice {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String dataFile = "./src/main/resources/menuItems/purchase/transactions/Reg_PurchaseVoucher_InterState_RCMGST_TCS_Inclusive.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void reg_InterState_PV_RCMGST_TCS_Inclusive() throws IOException, ParseException, InterruptedException, AWTException {
        UnReg_PurchaseVoucher_InterState_RCMGST_TCS_Inclusive inclusiveRCM=new UnReg_PurchaseVoucher_InterState_RCMGST_TCS_Inclusive(driver,dataFile);
        String purchaseVoucher=inclusiveRCM.InterState_PV_GST_TCS_Inclusive();
        Reg_PRWIR_Interstate_RCMGST_TCS_InclusivePrice invoiceReturns=new Reg_PRWIR_Interstate_RCMGST_TCS_InclusivePrice(driver,dataFile);
        invoiceReturns.regPrwirInclusive(purchaseVoucher);
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
