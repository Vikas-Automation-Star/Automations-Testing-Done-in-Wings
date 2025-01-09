package menuItems.sales.reports.analysis.product;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.reports.analysis.product.MonthWiseSalesByQuantity;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class MonthWiseSalesByQuantityReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void monthWiseSalesByQuantity() throws InterruptedException {
        MonthWiseSalesByQuantity mwsbq = new MonthWiseSalesByQuantity(driver);
        mwsbq.monthWiseSalesByQuantity();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }

}
