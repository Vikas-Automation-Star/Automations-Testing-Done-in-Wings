package menuItems.company.masters;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.Company;
import com.wings.pages.company.masters.Transporters;

import java.awt.*;
import java.io.IOException;

public class createTransporters {
    WindowsDriver driver;
    AppLogin login=new AppLogin();
    String file="./src/main/resources/MenuItems/company/Masters/transporterData.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=login.launchSingleUserApp();
        login.singleUserLogin();
    }

    @Test
    public void findTransport() throws IOException, ParseException, InterruptedException, AWTException {
        Transporters transporters = new Transporters(driver, file);
        transporters.navigateToMaster();
    }

    @AfterTest
    public void afterTest(){
        login.logout();
    }

}
