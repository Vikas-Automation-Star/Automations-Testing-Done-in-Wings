package menuItems.company.masters;

import com.wings.pages.AppLogin;
import com.wings.pages.company.masters.Executive;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class ExecutiveMaster {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    Executive newExecutive;
    String file = "./src/main/resources/MenuItems/Company/Masters/ExecutiveData.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void createExecutive() throws IOException, ParseException, InterruptedException, AWTException {
        newExecutive = new Executive(driver, file);
        newExecutive.Executive();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }

}
