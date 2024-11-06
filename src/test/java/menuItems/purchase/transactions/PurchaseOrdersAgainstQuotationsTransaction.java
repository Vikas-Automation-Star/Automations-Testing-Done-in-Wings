package menuItems.purchase.transactions;

import com.wings.pages.purchase.transactions.PurchaseOrdersAgainstQuotation;
import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class PurchaseOrdersAgainstQuotationsTransaction {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    String file = "./src/main/resources/MenuItems/purchase/transactions/PurchaseOrdersAgainstQuotation.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void purchaseOrdersAgainstQuotations() throws IOException, ParseException, InterruptedException {
        PurchaseOrdersAgainstQuotation poaq=new PurchaseOrdersAgainstQuotation(driver,file);
        poaq.purchaseOrdersAgainstQuotation();
    }

    @AfterTest
    public void afterTest(){
        appLogin.logout();
    }
}
