package menuItems.finance.masters.ManageCurrencies;

import com.wings.pages.AppLogin;
import com.wings.pages.finance.masters.ManageCurrencies.SetExchangeRate;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class SetExchangeRateMaster {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String dataFile = "./";

    @BeforeTest
    public void beforeTest() throws IOException, ParseException, InterruptedException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void exchangeRate() throws IOException, ParseException, InterruptedException, AWTException {
        SetExchangeRate exchangeRate = new SetExchangeRate(driver, dataFile);
        exchangeRate.exchangeRate();

    }
}
