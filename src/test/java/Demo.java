import com.wings.pages.AppLogin;
import com.wings.pages.LandingPage;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

//@Listeners(Listener.class)
public class Demo {

    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    LandingPage landingPage;

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.login();
    }

    @Test
    public void validateMenuItems() throws IOException, InterruptedException, ParseException {
        landingPage = new LandingPage(driver);
        landingPage.validateMenus();
    }

    @Test
    public void validateTabItems() throws InterruptedException {
        landingPage = new LandingPage(driver);
        landingPage.validateTabs();
    }

    @AfterTest
    public void afterTest() throws IOException {
        System.out.println("After locationMaster");
        landingPage.quit();
    }
}