package menuItems.Taxes.masters.GST;

import com.wings.pages.taxes.masters.GST.HsnCodes;
import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class HsnCodesMaster {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String file="./src/main/resources/MenuItems/Taxes/masters/gst/HsnCodes.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void hsnCodesMaster() throws InterruptedException, IOException, ParseException, AWTException {
        HsnCodes hc=new HsnCodes(driver,file);
        hc.hsnCodes();
    }

    @AfterTest
    public void afterTest(){
        appLogin.logout();
    }
}
