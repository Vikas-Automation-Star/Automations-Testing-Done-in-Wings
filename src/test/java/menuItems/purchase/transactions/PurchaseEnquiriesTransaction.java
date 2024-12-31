package menuItems.purchase.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.purchase.transactions.PurchaseEnquiries;
import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
public class PurchaseEnquiriesTransaction {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file = "./src/main/resources/menuItems/purchase/transactions/purchaseEnquiries.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
        Allure.step("Before Test PurchaseEnquiries Transaction");
    }

    @Test
    public void purchaseEnquiries() throws IOException, ParseException, InterruptedException {
        PurchaseEnquiries pe=new PurchaseEnquiries(driver,file);
        pe.PurchaseEnquires();
    }
    @AfterTest
    public void afterTest(){
        appLogin.logout();
        Allure.step("After Test PurchaseEnquiries Transaction");

    }
}
