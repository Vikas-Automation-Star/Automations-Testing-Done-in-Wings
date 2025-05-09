package menuItems.purchase.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.purchase.transactions.PurchaseEnquiries;
import com.wings.pages.purchase.transactions.PurchaseQuotationsAgainstEnquiries;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class TestPurchaseQuotationsAgainstEnquiries {
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
    public void purchaseQuotationsAgainstEnquiries() throws IOException, ParseException, InterruptedException, AWTException {
        PurchaseEnquiries enquiries=new PurchaseEnquiries(driver,file);
        PurchaseQuotationsAgainstEnquiries pqae = new PurchaseQuotationsAgainstEnquiries(driver, file);
        pqae.purchaseQuotationsAgainstEnquiry(enquiries.purchaseEnquires());
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
