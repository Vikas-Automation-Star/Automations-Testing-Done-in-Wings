package menuItems.purchase.transactions;

import com.wings.pages.purchase.transactions.PurchasePrice;
import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class PurchasePricesTransaction {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file = "./src/main/resources/MenuItems/purchase/transactions/PurchasePrices.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
        Allure.step("Before Test PurchasePrices Transaction");

    }

    @Test
    public void PurchasePrices() throws IOException, ParseException, InterruptedException {
        PurchasePrice pp=new PurchasePrice(driver,file);
        pp.purchasePrice();
    }

    @AfterTest
    public void afterTest(){
//        appLogin.logout();
        Allure.step("After Test PurchasePrices Transaction");

    }
}
