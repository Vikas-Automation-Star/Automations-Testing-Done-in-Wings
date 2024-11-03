package menuItems.purchases.masters;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.purchases.masters.PurchasePriceList;

import java.io.IOException;

public class createPurchasePriceList {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file="./src/main/resources/menuItems/purchases/masters/purchaseList.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void newPriceList() throws InterruptedException, IOException, ParseException {
        PurchasePriceList priceList=new PurchasePriceList(driver,file);
        priceList.purchasePriceList();
    }

    @AfterTest
    public void afterTest(){
        appLogin.logout();
    }

}
