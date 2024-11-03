package menuItems.inventory.masters.product;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.inventory.masters.product.StorageBins;
import java.awt.*;
import java.io.IOException;

public class StorageBin {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file="./src/main/resources/menuItems/inventory/masters/storageBin.json";

    @BeforeTest
    public void beforeTest() throws  InterruptedException, IOException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void storageBin() throws IOException, ParseException, InterruptedException, AWTException {
        StorageBins storageBins=new StorageBins(driver,file);
        storageBins.storageBincreation();
    }

    @AfterTest
    public void afterTest() {
        appLogin.logout();
    }
}
