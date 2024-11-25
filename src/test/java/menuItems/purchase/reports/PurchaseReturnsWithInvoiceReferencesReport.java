package menuItems.purchase.reports;

import com.wings.pages.purchase.reports.PurchaseReturnsWithInvoiceReference;
import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Allure;
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
        Allure.step("Before Test PurchaseReturnsWithInvoiceReferences Report");

    }

    @Test
    public void purchaseReturnsWithInvoiceReferences() throws InterruptedException, AWTException {
        PurchaseReturnsWithInvoiceReference prwir=new PurchaseReturnsWithInvoiceReference(driver);
        prwir.purchaseReturnsWithInvoiceReference();
    }

    @AfterTest
    public void afterTest(){
        appLogin.logout();
        Allure.step("After Test  PurchaseReturnsWithInvoiceReferences Report");

    }
}
