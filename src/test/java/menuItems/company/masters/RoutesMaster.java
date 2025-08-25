package menuItems.company.masters;

import com.wings.pages.company.masters.Routes;
import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class RoutesMaster {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file = "./src/main/resources/MenuItems/Company/Masters/RoutesMasterData.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void routesMaster() throws Exception {
        Routes routes = new Routes(driver, file);
        routes.createRoutes();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }

}
