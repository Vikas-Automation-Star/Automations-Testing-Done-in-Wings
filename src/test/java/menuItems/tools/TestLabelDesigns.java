package menuItems.tools;

import com.wings.pages.AppLogin;
import com.wings.pages.tools.formsAndPrintTemplates.LabelDesigns;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;

public class TestLabelDesigns {

        WindowsDriver driver;
        AppLogin appLogin = new AppLogin();
        String file = "./src/main/resources/menuItems/tools/labelDesign.json";

        @BeforeTest
        public void beforeTest() throws InterruptedException, IOException, ParseException {
            driver = appLogin.login();

        }

        @Test
        public void labelDesigns() throws Exception {
           LabelDesigns labelDesigns=new LabelDesigns(driver,file);
           labelDesigns.labelDesigns();
        }

        @AfterTest
        public void afterTest() throws IOException {
            appLogin.logout();
        }
    }