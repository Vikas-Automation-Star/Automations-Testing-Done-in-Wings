package menuItems.company.masters;

import com.wings.pages.AppLogin;
import com.wings.pages.company.masters.CompanyProperties;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class CompanyPropertiesMaster {


    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    CompanyProperties companyProperties;
    String file = "./src/main/resources/MenuItems/Company/Masters/CompanyPropertiesData.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void companyProperties() throws IOException, ParseException, InterruptedException, AWTException {
        companyProperties = new CompanyProperties(driver, file);
        companyProperties.companyProperty();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
