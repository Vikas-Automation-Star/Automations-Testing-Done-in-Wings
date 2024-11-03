package menuItems.company.masters;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.Company;
import com.wings.pages.company.masters.Routes;

import java.io.IOException;

public class createRoute {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    Routes routes;
    String file="./src/main/resources/MenuItems/company/Masters/routeData.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void newRoute() throws IOException, ParseException, InterruptedException {
        routes = new Routes(driver, file);
        routes.findRoute();
    }

    @AfterTest
    public void afterTest() {
        appLogin.logout();
    }
}

