package menuItems.inventory.reports.masterInfo;

import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.inventory.reports.masterInfo.MasterDetails;

import java.awt.*;
import java.io.IOException;

public class TestMasterDetail {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    Common common;
    String file = "./src/main/resources/menuItems/inventory/reports/masterDetails.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        common=new Common(driver);
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin(common.getData(file,"masterDetails","userName"),common.getData(file,"masterDetails","password"));
    }

    @Test
    public void masterDetail() throws InterruptedException, AWTException, IOException, ParseException {
        MasterDetails details = new MasterDetails(driver,file);
        details.masterDetails();

    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
