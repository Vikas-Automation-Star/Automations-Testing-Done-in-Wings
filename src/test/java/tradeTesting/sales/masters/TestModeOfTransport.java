package tradeTesting.sales.masters;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestModeOfTransport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file = "./src/main/resources/tradeAutomation/sales/masters/tradeModeOfTransport.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void modeOfTransport() throws Exception {
        CreateModeOfTransport modeOfTransport=new CreateModeOfTransport(driver,file);
        modeOfTransport.createModeOfTransport();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
