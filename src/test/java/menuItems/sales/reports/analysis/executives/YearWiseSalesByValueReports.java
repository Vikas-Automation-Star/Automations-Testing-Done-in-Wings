package menuItems.sales.reports.analysis.executives;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.reports.analysis.executives.YearWiseSalesByValue;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class YearWiseSalesByValueReports {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void yearWiseSalesByValue() throws InterruptedException {
        YearWiseSalesByValue ywsbv = new YearWiseSalesByValue(driver);
        ywsbv.yearWiseSalesByValue();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
