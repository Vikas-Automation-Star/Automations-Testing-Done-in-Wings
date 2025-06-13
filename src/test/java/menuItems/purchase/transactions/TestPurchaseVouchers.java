package menuItems.purchase.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.purchase.transactions.PurchaseVoucher;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class TestPurchaseVouchers {

    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    Common common;
    String file = "./src/main/resources/menuItems/purchase/transactions/PurchaseVouchers.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        common=new Common(driver);
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin(common.getData(file,"PurchaseVoucher","userName"),common.getData(file,"PurchaseVoucher","password"));
    }

    @Test
    public void purchaseVouchers() throws IOException, ParseException, InterruptedException, AWTException {
        PurchaseVoucher po = new PurchaseVoucher(driver, file);
        po.purchaseVoucher();
    }


    @AfterTest
    public void afterTest() throws IOException {
//        appLogin.logout();
    }

}
