package menuItems.purchase.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.purchase.transactions.PurchaseVoucher_RegIntraExclusive;
import io.appium.java_client.windows.WindowsDriver;

import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class TestPurchaseVoucher_RegIntraExclusivePrice {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String dataFile = "./src/main/resources/menuItems/purchase/transactions/Reg_To_Reg_IntraState_PV_GST_TCS_Exclusive.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void Reg_To_Reg_IntraState_PurchaseVoucher_GST_TCS_Exclusive() throws IOException, ParseException, InterruptedException, AWTException {
        PurchaseVoucher_RegIntraExclusive voucher=new PurchaseVoucher_RegIntraExclusive(driver,dataFile);
       voucher.IntraState_PurchaseVoucher_GST_TCS_Exclusive();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
