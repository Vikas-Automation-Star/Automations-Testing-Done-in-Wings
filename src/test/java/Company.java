import com.wings.pages.AppLogin;
import com.wings.pages.LandingPage;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class Company {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    com.wings.pages.Company company;
    LandingPage landingPage;

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException {
        driver = appLogin.launchSingleUserApp();
    }

    @Test
    public void creation() throws InterruptedException, IOException, ParseException {
        company = new com.wings.pages.Company(driver);
        company.companyCreation();
        driver = appLogin.launchSingleUserApp();
        company = new com.wings.pages.Company(driver);
        company.companyLogin();
        landingPage = new LandingPage(driver);
        landingPage.validateMenus();
        landingPage.validateTabs();
        System.out.println("Overall Company creation locationMaster script ran successfully without any issues! " + new String(Character.toChars(0x1F349)));
    }

    @AfterTest
    public void afterTest() throws IOException {
        landingPage.quit();
    }
}
