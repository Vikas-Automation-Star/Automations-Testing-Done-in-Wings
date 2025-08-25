package Technical;

import com.wings.Technical.QuickMenu;
import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;

public class TestQuickMenu {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String dataFile="./src/main/resources/Technical/technicalFeatures.json";

    @BeforeTest
    public void beforeTest() throws Exception {
        driver=appLogin.login();
    }

    @Test
    public void quickMenu() throws InterruptedException, IOException, ParseException {
        QuickMenu quickMenu=new QuickMenu(driver,dataFile);
        quickMenu.quickMenu();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
