package tradeTesting.inventory.masters;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestProductVariants {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file = "./src/main/resources/tradeAutomation/inventory/masters/tradeCreateProductVariants.json";

    @BeforeTest
    public void beforeTest() throws InterruptedException, IOException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void createProductVariants () throws Exception {
        CreateProductVariants productVariants=new CreateProductVariants(driver,file);
        productVariants.createProductVariants();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
