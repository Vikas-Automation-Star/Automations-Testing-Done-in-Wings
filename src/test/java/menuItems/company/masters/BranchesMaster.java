package menuItems.company.masters;


import com.wings.pages.AppLogin;
import com.wings.pages.company.masters.Branches;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class BranchesMaster {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file = "./src/main/resources/MenuItems/Company/Masters/BranchData.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void branches() throws IOException, ParseException, InterruptedException, AWTException {
        Branches newBranch = new Branches(driver, file);
        newBranch.branch();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
