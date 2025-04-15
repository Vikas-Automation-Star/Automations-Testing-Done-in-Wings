package menuItems.purchase.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.purchase.transactions.PRWIR_RegIntraRCMExclusive;
import com.wings.pages.purchase.transactions.PurchaseVoucher_RegIntraRCMExclusive;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import java.awt.*;
import java.io.IOException;
public class TestPurchaseVoucher_RegIntraRCMExclusivePrice {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String dataFile = "./src/main/resources/menuItems/purchase/transactions/Reg_PV_IntraState_RCMGST_TCS_Exclusive.json";
    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }
    @Test
    public void reg_IntraState_PV_RCMGST_TCS_Exclusive() throws IOException, ParseException, InterruptedException, AWTException {
        PurchaseVoucher_RegIntraRCMExclusive rcmGST=new PurchaseVoucher_RegIntraRCMExclusive(driver,dataFile);
        String rcmVoucher=rcmGST.IntraState_PV_RCMGST_TCS_Exclusive();
        PRWIR_RegIntraRCMExclusive returnRCm=new PRWIR_RegIntraRCMExclusive(driver,dataFile);
        returnRCm.regPrwirExcusive(rcmVoucher);
    }
    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
