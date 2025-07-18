package menuItems.purchase.reports;

import com.wings.pages.AppLogin;
import com.wings.pages.purchase.reports.PurchaseReturnsWithInvoiceReference;
import io.appium.java_client.windows.WindowsDriver;

import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class PurchaseReturnsWithInvoiceReferencesReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void purchaseReturnsWithInvoiceReferences() throws InterruptedException, AWTException {
        PurchaseReturnsWithInvoiceReference prwir = new PurchaseReturnsWithInvoiceReference(driver);
        prwir.purchaseReturnsWithInvoiceReference();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
