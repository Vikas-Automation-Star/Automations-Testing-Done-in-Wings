package menuItems.purchase.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.purchase.transactions.PRWIR_UnRegInterInclusive;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class TestPRWIR_UnRegInterInclusivePrice {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String dataFile = "./src/main/resources/menuItems/purchase/transactions/PRWIR_UnRegInterInclusive.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void reg_To_UnReg_InterState_PurchaseReturnWithInvoice_GST_TCS_Exclusive() throws IOException, ParseException, InterruptedException, AWTException {
        PRWIR_UnRegInterInclusive voucherRef=new PRWIR_UnRegInterInclusive(driver,dataFile);
        voucherRef.reg_To_UnReg_InterState_PurchaseReturnsWithInvoice_GST_TCS_Inclusive("PV 47");
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
