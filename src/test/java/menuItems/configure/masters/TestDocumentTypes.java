package menuItems.configure.masters;

import com.wings.pages.AppLogin;
import com.wings.pages.configure.masters.DocumentTypes;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class TestDocumentTypes {

        WindowsDriver driver;
        AppLogin appLogin = new AppLogin();
        String file = "./src/main/resources/menuItems/configure/documentType.json";

        @BeforeTest
        public void beforeTest() throws InterruptedException, IOException, ParseException {
            driver = appLogin.launchSingleUserApp();
            appLogin.singleUserLogin();
        }

        @Test
        public void documentTypes() throws IOException, ParseException, InterruptedException, AWTException {
            DocumentTypes documentTypes=new DocumentTypes(driver,file);
            documentTypes.documentTypes();
        }

        @AfterTest
        public void afterTest() throws IOException {
            appLogin.logout();
        }
    }