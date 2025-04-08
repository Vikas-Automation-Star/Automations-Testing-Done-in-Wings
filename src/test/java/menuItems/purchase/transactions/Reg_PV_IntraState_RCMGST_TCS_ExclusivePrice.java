package menuItems.purchase.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.purchase.transactions.Reg_PRWIR_IntraState_RCMGST_TCS_ExclusivePrice;
import com.wings.pages.purchase.transactions.Reg_PV_IntraState_RCMGST_TCS_Exclusive;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import java.awt.*;
import java.io.IOException;
public class Reg_PV_IntraState_RCMGST_TCS_ExclusivePrice {
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
        Reg_PV_IntraState_RCMGST_TCS_Exclusive rcmGST=new Reg_PV_IntraState_RCMGST_TCS_Exclusive(driver,dataFile);
        String rcmVoucher=rcmGST.IntraState_PV_RCMGST_TCS_Exclusive();
        Reg_PRWIR_IntraState_RCMGST_TCS_ExclusivePrice returnRCm=new Reg_PRWIR_IntraState_RCMGST_TCS_ExclusivePrice(driver,dataFile);
        returnRCm.regPrwirExcusive(rcmVoucher);
    }
    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
