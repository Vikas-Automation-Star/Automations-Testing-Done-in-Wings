package tradeTesting.inventory.masters;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import java.io.IOException;

public class TestProducts {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file = "./src/main/resources/tradeAutomation/inventory/masters/tradeCreateProducts.json";

    @BeforeTest
    public void beforeTest() throws InterruptedException, IOException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void createProducts () throws Exception {
        CreateProducts products=new CreateProducts(driver,file);
        products.createProducts();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }

}
