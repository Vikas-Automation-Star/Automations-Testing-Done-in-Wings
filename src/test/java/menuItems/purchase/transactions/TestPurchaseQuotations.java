package menuItems.purchase.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.purchase.transactions.PurchaseQuotation;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class TestPurchaseQuotations {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file = "./src/main/resources/menuItems/purchase/transactions/469187 - Purchase Quotations-AC_PQ_1.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.login();
    }

    @Test
    public void purchaseQuotations() throws IOException, ParseException, InterruptedException, AWTException {
        PurchaseQuotation pq = new PurchaseQuotation(driver, file);
        pq.purchaseQuotation();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
