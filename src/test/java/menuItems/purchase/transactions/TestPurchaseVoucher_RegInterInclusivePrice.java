package menuItems.purchase.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.purchase.transactions.PurchaseVoucher_RegInterInclusive;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class TestPurchaseVoucher_RegInterInclusivePrice {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String dataFile = "./src/main/resources/menuItems/purchase/transactions/PurchaseVoucher_RegInterInclusive.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void Reg_InterPurchaseVoucher_GST_TCS_Inclusive() throws IOException, ParseException, InterruptedException, AWTException {
        PurchaseVoucher_RegInterInclusive voucher=new PurchaseVoucher_RegInterInclusive(driver,dataFile);
        voucher.InterState_PurchaseVoucher_GST_TCS_Inclusive();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }

}
