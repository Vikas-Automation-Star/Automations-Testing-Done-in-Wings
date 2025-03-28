package menuItems.purchase.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.purchase.transactions.Reg_To_Reg_IntraState_PurchaseReturnsWithInvoice_GST_TCS_Exclusive;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class Reg_To_Reg_IntraState_PurchaseReturnsWithInvoice_GST_TCS_ExclusivePrice {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String dataFile = "./src/main/resources/menuItems/purchase/transactions/Reg_To_Reg_IntraState_PurchaseReturnsWithInvoice_GST_TCS_Exclusive.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void Reg_To_Reg_IntraState_PurchaseReturnsWithInvoice_GST_TCS_ExclusivePrice() throws IOException, ParseException, InterruptedException, AWTException {
        Reg_To_Reg_IntraState_PurchaseReturnsWithInvoice_GST_TCS_Exclusive voucherReturns=new Reg_To_Reg_IntraState_PurchaseReturnsWithInvoice_GST_TCS_Exclusive(driver,dataFile);
        voucherReturns.Reg_To_Reg_IntraState_PurchaseReturnsWithInvoice("PV25");
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
