package menuItems.inventory.reports;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.inventory.reports.InterLocationTransfer;

import java.awt.*;
import java.io.IOException;

public class InterLocationTransfers {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void interLocationTransfer() throws  InterruptedException, AWTException {
        InterLocationTransfer locationTransfer=new InterLocationTransfer(driver);
        locationTransfer.intrLocationTransfer();
    }

    @AfterTest
    public void afterTest(){
        appLogin.logout();
    }
}
