package menuItems.Taxes.transactions.EwayBill;

import com.wings.pages.AppLogin;
import com.wings.pages.taxes.transactions.EwayBill.EwayBillOffline;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class EwayBillOfflineTransaction {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String file ="./src/main/resources/MenuItems/Taxes/transactions/EwayBill/EwayBillOfflineTransaction.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public  void ewayBillOfflineTransaction() throws InterruptedException, IOException, ParseException, AWTException {
        EwayBillOffline ewbo=new EwayBillOffline(driver,file);
        ewbo.ewayBillOffline();
    }

    @AfterTest
    public void afterTest(){
        appLogin.logout();
    }
}
