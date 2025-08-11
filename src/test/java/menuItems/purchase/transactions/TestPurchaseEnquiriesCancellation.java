package menuItems.purchase.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.purchase.transactions.PurchaseEnquiries;
import com.wings.pages.purchase.transactions.PurchaseEnquiriesCancellation;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class TestPurchaseEnquiriesCancellation {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String dataFile = "./src/main/resources/menuItems/purchase/transactions/461617 - Purchase Enquiries-AC_PE_3.xlsx";
    String dataFile1 = "./src/main/resources/menuItems/purchase/transactions/461400 - Purchase Enquiries Cancellation-AC_PEC_1.xlsx";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver= appLogin.login();
    }

    @Test
    public void purchaseEnquiriesCancellation() throws IOException, ParseException, InterruptedException, AWTException {
        PurchaseEnquiries purchaseEnquiries = new PurchaseEnquiries(driver, dataFile);
        String pe=purchaseEnquiries.purchaseEnquires();
        PurchaseEnquiriesCancellation pec = new PurchaseEnquiriesCancellation(driver, dataFile1);
        pec.purchaseEnquiriesCancellation(pe);
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
