package menuItems.configure.masters;

import com.wings.pages.AppLogin;
import com.wings.pages.configure.masters.Images;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class TestImages {

        WindowsDriver driver;
        AppLogin appLogin = new AppLogin();
        String file = "./src/main/resources/menuItems/configure/image.json";

        @BeforeTest
        public void beforeTest() throws InterruptedException, IOException, ParseException {
            driver = appLogin.launchSingleUserApp();
            appLogin.singleUserLogin();
        }

        @Test
        public void images() throws IOException, ParseException, InterruptedException, AWTException {
            Images images=new Images(driver,file);
            images.images();
        }

        @AfterTest
        public void afterTest() throws IOException {
            appLogin.logout();
        }
    }