package menuItems.configure.masters;

import com.wings.pages.AppLogin;
import com.wings.pages.configure.masters.ImageTypes;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class TestImageTypes {

        WindowsDriver driver;
        AppLogin appLogin = new AppLogin();
        String file = "./src/main/resources/menuItems/configure/imageTypes.json";

        @BeforeTest
        public void beforeTest() throws InterruptedException, IOException, ParseException {
            driver = appLogin.launchSingleUserApp();
            appLogin.singleUserLogin();
        }

        @Test
        public void imageTypes() throws IOException, ParseException, InterruptedException, AWTException {
            ImageTypes imageTypes=new ImageTypes(driver,file);
            imageTypes.imageTypes();
        }

        @AfterTest
        public void afterTest() throws IOException {
            appLogin.logout();
        }
    }