package menuItems.sales.reports.analysis.sales;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.reports.analysis.sales.SalesAggregateTurnover;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class SalesAggregateTurnoverReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void yearWiseSalesByValue() throws InterruptedException {
        SalesAggregateTurnover sator = new SalesAggregateTurnover(driver);
        sator.salesAggregateTurnover();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
