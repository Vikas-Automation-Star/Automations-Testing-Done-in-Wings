package menuItems.Production.reports;

import com.wings.pages.production.reports.CloseProductionOrder;
import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class CloseProductionOrderReport {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void closeProductionOrderReport() throws InterruptedException {
        CloseProductionOrder cpo=new CloseProductionOrder(driver);
        cpo.closeProductionOrder();
    }

    @AfterTest
    public void afterTest(){
        appLogin.logout();
    }
}
