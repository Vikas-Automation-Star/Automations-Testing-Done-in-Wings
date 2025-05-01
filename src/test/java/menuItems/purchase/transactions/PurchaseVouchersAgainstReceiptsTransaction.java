package menuItems.purchase.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.purchase.transactions.PurchaseVouchersAgainstReceipt;
import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class PurchaseVouchersAgainstReceiptsTransaction {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file = "./src/main/resources/MenuItems/purchase/transactions/PurchaseVouchersAgainstReceipts.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void PurchaseVouchersAgainstReceipts() throws IOException, ParseException, InterruptedException {
        PurchaseVouchersAgainstReceipt poar = new PurchaseVouchersAgainstReceipt(driver, file);
        poar.purchaseVouchersAgainstReceipt();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
