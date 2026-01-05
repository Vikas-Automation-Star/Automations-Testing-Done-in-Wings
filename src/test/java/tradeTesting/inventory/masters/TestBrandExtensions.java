package tradeTesting.inventory.masters;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestBrandExtensions {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file = "./src/main/resources/tradeAutomation/inventory/masters/tradeCreateBrandExtensions.json";

    @BeforeTest
    public void beforeTest() throws  InterruptedException, IOException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void createBrandExtensions () throws Exception {
        CreateBrandExtensions brandExtensions=new CreateBrandExtensions(driver,file);
        brandExtensions.createBrandExtensions();

    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
