package tradeTesting.sales.masters;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestSimpleSchemesPrimary {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file = "./src/main/resources/tradeAutomation/sales/masters/tradeSimpleSchemesPrimary.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void simpleSchemesPrimary() throws Exception {
        CreateSimpleSchemesPrimary simpleSchemesPrimary=new CreateSimpleSchemesPrimary(driver,file);
        simpleSchemesPrimary.createSimpleSchemesPrimary();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
