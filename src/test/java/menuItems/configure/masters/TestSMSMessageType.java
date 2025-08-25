package menuItems.configure.masters;

import com.wings.pages.AppLogin;
import com.wings.pages.configure.masters.SMS;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class TestSMSMessageType {

    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file = "./src/main/resources/menuItems/configure/smsMessageTypes.json";

    @BeforeTest
    public void beforeTest() throws InterruptedException, IOException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void smsMessageType() throws Exception, AWTException {
        SMS sms = new SMS(driver, file);
        sms.sMSMessageTypes();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}