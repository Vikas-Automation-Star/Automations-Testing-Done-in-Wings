package menuItems.purchase.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.purchase.transactions.PRWIR_RegIntraExclusive;
import com.wings.pages.purchase.transactions.PurchaseVoucher_RegIntraExclusive;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class TestPRWIR_RegIntraExclusivePrice {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String voucherdatafile="./src/main/resources/menuItems/purchase/transactions/PurchaseVoucher_RegIntraExclusive.json";
    String dataFile = "./src/main/resources/menuItems/purchase/transactions/PRWIR_RegIntraExclusive.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void Reg_IntraPurchaseReturnsWithInvoice_ExclusivePrice() throws IOException, ParseException, InterruptedException, AWTException {
        PurchaseVoucher_RegIntraExclusive voucher=new PurchaseVoucher_RegIntraExclusive(driver,voucherdatafile);
        String exclusiveVoucher=voucher.IntraState_PurchaseVoucher_GST_TCS_Exclusive();
        PRWIR_RegIntraExclusive voucherReturns=new PRWIR_RegIntraExclusive(driver,dataFile);
        voucherReturns.Reg_To_Reg_IntraState_PurchaseReturnsWithInvoice(exclusiveVoucher);
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
