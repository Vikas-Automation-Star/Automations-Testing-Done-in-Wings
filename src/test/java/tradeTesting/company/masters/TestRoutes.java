package tradeTesting.company.masters;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestRoutes {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file = "./src/main/resources/tradeAutomation/company/masters/tradeRoutes.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void routes() throws Exception {
        CreatesRoutes routes=new CreatesRoutes(driver,file);
        routes.routes();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
