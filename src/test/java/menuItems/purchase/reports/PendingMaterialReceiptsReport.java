package menuItems.purchase.reports;

import com.wings.pages.purchase.reports.PendingMaterialReceipts;
import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class PendingMaterialReceiptsReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
        Allure.step("Before Test PendingMaterialReceipts Report");

    }

    @Test
    public void pendingMaterialReceipts() throws  InterruptedException, AWTException {
        PendingMaterialReceipts pmr=new PendingMaterialReceipts(driver);
        pmr.pendingMaterialReceipt();
    }

    @AfterTest
    public void afterTest(){
        appLogin.logout();
        Allure.step("After Test PendingMaterialReceipts Report");

    }
}
