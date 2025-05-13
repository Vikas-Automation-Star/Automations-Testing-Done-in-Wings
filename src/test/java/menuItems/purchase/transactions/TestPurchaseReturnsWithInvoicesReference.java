package menuItems.purchase.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.purchase.transactions.*;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class TestPurchaseReturnsWithInvoicesReference {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file = "./src/main/resources/menuItems/purchase/transactions/purchaseEnquiries.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void purchaseReturnsWithInvoicesReferences() throws IOException, ParseException, InterruptedException, AWTException {
        PurchaseEnquiries enquiries=new PurchaseEnquiries(driver,file);
        PurchaseQuotationsAgainstEnquiries quotationsAgainstEnquiries = new PurchaseQuotationsAgainstEnquiries(driver, file);
        String PQAE =quotationsAgainstEnquiries.purchaseQuotationsAgainstEnquiry(enquiries.purchaseEnquires());
        PurchaseOrdersAgainstQuotation ordersAgainstQuotation = new PurchaseOrdersAgainstQuotation(driver, file);
        String POAPQ= ordersAgainstQuotation.purchaseOrdersAgainstQuotation(PQAE);
        PurchaseVoucherAgainstPurchaseOrders voucherAgainstPurchaseOrders=new PurchaseVoucherAgainstPurchaseOrders(driver,file);
        String PVAPO=voucherAgainstPurchaseOrders.purchaseVoucherAgainstPurchaseOrders(POAPQ);
        PurchaseReturnsWithInvoicesReference prwir = new PurchaseReturnsWithInvoicesReference(driver, file);
        prwir.purchaseReturnsWithInvoicesReference(PVAPO);
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
