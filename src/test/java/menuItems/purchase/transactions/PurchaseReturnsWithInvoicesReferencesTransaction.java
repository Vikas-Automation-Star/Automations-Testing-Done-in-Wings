package menuItems.purchase.transactions;

import com.wings.pages.purchase.transactions.PurchaseReturnsWithInvoicesReference;
import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class PurchaseReturnsWithInvoicesReferencesTransaction {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file = "./src/main/resources/MenuItems/purchase/transactions/PurchaseReturnsWithInvoicesReference.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
        Allure.step("Before Test PurchaseReturnsWithInvoicesReferences Transaction");

    }

    @Test
    public void purchaseReturnsWithInvoicesReferences() throws IOException, ParseException, InterruptedException {
        PurchaseReturnsWithInvoicesReference prwir=new PurchaseReturnsWithInvoicesReference(driver,file);
        prwir.purchaseReturnsWithInvoicesReference();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
        Allure.step("After Test PurchaseReturnsWithInvoicesReferences Transaction");

    }
}
