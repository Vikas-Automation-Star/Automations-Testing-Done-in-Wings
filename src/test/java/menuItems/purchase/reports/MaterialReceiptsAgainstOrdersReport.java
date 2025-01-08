package menuItems.purchase.reports;

import com.wings.pages.AppLogin;
import com.wings.pages.purchase.reports.MaterialReceiptsAgainstOrders;
import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class MaterialReceiptsAgainstOrdersReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
        Allure.step("Before Test MaterialReceiptsAgainstOrders Report");
    }

    @Test
    public void materialReceiptsAgainstOrders() throws  InterruptedException {
        MaterialReceiptsAgainstOrders mrao=new MaterialReceiptsAgainstOrders(driver);
        mrao.materialReceiptsAgainstOrder();
    }

    @AfterTest
    public void afterTest() throws IOException{
        appLogin.logout();
        Allure.step("After Test MaterialReceiptsAgainstOrders Report");
    }
}
