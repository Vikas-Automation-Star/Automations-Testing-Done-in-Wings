package menuItems.purchase.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.purchase.transactions.PurchaseReturns;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class TestPurchaseReturns {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    Common common;
    String file = "./src/main/resources/menuItems/purchase/transactions/purchaseReturns.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        common=new Common(driver);
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin(common.getData(file,"PurchaseReturns","userName"),common.getData(file,"PurchaseReturns","password"));
    }


    @Test
    public void purchaseReturns() throws IOException, ParseException, InterruptedException, AWTException {
        PurchaseReturns purchaseReturns=new PurchaseReturns(driver,file);
        purchaseReturns.purchaseReturns();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }

}
