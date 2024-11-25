package menuItems.purchase.transactions;

import com.wings.pages.purchase.transactions.PurchaseQuotationsAgainstEnquiries;
import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class PurchaseQuotationsAgainstEnquiriesTransaction {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file = "./src/main/resources/MenuItems/purchase/transactions/purchaseQuotationsAgainstEnquires.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
        Allure.step("Before Test PurchaseQuotationsAgainstEnquiry Transaction");

    }

    @Test
    public void purchaseQuotationsAgainstEnquiries() throws IOException, ParseException, InterruptedException {
        PurchaseQuotationsAgainstEnquiries pqae=new PurchaseQuotationsAgainstEnquiries(driver,file);
        pqae.purchaseQuotationsAgainstEnquiry();

    }
    @AfterTest
    public void afterTest(){
        appLogin.logout();
        Allure.step("After Test PurchaseQuotationsAgainstEnquiry Transaction");

    }
}
