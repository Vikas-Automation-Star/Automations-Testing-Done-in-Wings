package menuItems.purchase.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.purchase.transactions.PurchasePrice;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestPurchasePrices {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    Common common;
    String file = "./src/main/resources/menuItems/purchase/transactions/PurchasePrices.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        common=new Common(driver);
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin(common.getData(file,"PurchasePrice","userName"),common.getData(file,"PurchasePrice","password"));
    }

    @Test
    public void PurchasePrices() throws IOException, ParseException, InterruptedException {
        PurchasePrice pp = new PurchasePrice(driver, file);
        pp.purchasePrice();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
