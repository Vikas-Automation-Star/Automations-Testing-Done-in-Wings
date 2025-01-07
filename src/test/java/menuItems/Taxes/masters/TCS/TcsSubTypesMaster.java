package menuItems.Taxes.masters.TCS;

import com.wings.pages.taxes.masters.TCS.TcsSubTypes;
import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class TcsSubTypesMaster {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String file ="./src/main/resources/MenuItems/Taxes/masters/TCS/TcsSubTypes.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void tcsSubTypesMaster() throws InterruptedException, IOException, ParseException, AWTException {
        TcsSubTypes tsm=new TcsSubTypes(driver,file);
        tsm.tcsSubTypes();

    }

    @AfterTest
    public void afterTest() throws IOException{
        appLogin.logout();
    }
}
