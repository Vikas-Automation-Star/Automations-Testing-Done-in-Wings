package menuItems.purchase.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.purchase.transactions.Reg_To_UnReg_PurchaseVoucher_InterState_GST_TCS_Inclusive;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class Reg_To_UnReg_PurchaseVoucher_InterState_GST_TCS_InclusivePrice {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String dataFile = "./src/main/resources/menuItems/purchase/transactions/Reg_To_UnReg_PurchaseVoucher_InterState_GST_TCS_Inclusive.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void Reg_To_UnReg_PurchaseVoucher_InterState_Inclusive() throws IOException, ParseException, InterruptedException, AWTException {
        Reg_To_UnReg_PurchaseVoucher_InterState_GST_TCS_Inclusive voucher=new Reg_To_UnReg_PurchaseVoucher_InterState_GST_TCS_Inclusive(driver,dataFile);
        voucher.Reg_To_UnReg_PurchaseVoucher_InterState_Inclusive();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
