package tradeTesting.inventory.masters;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestLocations {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file = "./src/main/resources/tradeAutomation/inventory/masters/tradeCreateLocations.json";

    @BeforeTest
    public void beforeTest() throws InterruptedException, IOException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void createLocations () throws Exception {
        CreateLocations locations=new CreateLocations(driver,file);
        locations.createLocations();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
