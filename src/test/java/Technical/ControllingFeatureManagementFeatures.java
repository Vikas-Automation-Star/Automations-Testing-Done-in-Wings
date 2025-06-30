package Technical;

import com.wings.Technical.ControllingWithFeatureManagement;
import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class ControllingFeatureManagementFeatures {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file = "";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void featureManagementValidations() throws InterruptedException, IOException, ParseException {
        ControllingWithFeatureManagement controlling = new ControllingWithFeatureManagement(driver, file);
//        controlling.validateMenuItemsNegative();
//        controlling.validateMenuItemsPositive();
//        controlling.validateTransactionFieldsPositive();
//        controlling.validateTransactionFieldsNegative();
        controlling.enableDragAndDropNodes();
//        controlling.disableDragAndDropToNodes();
    }

    @AfterTest
    public void afterTest() throws IOException {
//        appLogin.logout();
    }

}
