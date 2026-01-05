package tradeTesting.inventory.masters;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestOutletSegments {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file = "./src/main/resources/tradeAutomation/inventory/masters/tradeCreateOutletSegments.json";

    @BeforeTest
    public void beforeTest() throws InterruptedException, IOException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void createOutletSegments () throws Exception {
        CreateOutletSegments outletSegments=new CreateOutletSegments(driver,file);
        outletSegments.createOutletSegments();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
