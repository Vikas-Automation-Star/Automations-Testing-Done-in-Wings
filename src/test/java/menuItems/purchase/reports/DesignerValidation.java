package menuItems.purchase.reports;

import com.wings.pages.AppLogin;
import com.wings.pages.purchase.reports.Designer;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class DesignerValidation {

    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    Common common;
    String file = "./src/main/resources/menuItems/purchase/reports/designer.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        common=new Common(driver);
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin(common.getData(file,"designer","userName"),common.getData(file,"designer","password"));
    }

    @Test
    public void purchaseVouchers() throws IOException, InterruptedException, AWTException {
        Designer designer=new Designer(driver,file);
        designer.designer();
    }

    @AfterTest
    public void afterTest() throws IOException {
//        appLogin.logout();
    }
}
