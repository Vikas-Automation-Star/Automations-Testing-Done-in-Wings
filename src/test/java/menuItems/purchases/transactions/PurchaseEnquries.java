package menuItems.purchases.transactions;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.Company;
import com.wings.pages.purchase.transactions.PurchaseEnquiry;

import java.io.IOException;

public class PurchaseEnquries {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    Company company;
    String file = "./src/main/resources/menuItems/purchases/transactions/purchaseEnquiries.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void newSalesEnquiryTrsncn() throws IOException, ParseException, InterruptedException {
        PurchaseEnquiry enquiry=new PurchaseEnquiry(driver,file);
        enquiry.createTransaction();
    }

    @AfterTest
    public void afterTest(){
        appLogin.logout();
    }
}
