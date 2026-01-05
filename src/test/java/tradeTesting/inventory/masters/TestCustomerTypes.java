package tradeTesting.inventory.masters;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestCustomerTypes {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file = "./src/main/resources/tradeAutomation/inventory/masters/tradeCreateCustomerTypes.json";

    @BeforeTest
    public void beforeTest() throws  InterruptedException, IOException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void createCustomerTypes () throws Exception {
        CreateCustomerTypes customerTypes=new CreateCustomerTypes(driver,file);
        customerTypes.createCustomerTypes();

    }

    @AfterTest
    public void afterTest() throws  IOException {
        appLogin.logout();
    }
}
