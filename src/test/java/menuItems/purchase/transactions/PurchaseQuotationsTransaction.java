package menuItems.purchase.transactions;

import com.wings.pages.purchase.transactions.PurchaseQuotation;
import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class PurchaseQuotationsTransaction {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file = "./src/main/resources/MenuItems/purchase/transactions/purchaseQuotation.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
        Allure.step("Before Test PurchaseQuotations Transaction");

    }

    @Test
    public void purchaseQuotations() throws IOException, ParseException, InterruptedException {
        PurchaseQuotation pq=new PurchaseQuotation(driver,file);
        pq.purchaseQuotation();

    }
    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
        Allure.step("After Test PurchaseEnquiries Transaction");

    }
}
