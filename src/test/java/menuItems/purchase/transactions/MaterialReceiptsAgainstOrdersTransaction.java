package menuItems.purchase.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.purchase.transactions.MaterialReceiptsAgainstOrder;
import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class MaterialReceiptsAgainstOrdersTransaction {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file = "./src/main/resources/MenuItems/purchase/transactions/MeterialReceiptsAgainstOrder.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
        Allure.step("Before Test PurchaseReceiptsAgainstOrders Transaction");

    }

    @Test
    public void MeterialReceiptsAgainstOrders() throws IOException, ParseException, InterruptedException {
        MaterialReceiptsAgainstOrder mrao=new MaterialReceiptsAgainstOrder(driver,file);
        mrao.meterialReceiptsAgainstOrder();

    }

    @AfterTest
    public void afterTest(){
        appLogin.logout();
        Allure.step("After Test PurchaseReceiptsAgainstOrders Transaction");

    }
}
