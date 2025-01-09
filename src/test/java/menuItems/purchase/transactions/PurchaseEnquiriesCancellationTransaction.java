package menuItems.purchase.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.purchase.transactions.PurchaseEnquiriesCancellation;
import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class PurchaseEnquiriesCancellationTransaction {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file = "./src/main/resources/MenuItems/purchase/transactions/purchaseEnquiriesCancellation.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
        Allure.step("Before Test PurchaseEnquiriesCancellation Transaction");

    }

    @Test
    public void purchaseEnquiriesCancellation() throws IOException, ParseException, InterruptedException {
        PurchaseEnquiriesCancellation pec = new PurchaseEnquiriesCancellation(driver, file);
        pec.purchaseEnquiriesCancellation();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
        Allure.step("After Test PurchaseEnquiriescancellation Transation");
    }
}
