package menuItems.Taxes.masters.GST;

import com.wings.pages.AppLogin;
import com.wings.pages.taxes.masters.GST.Consigner;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class ConsignerMaster {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file = "./src/main/resources/MenuItems/Taxes/masters/gst/Consigner.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void consignerReport() throws InterruptedException, IOException, ParseException, AWTException {
        Consigner consigner = new Consigner(driver, file);
        consigner.consigner();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
