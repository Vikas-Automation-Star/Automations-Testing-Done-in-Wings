package menuItems.purchase.transactions;
import com.wings.pages.AppLogin;
import com.wings.pages.purchase.transactions.PurchaseVoucherAgainstPurchaseOrders;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class TestPurchaseVoucherAgainstOrders {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    Common common;
    String file = "./src/main/resources/menuItems/purchase/transactions/purchaseEnquiries.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        common=new Common(driver);
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin(common.getData(file,"Purchase Enquiries","userName"),common.getData(file,"Purchase Enquiries","password"));
    }

    @Test
    public void purchaseOrdersAgainstQuotations() throws IOException, ParseException, InterruptedException, AWTException {
//        PurchaseEnquiries enquiries=new PurchaseEnquiries(driver,file);
//        PurchaseQuotationsAgainstEnquiries quotationsAgainstEnquiries = new PurchaseQuotationsAgainstEnquiries(driver, file);
//        String PQAE =quotationsAgainstEnquiries.purchaseQuotationsAgainstEnquiry(enquiries.purchaseEnquires());
//        PurchaseOrdersAgainstQuotation POAQ = new PurchaseOrdersAgainstQuotation(driver, file);
//        POAQ.purchaseOrdersAgainstQuotation(PQAE);

        PurchaseVoucherAgainstPurchaseOrders voucherAgainstPurchaseOrders=new PurchaseVoucherAgainstPurchaseOrders(driver,file);
        String PVAPO=voucherAgainstPurchaseOrders.purchaseVoucherAgainstPurchaseOrders("POAQ 2");
    }

    @AfterTest
    public void afterTest() throws IOException {
//        appLogin.logout();
    }
}
