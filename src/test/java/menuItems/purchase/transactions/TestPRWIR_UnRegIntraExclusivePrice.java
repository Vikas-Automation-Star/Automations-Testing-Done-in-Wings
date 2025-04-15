package menuItems.purchase.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.purchase.transactions.PRWIR_UnRegIntraExclusive;
import com.wings.pages.purchase.transactions.PurchaseVoucher_UnRegIntraExclusive;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class TestPRWIR_UnRegIntraExclusivePrice {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String voucherDataFile = "./src/main/resources/menuItems/purchase/transactions/PurchaseVoucher_UnRegIntraExclusive.json";
    String prwirDataFile = "./src/main/resources/menuItems/purchase/transactions/PRWIR_UnRegIntraExclusive.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void reg_To_Reg_IntraState_PurchaseReturnWithInvoice_GST_TCS_Exclusive() throws IOException, ParseException, InterruptedException, AWTException {
        PurchaseVoucher_UnRegIntraExclusive voucher=new PurchaseVoucher_UnRegIntraExclusive(driver,voucherDataFile);
        String voucherId=voucher.Reg_To_UnReg_PvIntraStateExclusive();
        PRWIR_UnRegIntraExclusive voucherRef=new PRWIR_UnRegIntraExclusive(driver,prwirDataFile);
        voucherRef.regToUnRegPrwirExcusive(voucherId);
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
