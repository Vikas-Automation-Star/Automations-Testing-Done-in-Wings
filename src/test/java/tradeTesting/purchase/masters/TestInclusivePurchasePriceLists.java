package tradeTesting.purchase.masters;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestInclusivePurchasePriceLists {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file = "./src/main/resources/tradeAutomation/purchase/masters/tradeInclusivePurchasePriceLists.json";

    @BeforeTest
    public void beforeTest() throws  InterruptedException,  IOException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void inclusivePurchasePriceLists () throws Exception {
        CreateInclusivePurchasePriceLists purchasePriceLists=new CreateInclusivePurchasePriceLists(driver,file);
        purchasePriceLists.inclusivePurchasePriceLists();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
