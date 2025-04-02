package menuItems.purchase.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.purchase.transactions.Reg_To_UnReg_IntraState_PurchaseReturnsWithInvoice_GST_TCS_Exclusive;
import com.wings.pages.purchase.transactions.Reg_To_UnReg_PurchaseVoucher_IntraState_GST_TCS_Exclusive;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class Reg_To_UnReg_IntraState_PurchaseReturnsWithInvoice_GST_TCS_ExclusivePrice {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String voucherDataFile = "./src/main/resources/menuItems/purchase/transactions/Reg_To_UnReg_PurchaseVoucher_IntraState_GST_TCS_Exclusive.json";
    String prwirDataFile = "./src/main/resources/menuItems/purchase/transactions/Reg_To_UnReg_IntraState_PurchaseReturnsWithInvoice_GST_TCS_Exclusive.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void reg_To_Reg_IntraState_PurchaseReturnWithInvoice_GST_TCS_Exclusive() throws IOException, ParseException, InterruptedException, AWTException {
        Reg_To_UnReg_PurchaseVoucher_IntraState_GST_TCS_Exclusive voucher=new Reg_To_UnReg_PurchaseVoucher_IntraState_GST_TCS_Exclusive(driver,voucherDataFile);
        String voucherId=voucher.Reg_To_UnReg_PvIntraStateExclusive();
        Reg_To_UnReg_IntraState_PurchaseReturnsWithInvoice_GST_TCS_Exclusive voucherRef=new Reg_To_UnReg_IntraState_PurchaseReturnsWithInvoice_GST_TCS_Exclusive(driver,prwirDataFile);
        voucherRef.regToUnRegPrwirExcusive(voucherId);
    }

    @AfterTest
    public void afterTest() throws IOException {
//        appLogin.logout();
    }
}
