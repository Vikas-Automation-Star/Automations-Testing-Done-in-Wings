package menuItems.company.masters;

import com.wings.pages.company.masters.Transporters;
import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class TransportersMaster {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String file="./src/main/resources/MenuItems/Company/Masters/TransportersData.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void routesMaster() throws IOException, ParseException, InterruptedException, AWTException {
        Transporters transporters=new Transporters(driver,file);
        transporters.createTransporters();
    }

    @AfterTest
    public void afterTest() throws IOException{
        appLogin.logout();
    }

}
