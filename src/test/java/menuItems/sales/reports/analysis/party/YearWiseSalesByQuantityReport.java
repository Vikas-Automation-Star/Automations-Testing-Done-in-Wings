package menuItems.sales.reports.analysis.party;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.reports.analysis.party.YearWiseSalesByQuantity;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class YearWiseSalesByQuantityReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void yearWiseSalesByQuantity() throws InterruptedException {
        YearWiseSalesByQuantity ywsbq = new YearWiseSalesByQuantity(driver);
        ywsbq.yearWiseSalesByQuantity();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
