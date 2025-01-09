package menu;

import com.wings.pages.AppLogin;
import com.wings.pages.LandingPage;
import io.appium.java_client.windows.WindowsDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

//@Listeners(listener.class)
public class Demo1 {

    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    LandingPage landingPage;

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException {
        driver = appLogin.login();
    }

    @Test
    public void validateTabItems() throws InterruptedException {
        landingPage = new LandingPage(driver);
        landingPage.validateTabs();
    }

    @AfterTest
    public void afterTest() throws IOException {
        System.out.println("After locationMaster 2 ");
        landingPage.quit();
    }
}