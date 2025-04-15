package menuItems.purchase.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.purchase.transactions.PRWIR_RegInterInclusive;
import com.wings.pages.purchase.transactions.PurchaseVoucher_RegInterInclusive;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class TestPRWIR_RegInterInclusivePrice {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String VoucherDataFile="./src/main/resources/menuItems/purchase/transactions/PurchaseVoucher_RegInterInclusive.json";
    String dataFile = "./src/main/resources/menuItems/purchase/transactions/PRWIR_RegInterInclusive.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void reg_To_Reg_InterState_PurchaseReturnWithInvoice_GST_TCS_Inclusive() throws IOException, ParseException, InterruptedException, AWTException {
        PurchaseVoucher_RegInterInclusive purchaseVoucher=new PurchaseVoucher_RegInterInclusive(driver,VoucherDataFile);
        String inclusivePV =purchaseVoucher.InterState_PurchaseVoucher_GST_TCS_Inclusive();
        PRWIR_RegInterInclusive voucherRef=new PRWIR_RegInterInclusive(driver,dataFile);
        voucherRef.reg_To_Reg_InterState_PurchaseReturnWithInvoice_GST_TCS_Inclusive(inclusivePV);
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }

}
