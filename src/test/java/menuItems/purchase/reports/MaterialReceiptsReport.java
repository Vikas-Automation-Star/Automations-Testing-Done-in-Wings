package menuItems.purchase.reports;

import com.wings.pages.purchase.reports.MaterialReceipts;
import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class MaterialReceiptsReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
        Allure.step("Before Test MaterialReceipts Report");

    }

    @Test
    public void materialReceipts() throws  InterruptedException, AWTException {
       MaterialReceipts mr=new MaterialReceipts(driver);
       mr.materialReceipt();

    }

    @AfterTest
    public void afterTest(){
        appLogin.logout();
        Allure.step("After Test MaterialReceipts Report");

    }
}
